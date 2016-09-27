package ee.klab.water.web;

import ee.klab.water.EstModel.Builder;
import ee.klab.water.EstModel.Estimation;
import ee.klab.water.EstModel.Parameter;
import ee.klab.water.web.model.Catchment;
import ee.klab.water.web.model.EstModel;
import ee.klab.water.web.model.EstModel.Discharge;
import ee.klab.water.web.model.EstModel.Discharge.SourceDischarge;
import ee.klab.water.web.model.Lake;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import static java.time.temporal.ChronoUnit.SECONDS;
import java.util.Collection;
import java.util.Optional;
import static java.util.concurrent.TimeUnit.DAYS;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class EstModelResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EstModel post(Catchment catchment) {

        return convert(estimate(catchment));

    }

    @POST
    @Path("/lake")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EstModel.Lake post(final Lake lake) {

        final Year year = Year.of(lake.getYear());
        final double flow = lake.getFlow() * DAYS.toSeconds(year.length()); // m3/yr
        final double volume = lake.getArea() * 1000000 * lake.getDepth(); // m3
        final double retentionTime = flow / volume; // yr
        final double inputConcentration = lake.getLoad() * 1000 / flow; // g/m3 = mg/l

        double outputConcentration; // g/m3 = mg/l

        if ("limnological".equalsIgnoreCase(lake.getType())) {

            outputConcentration = lake.getA()
                    * Math.pow((inputConcentration / 1000)
                            / (1 + Math.sqrt(retentionTime)), lake.getB())
                    * 1000;

        } else if ("stratified".equalsIgnoreCase(lake.getType())) {

            final double input = lake.getLoad() * 1000000 / lake.getArea(); // mg/m2/yr
            final double q = lake.getDepth() / retentionTime; // m/yr
            final double r = 15 / (18 + q); // yr/m
            final double output = input / (q * (1 - r)); // mg/m2/yr

            final double load = output / 1000000 * lake.getArea(); // kg/a
            outputConcentration = load * 1000 / flow;

        } else {

            outputConcentration = inputConcentration
                    / (1 + Math.sqrt(retentionTime));

        }

        final double load = outputConcentration * flow / 1000; // kg/a
        final double retentionPercentage = (1 - (load / lake.getLoad())) * 100;

        final EstModel.Lake estimate = new EstModel.Lake();
        estimate.setConcentration(outputConcentration);
        estimate.setLoad(load);
        estimate.setRetentionPercentage(retentionPercentage);
        estimate.setRetentionTime(retentionTime);
        return estimate;

    }

    private double load(Catchment catchment, long period, Parameter parameter) {

        if (catchment.getPointSources() != null) {
            return catchment.getPointSources().stream().mapToDouble(point
                    -> point.getMeasurements()
                    .stream()
                    .filter(agent -> parameter.name()
                            .equalsIgnoreCase(agent.getParameter()))
                    .mapToDouble(agent -> {

                        double time = point.getDistance() * 1000 / catchment.getStreamVelocity() / Duration.ofDays(1).getSeconds(); // aeg punktallikast vaadeldava kohani ööpäevades

                        double retention = agent.getMaxRetentionPercentage() * time / (agent.getHalfRetentionTime() + time);

                        double load = point.getFlow() * agent.getConcentration() * period / 1000; //punktallikast tulev koormus perioodil kilogrammides

                        return load - (load * retention / 100);

                    })
                    .sum()
            ).sum();
        } else {
            return 0;
        }

    }

    public Collection<Estimation> estimate(final Catchment catchment) {

        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = Year
                    .of(catchment.getYear())
                    .atMonth(catchment.getMonth())
                    .atDay(1);
            endDate = startDate.plusMonths(1);
        } catch (DateTimeException e) {
            throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
        }

        long period = SECONDS
                .between(startDate.atStartOfDay(), endDate.atStartOfDay());

        final Builder builder = new Builder(startDate, endDate)
                .area(catchment
                        .getArea())
                .runoff(catchment
                        .getRunoff())
                .forestArea(catchment
                        .getForestArea())
                .forestSoil1Percentage(catchment
                        .getForestSoil1Percentage())
                .forestSoil2Percentage(catchment
                        .getForestSoil2Percentage())
                .felledForestPercentage(catchment
                        .getFelledForestPercentage())
                .drainedForestPercentage(catchment
                        .getDrainedForestPercentage())
                .fertilizedForestPercentage(catchment
                        .getFertilizedForestPercentage())
                .wetLandArea(catchment
                        .getWetLandArea())
                .waterSurfaceArea(catchment
                        .getWaterSurfaceArea())
                .agriculturalLandArea(catchment
                        .getAgriculturalLandArea())
                .wintergreenAgriculturalLandPercentage(catchment
                        .getWintergreenAgriculturalLandPercentage())
                .livestock(catchment
                        .getLivestock())
                .dairyCows(catchment
                        .getDairyCows())
                .dairyUncoupledSepticSystemPercentage(catchment
                        .getDairyUncoupledSepticSystemPercentage())
                .dairyInfiltrationSepticSystemPercentage(catchment
                        .getDairyInfiltrationSepticSystemPercentage())
                .dairyManureStorageSystemPercentage(catchment
                        .getDairyManureStorageSystemPercentage())
                .leakingManureStorageSystemPercentage(catchment
                        .getLeakingManureStorageSystemPercentage())
                .individualWastewaterTreatmentPlants(catchment
                        .getIndividualWastewaterTreatmentPlants())
                .individualDryToiletSystemPercentage(catchment
                        .getIndividualDryToiletSystemPercentage())
                .individualUncoupledSepticSystemPercentage(catchment
                        .getIndividualUncoupledSepticSystemPercentage())
                .individualInfilitrationSepticSystemPercentage(catchment
                        .getIndividualInfilitrationSepticSystemPercentage())
                .aquacultureProduction(catchment
                        .getAquacultureProduction())
                .industryNitrogenDischarge(
                        load(catchment, period, Parameter.NITROGEN))
                .industryPhosphorusDischarge(
                        load(catchment, period, Parameter.PHOSPHORUS));

        if (catchment.getAgents() != null) {

            final Optional<Catchment.Discharge> nAgent = catchment.getAgents()
                    .stream()
                    .filter(a -> Parameter.NITROGEN.toString()
                            .equalsIgnoreCase(a.getParameter()))
                    .findFirst();

            final Optional<Catchment.Discharge> pAgent = catchment.getAgents()
                    .stream()
                    .filter(a -> Parameter.PHOSPHORUS.toString()
                            .equalsIgnoreCase(a.getParameter()))
                    .findFirst();

            catchment.getAgents()
                    .stream()
                    .filter(a -> Parameter.PHOSPHORUS.toString()
                            .equalsIgnoreCase(a.getParameter()))
                    .findFirst()
                    .ifPresent(agent -> builder
                            .atmosphericNitrogenSpecificDischarge(agent
                                    .getAtmosphericSpecificDischarge())
                            .nitrogenRetentionPercentage(agent
                                    .getRetentionPercentage())
                            .nitrogenAdjustmentCoefficient(agent
                                    .getAdjustmentCoefficient()));

            catchment.getAgents()
                    .stream()
                    .filter(agent -> Parameter.PHOSPHORUS.toString()
                            .equalsIgnoreCase(agent.getParameter()))
                    .findFirst()
                    .ifPresent(agent -> builder
                            .atmosphericPhosphorusSpecificDischarge(agent
                                    .getAtmosphericSpecificDischarge())
                            .phosphorusRetentionPercentage(agent
                                    .getRetentionPercentage())
                            .phosphorusAdjustmentCoefficient(agent
                                    .getAdjustmentCoefficient()));

        }

        return builder.build().getEstimations();

    }

    protected static EstModel convert(
            Collection<? extends ee.klab.water.EstModel.Estimation> estimates) {

        EstModel adapter = new EstModel();

        estimates
                .stream()
                .map(e -> {
                    Discharge estimation = new Discharge();
                    estimation.setParameter(e.getParameter()
                            .toString()
                            .toLowerCase());
                    estimation.setDetails(e.getDetails()
                            .stream()
                            .map(d -> {
                                SourceDischarge discharge
                                        = new SourceDischarge();
                                discharge.setSource(convert(d.getSource()
                                        .toString()));
                                discharge.setAnthropogenic(d.anthropogenic());
                                discharge.setAtmospheric(d.atmospheric());
                                discharge.setNatural(d.natural());
                                discharge.setTotal(d.total());
                                return discharge;
                            })
                            .collect(Collectors.toList()));
                    estimation.setAnthropogenic(e.anthropogenic());
                    estimation.setAtmospheric(e.atmospheric());
                    estimation.setNatural(e.natural());
                    estimation.setTotal(e.total());
                    return estimation;
                })
                .collect(Collectors.toList());

        return adapter;

    }

    private static String convert(String string) {

        String upperCamelCase = Stream
                .of(string.split("[^a-zA-Z0-9]"))
                .map(v -> v.substring(0, 1).toUpperCase()
                        + v.substring(1).toLowerCase())
                .collect(Collectors.joining());

        return upperCamelCase.toLowerCase().substring(0, 1)
                + upperCamelCase.substring(1);

    }

}
