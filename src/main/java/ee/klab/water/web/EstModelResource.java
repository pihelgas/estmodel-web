package ee.klab.water.web;

import ee.klab.estmodel.EstModel.Builder;
import ee.klab.estmodel.EstModel.Estimation;
import ee.klab.estmodel.EstModel.Parameter;
import ee.klab.estmodel.EstModel.Source;
import ee.klab.water.web.model.EstModel;
import ee.klab.water.web.model.EstModel.PointSourceEstimate;
import ee.klab.water.web.model.Measurement;
import ee.klab.water.web.model.PointSource;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.SECONDS;
import java.time.temporal.TemporalUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class EstModelResource {

    protected final static double NITROGEN_MINIMUM_DIFFUSE_CONCENTRATION = 0.73; // mg/l
    protected final static double PHOSPHORUS_MINIMUM_DIFFUSE_CONCENTRATION = 0.016; // mg/l

    /**
     * WORK IN PROGRESS
     */
    private final static Comparator<Measurement> DATE = Comparator
            .comparing(Measurement::getDate);

    protected TemporalUnit timestep = MONTHS;

    protected String nitrogen = "nyld";
    protected String phosphorus = "pyld";
    protected String flow = "veehulk";

    protected double nitrogenRetentionPercentage = 0; // %
    protected double nitrogenMaxRetentionPercentage = 20; // %
    protected double nitrogenHalfRetentionTime = 3; // ööp

    protected double phosphorusRetentionPercentage = 0; // %
    protected double phosphorusMaxRetentionPercentage = 20; // %
    protected double phosphorusHalfRetentionTime = 3; // ööp

    protected double streamVelocity = 0.2; // m/s

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstModel.Estimate> postEstModel(final EstModel model) {

        final LocalDate start = Year.of(model.getYear()).atDay(1);
        final LocalDate end = start.plusYears(1);

        final Predicate<Measurement> isThisYear = m -> m.getDate() != null
                && m.getDate().getYear() == model.getYear();

        final Set<Measurement> measurements = Optional
                .ofNullable(model.getMeasurements())
                .orElse(Collections.emptySet())
                .stream()
                .filter(isThisYear)
                .distinct()
                .collect(Collectors.groupingBy(Measurement::getParameter))
                .values()
                .stream()
                .flatMap(ms -> interpolate(ms.stream()))
                .collect(Collectors.toSet());

        final Set<PointSource> pointSources = Optional
                .ofNullable(model.getPointSources())
                .orElse(Collections.emptySet())
                .stream()
                .filter(p -> p.getMeasurements() != null && !p.getMeasurements().isEmpty())
                .map(p -> {
                    PointSource pointSource = new PointSource();
                    pointSource.setId(p.getId());
                    pointSource.setType(p.getType());
                    pointSource.setDistance(p.getDistance());
                    pointSource.setMeasurements(p.getMeasurements()
                            .stream()
                            .filter(isThisYear)
                            .distinct()
                            .collect(Collectors.groupingBy(Measurement::getParameter))
                            .entrySet()
                            .stream()
                            .flatMap(e -> interpolate(e.getValue().stream()))
                            .collect(Collectors.toSet()));
                    return pointSource;
                }).collect(Collectors.toSet());

        return Stream
                .iterate(start, date -> date.plus(1, timestep))
                .limit(start.until(end, timestep))
                .flatMap(from -> {
                    LocalDate to = from.plus(1, timestep);

                    Predicate<Measurement> isInTimestep = m -> (from.isBefore(m.getDate()) || from.isEqual(m.getDate())) && to.isAfter(m.getDate());

                    final Set<PointSourceEstimate> nitrogenPointSources = pointSources
                            .stream()
                            .map(p -> {
                                double discharge = p.getMeasurements()
                                        .parallelStream()
                                        .filter(m -> nitrogen.equalsIgnoreCase(m.getParameter()))
                                        .filter(isInTimestep)
                                        .mapToDouble(Measurement::getDischarge)
                                        .sum();
                                PointSourceEstimate estimate = new PointSourceEstimate();
                                estimate.setId(p.getId());
                                switch (p.getType()) {
                                    case "1":
                                        estimate.setRetentionPercentage(nitrogenMaxRetentionPercentage);
                                        estimate.setDischarge(discharge * ((100 - nitrogenMaxRetentionPercentage) / 100));
                                        break;
                                    case "4":
                                        // aeg punktallikast vaadeldava kohani ööpäevades
                                        double time = p.getDistance() * 1000 / streamVelocity / Duration.ofDays(1).getSeconds();

                                        double retentionPercentage = nitrogenMaxRetentionPercentage * time / (nitrogenHalfRetentionTime + time);

                                        estimate.setRetentionPercentage(retentionPercentage);
                                        estimate.setDischarge(discharge * ((100 - retentionPercentage) / 100));
                                        break;
                                    default:
                                        estimate.setRetentionPercentage(100);
                                        estimate.setDischarge(0.0);
                                }
                                return estimate;
                            })
                            .collect(Collectors.toSet());

                    final Set<PointSourceEstimate> phosphorusPointSources = pointSources
                            .stream()
                            .map(p -> {
                                double discharge = p.getMeasurements()
                                        .parallelStream()
                                        .filter(m -> phosphorus.equalsIgnoreCase(m.getParameter()))
                                        .filter(m -> (from.isBefore(m.getDate()) || from.isEqual(m.getDate())) && to.isAfter(m.getDate()))
                                        .mapToDouble(Measurement::getDischarge)
                                        .sum();
                                PointSourceEstimate estimate = new PointSourceEstimate();
                                estimate.setId(p.getId());
                                switch (p.getType()) {
                                    case "1":
                                        estimate.setRetentionPercentage(phosphorusMaxRetentionPercentage);
                                        estimate.setDischarge(discharge * ((100 - phosphorusMaxRetentionPercentage) / 100));
                                        break;
                                    case "4":
                                        // aeg punktallikast vaadeldava kohani ööpäevades
                                        double time = p.getDistance() * 1000 / streamVelocity / Duration.ofDays(1).getSeconds();

                                        double retentionPercentage = phosphorusMaxRetentionPercentage * time / (phosphorusHalfRetentionTime + time);

                                        estimate.setRetentionPercentage(retentionPercentage);
                                        estimate.setDischarge(discharge * ((100 - retentionPercentage) / 100));
                                        break;
                                    default:
                                        estimate.setRetentionPercentage(100);
                                        estimate.setDischarge(0.0);
                                }
                                return estimate;
                            })
                            .collect(Collectors.toSet());

                    final Set<Measurement> flowMeasurements = measurements
                            .parallelStream()
                            .filter(m -> flow.equalsIgnoreCase(m.getParameter()))
                            .filter(isInTimestep)
                            .collect(Collectors.toSet());

                    final Set<Measurement> nitrogenMeasurements = measurements
                            .parallelStream()
                            .filter(m -> nitrogen.equalsIgnoreCase(m.getParameter()))
                            .filter(isInTimestep)
                            .collect(Collectors.toSet());

                    final Set<Measurement> phosphorusMeasurements = measurements
                            .parallelStream()
                            .filter(m -> phosphorus.equalsIgnoreCase(m.getParameter()))
                            .filter(isInTimestep)
                            .collect(Collectors.toSet());

                    final double nitrogenPointSourceDischarge
                            = nitrogenPointSources.stream()
                                    .mapToDouble(PointSourceEstimate::getDischarge)
                                    .sum();

                    final double phosphorusPointSourceDischarge
                            = phosphorusPointSources.stream()
                                    .mapToDouble(PointSourceEstimate::getDischarge)
                                    .sum();

                    final double runoff = flowMeasurements.stream()
                            .mapToDouble(Measurement::getDischarge)
                            .sum();

                    final ee.klab.estmodel.EstModel estModel = new Builder(start, end)
                            .area(model
                                    .getArea())
                            .forestArea(model
                                    .getForestArea())
                            .forestSoil1Percentage(model
                                    .getForestSoil1Percentage())
                            .forestSoil2Percentage(model
                                    .getForestSoil2Percentage())
                            .felledForestPercentage(model
                                    .getFelledForestPercentage())
                            .drainedForestPercentage(model
                                    .getDrainedForestPercentage())
                            .fertilizedForestPercentage(model
                                    .getFertilizedForestPercentage())
                            .wetLandArea(model
                                    .getWetLandArea())
                            .waterSurfaceArea(model
                                    .getWaterSurfaceArea())
                            .agriculturalLandArea(model
                                    .getAgriculturalLandArea())
                            .wintergreenAgriculturalLandPercentage(model
                                    .getWintergreenAgriculturalLandPercentage())
                            .livestock(model
                                    .getLivestock())
                            .dairyCows(model
                                    .getDairyCows())
                            .dairyUncoupledSepticSystemPercentage(model
                                    .getDairyUncoupledSepticSystemPercentage())
                            .dairyInfiltrationSepticSystemPercentage(model
                                    .getDairyInfiltrationSepticSystemPercentage())
                            .dairyManureStorageSystemPercentage(model
                                    .getDairyManureStorageSystemPercentage())
                            .leakingManureStorageSystemPercentage(model
                                    .getLeakingManureStorageSystemPercentage())
                            .individualWastewaterTreatmentPlants(model
                                    .getIndividualWastewaterTreatmentPlants())
                            .individualDryToiletSystemPercentage(model
                                    .getIndividualDryToiletSystemPercentage())
                            .individualUncoupledSepticSystemPercentage(model
                                    .getIndividualUncoupledSepticSystemPercentage())
                            .individualInfilitrationSepticSystemPercentage(model
                                    .getIndividualInfilitrationSepticSystemPercentage())
                            .aquacultureProduction(model
                                    .getAquacultureProduction())
                            .runoff(runoff / SECONDS
                                    .between(from.atStartOfDay(), to.atStartOfDay()))
                            .nitrogenAdjustmentCoefficient(1000)
                            .build();

                    Estimation nitrogenEstimation = estModel.getEstimations()
                            .stream()
                            .filter(e -> Parameter.NITROGEN.equals(e.getParameter()))
                            .findAny().get();

                    Estimation phosphorusEstimation = estModel.getEstimations()
                            .stream()
                            .filter(e -> Parameter.PHOSPHORUS.equals(e.getParameter()))
                            .findAny().get();

                    final double nitrogenDischarge = nitrogenMeasurements
                            .stream()
                            .mapToDouble(Measurement::getDischarge)
                            .sum();

                    final double phosphorusDischarge = phosphorusMeasurements
                            .stream()
                            .mapToDouble(Measurement::getDischarge)
                            .sum();

                    final double nitrogenDiffuseDischarge;
                    final double phosphorusDiffuseDischarge;

                    if ((nitrogenDischarge - nitrogenPointSourceDischarge) * 1000 / runoff < NITROGEN_MINIMUM_DIFFUSE_CONCENTRATION) {
                        nitrogenDiffuseDischarge = NITROGEN_MINIMUM_DIFFUSE_CONCENTRATION * runoff / 1000;
                    } else {
                        nitrogenDiffuseDischarge = nitrogenDischarge - nitrogenPointSourceDischarge;
                    }

                    if ((phosphorusDischarge - phosphorusPointSourceDischarge) * 1000 / runoff < PHOSPHORUS_MINIMUM_DIFFUSE_CONCENTRATION) {
                        phosphorusDiffuseDischarge = PHOSPHORUS_MINIMUM_DIFFUSE_CONCENTRATION * runoff / 1000;
                    } else {
                        phosphorusDiffuseDischarge = phosphorusDischarge - phosphorusPointSourceDischarge;
                    }

                    double nitrogenAdjustmentCoefficient = Optional
                            .ofNullable(model.getAdjustments())
                            .orElse(Collections.emptySet())
                            .stream()
                            .filter(a -> nitrogen.equalsIgnoreCase(a.getParameter()))
                            .filter(a -> (from.isBefore(a.getDate()) || from.isEqual(a.getDate())) && to.isAfter(a.getDate()))
                            .mapToDouble(EstModel.Adjustment::getAdjustmentCoefficient)
                            .findFirst()
                            .orElseGet(() -> {
                                if (!nitrogenMeasurements.isEmpty()) {
                                    return nitrogenDiffuseDischarge / nitrogenEstimation.total();
                                }
                                return 1;
                            });

                    double phosphorusAdjustmentCoefficient = Optional
                            .ofNullable(model.getAdjustments())
                            .orElse(Collections.emptySet())
                            .stream()
                            .filter(a -> phosphorus.equalsIgnoreCase(a.getParameter()))
                            .filter(a -> (from.isBefore(a.getDate()) || from.isEqual(a.getDate())) && to.isAfter(a.getDate()))
                            .mapToDouble(EstModel.Adjustment::getAdjustmentCoefficient)
                            .findFirst()
                            .orElseGet(() -> {
                                if (!phosphorusMeasurements.isEmpty()) {
                                    return phosphorusDiffuseDischarge / phosphorusEstimation.total();
                                }
                                return 1;
                            });

                    EstModel.Estimate nitrogenEstimate = new EstModel.Estimate();
                    nitrogenEstimate.setParameter(nitrogen);
                    nitrogenEstimate.setDate(to.minusDays(1));
                    nitrogenEstimate.setAdjustmentCoefficient(nitrogenAdjustmentCoefficient);
                    nitrogenEstimate.setPointSources(nitrogenPointSources);
                    nitrogenEstimate.setNaturalDischarge(nitrogenEstimation.natural() * nitrogenAdjustmentCoefficient);
                    nitrogenEstimate.setAtmosphericDischarge(nitrogenEstimation.atmospheric() * nitrogenAdjustmentCoefficient);
                    nitrogenEstimate.setAnthropogenicDischarge(nitrogenEstimation.anthropogenic() * nitrogenAdjustmentCoefficient + nitrogenPointSourceDischarge);
                    nitrogenEstimate.setTotalDischarge(nitrogenEstimation.total() * nitrogenAdjustmentCoefficient + nitrogenPointSourceDischarge);
                    nitrogenEstimate.setDiffuseSources(nitrogenEstimation.getDetails()
                            .stream()
                            .filter(discharge -> !Source.MUNICIPAL_WASTEWATER_TREATMENT_PLANTS.equals(discharge.getSource()))
                            .map(discharge -> {
                                EstModel.DiffuseSourceEstimate se
                                        = new EstModel.DiffuseSourceEstimate();
                                se.setId(convert(discharge.getSource()
                                        .toString()));
                                se.setAnthropogenicDischarge(discharge.anthropogenic() * nitrogenAdjustmentCoefficient);
                                se.setAtmosphericDischarge(discharge.atmospheric() * nitrogenAdjustmentCoefficient);
                                se.setNaturalDischarge(discharge.natural() * nitrogenAdjustmentCoefficient);
                                se.setTotalDischarge(discharge.total() * nitrogenAdjustmentCoefficient);
                                se.setRetentionPercentage(nitrogenRetentionPercentage);
                                return se;
                            })
                            .collect(Collectors.toSet()));
                    EstModel.Estimate phosphorusEstimate = new EstModel.Estimate();
                    phosphorusEstimate.setParameter(phosphorus);
                    phosphorusEstimate.setDate(to.minusDays(1));
                    phosphorusEstimate.setAdjustmentCoefficient(phosphorusAdjustmentCoefficient);
                    phosphorusEstimate.setPointSources(phosphorusPointSources);
                    phosphorusEstimate.setNaturalDischarge(phosphorusEstimation.natural() * phosphorusAdjustmentCoefficient);
                    phosphorusEstimate.setAtmosphericDischarge(phosphorusEstimation.atmospheric() * phosphorusAdjustmentCoefficient);
                    phosphorusEstimate.setAnthropogenicDischarge(phosphorusEstimation.anthropogenic() * phosphorusAdjustmentCoefficient + phosphorusPointSourceDischarge);
                    phosphorusEstimate.setTotalDischarge(phosphorusEstimation.total() * phosphorusAdjustmentCoefficient + phosphorusPointSourceDischarge);
                    phosphorusEstimate.setDiffuseSources(phosphorusEstimation.getDetails()
                            .stream()
                            .filter(discharge -> !Source.MUNICIPAL_WASTEWATER_TREATMENT_PLANTS.equals(discharge.getSource()))
                            .map(discharge -> {
                                EstModel.DiffuseSourceEstimate se
                                        = new EstModel.DiffuseSourceEstimate();
                                se.setId(convert(discharge.getSource()
                                        .toString()));
                                se.setAnthropogenicDischarge(discharge.anthropogenic() * phosphorusAdjustmentCoefficient);
                                se.setAtmosphericDischarge(discharge.atmospheric() * phosphorusAdjustmentCoefficient);
                                se.setNaturalDischarge(discharge.natural() * phosphorusAdjustmentCoefficient);
                                se.setTotalDischarge(discharge.total() * phosphorusAdjustmentCoefficient);
                                se.setRetentionPercentage(phosphorusRetentionPercentage);
                                return se;
                            })
                            .collect(Collectors.toSet()));

                    return Stream.of(nitrogenEstimate, phosphorusEstimate);
                })
                .collect(Collectors.toList());

    }

    @POST
    @Path("lake")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public EstModel.Lake.Estimate postLakeEstModel(final EstModel.Lake model) {

//        if (!Parameter.PHOSPHORUS.toString()
//                .equalsIgnoreCase(model.getParameter())) {
//
//            throw new WebApplicationException(Response.Status.BAD_REQUEST);
//
//        }
//
        final double volume = model.getArea() * 1000000 * model.getDepth(); // m3
        final double retentionTime = model.getFlow() / volume; // yr
        final double inputConcentration = model.getLoad() * 1000 / model.getFlow(); // g/m3 = mg/l

        double outputConcentration; // g/m3 = mg/l

        if ("limnological".equalsIgnoreCase(model.getType())) {

            outputConcentration = model.getA()
                    * Math.pow((inputConcentration / 1000)
                            / (1 + Math.sqrt(retentionTime)), model.getB())
                    * 1000;

        } else if ("stratified".equalsIgnoreCase(model.getType())) {

            final double input = model.getLoad() * 1000000 / model.getArea(); // mg/m2/yr
            final double q = model.getDepth() / retentionTime; // m/yr
            final double r = 15 / (18 + q); // yr/m
            final double output = input / (q * (1 - r)); // mg/m2/yr

            final double load = output / 1000000 * model.getArea(); // kg/a
            outputConcentration = load * 1000 / model.getFlow();

        } else {

            outputConcentration = inputConcentration
                    / (1 + Math.sqrt(retentionTime));

        }

        final double load = outputConcentration * model.getFlow() / 1000; // kg/a
        final double retentionPercentage = (1 - (load / model.getLoad())) * 100;

        final EstModel.Lake.Estimate estimate = new EstModel.Lake.Estimate();
        estimate.setConcentration(outputConcentration);
        estimate.setLoad(load);
        estimate.setRetentionPercentage(retentionPercentage);
        estimate.setRetentionTime(retentionTime);
        return estimate;

    }

    protected Stream<Measurement> interpolate(Stream<Measurement> stream) {

        final Set<Measurement> measurements = stream
                .collect(Collectors.toSet());

        if (measurements.isEmpty()) {
            return Stream.empty();
        }

        final LocalDate from = measurements
                .stream()
                .min(DATE).get()
                .getDate().withDayOfYear(1);
        final LocalDate to = measurements.stream()
                .max(DATE).get()
                .getDate().withDayOfYear(1).plusYears(1);

        return Stream
                .iterate(from, date -> date.plus(1, DAYS))
                .limit(from.until(to, DAYS))
                .map(date -> {

                    final Measurement closest = measurements.stream()
                            .filter(m -> date.isBefore(m.getDate())
                            || date.isEqual(m.getDate()))
                            .min(DATE)
                            .orElseGet(() -> measurements.stream()
                            .filter(m -> date.isAfter(m.getDate()))
                            .max(DATE)
                            .get());

                    final Measurement current = new Measurement();
                    current.setDate(date);
                    current.setParameter(closest.getParameter());
                    current.setDischarge(closest.getDischarge());
                    return current;

                });

    }

    private Stream<Measurement> convert(Stream<Measurement> stream) {

        final Set<Measurement> measurements = stream
                .collect(Collectors.toSet());

        return measurements
                .stream()
                .map(current -> {

                    final Measurement correct = new Measurement();
                    correct.setDate(current.getDate());
                    correct.setParameter(current.getParameter());

                    final Optional<Measurement> previous = measurements.stream()
                            .filter(m -> m.getDate().isBefore(current.getDate()))
                            .max(DATE);

                    if (previous.isPresent()) {
                        correct.setDischarge(current.getDischarge()
                                / (current.getDate().getDayOfYear()
                                - previous.get().getDate().getDayOfYear()));
                    } else {
                        correct.setDischarge(current.getDischarge()
                                / current.getDate().getDayOfYear());
                    }

                    return correct;

                });

    }

    protected String convert(String string) {

        final String upperCamelCase = Stream.of(string.split("[^a-zA-Z0-9]"))
                .map(v -> v.substring(0, 1).toUpperCase()
                + v.substring(1).toLowerCase())
                .collect(Collectors.joining());

        return upperCamelCase.toLowerCase().substring(0, 1)
                + upperCamelCase.substring(1);

    }

}
