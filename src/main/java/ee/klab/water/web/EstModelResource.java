package ee.klab.water.web;

import ee.klab.water.EstModel.Builder;
import ee.klab.water.EstModel.Estimation;
import ee.klab.water.EstModel.Parameter;
import ee.klab.water.web.model.EstModel;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import static java.time.temporal.ChronoUnit.SECONDS;
import java.util.Collection;
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
    public Collection<EstModel.Estimate> post(final EstModel model) {

        return convert(estimate(model));

    }

    @POST
    @Path("/lake")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EstModel.Lake.Estimate post(final EstModel.Lake model) {

        if (!Parameter.PHOSPHORUS.toString()
                .equalsIgnoreCase(model.getParameter())) {

            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }

        final Year year = Year.of(model.getYear());
        final double flow = model.getFlow() * DAYS.toSeconds(year.length()); // m3/yr
        final double volume = model.getArea() * 1000000 * model.getDepth(); // m3
        final double retentionTime = flow / volume; // yr
        final double inputConcentration = model.getLoad() * 1000 / flow; // g/m3 = mg/l

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
            outputConcentration = load * 1000 / flow;

        } else {

            outputConcentration = inputConcentration
                    / (1 + Math.sqrt(retentionTime));

        }

        final double load = outputConcentration * flow / 1000; // kg/a
        final double retentionPercentage = (1 - (load / model.getLoad())) * 100;

        final EstModel.Lake.Estimate estimate = new EstModel.Lake.Estimate();
        estimate.setConcentration(outputConcentration);
        estimate.setLoad(load);
        estimate.setRetentionPercentage(retentionPercentage);
        estimate.setRetentionTime(retentionTime);
        return estimate;

    }

//    private double load(Catchment catchment, long period, Parameter parameter) {
//      max = 20%, half = 3; //ööp
//        if (catchment.getPointSources() != null) {
//            return catchment.getPointSources().stream()
//                    .mapToDouble(point -> point.getMeasurements()
//                            .stream()
//                            .filter(agent -> parameter.name()
//                                    .equalsIgnoreCase(agent.getParameter()))
//                            .mapToDouble(agent -> {
//
//                                double time = point.getDistance() * 1000 / catchment.getStreamVelocity() / Duration.ofDays(1).getSeconds(); // aeg punktallikast vaadeldava kohani ööpäevades
//
//                                double retention = agent.getMaxRetentionPercentage() * time / (agent.getHalfRetentionTime() + time);
//
//                                double load = point.getFlow() * agent.getConcentration() * period / 1000; //punktallikast tulev koormus perioodil kilogrammides
//
//                                return load - (load * retention / 100);
//
//                            })
//                            .sum()
//                    ).sum();
//        } else {
//            return 0;
//        }
//
//    }
    public Collection<Estimation> estimate(final EstModel model) {

        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = Year.of(model.getYear()).atDay(1);
            endDate = startDate.plusYears(1);
        } catch (DateTimeException e) {
            throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
        }

        long period = SECONDS
                .between(startDate.atStartOfDay(), endDate.atStartOfDay());

        final Builder builder = new Builder(startDate, endDate)
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
                        .getAquacultureProduction());
//                .industryNitrogenDischarge(
//                        load(model, period, Parameter.NITROGEN))
//                .industryPhosphorusDischarge(
//                        load(model, period, Parameter.PHOSPHORUS));
//
//        if (model.getAgents() != null) {
//
//            model.getAgents()
//                    .stream()
//                    .filter(agent -> Parameter.NITROGEN.toString()
//                            .equalsIgnoreCase(agent.getParameter()))
//                    .findFirst()
//                    .ifPresent(agent -> builder
//                            .atmosphericNitrogenSpecificDischarge(agent
//                                    .getAtmosphericSpecificDischarge())
//                            .nitrogenRetentionPercentage(agent
//                                    .getRetentionPercentage())
//                            .nitrogenAdjustmentCoefficient(agent
//                                    .getAdjustmentCoefficient()));
//
//            model.getAgents()
//                    .stream()
//                    .filter(agent -> Parameter.PHOSPHORUS.toString()
//                            .equalsIgnoreCase(agent.getParameter()))
//                    .findFirst()
//                    .ifPresent(agent -> builder
//                            .atmosphericPhosphorusSpecificDischarge(agent
//                                    .getAtmosphericSpecificDischarge())
//                            .phosphorusRetentionPercentage(agent
//                                    .getRetentionPercentage())
//                            .phosphorusAdjustmentCoefficient(agent
//                                    .getAdjustmentCoefficient()));
//
//        }

        return builder.build().getEstimations();

    }

    protected static Collection<EstModel.Estimate> convert(
            Collection<? extends ee.klab.water.EstModel.Estimation> estimates) {

        return estimates
                .stream()
                .map(d -> {
                    EstModel.Estimate estimate = new EstModel.Estimate();
                    estimate.setParameter(d.getParameter()
                            .toString()
                            .toLowerCase());
                    estimate.setDiffuseSources(d.getDetails()
                            .stream()
                            .map(s -> {
                                EstModel.DiffuseSourceEstimate se
                                        = new EstModel.DiffuseSourceEstimate();
                                se.setId(convert(s.getSource()
                                        .toString()));
                                se.setAnthropogenicDischarge(s.anthropogenic());
                                se.setAtmosphericDischarge(s.atmospheric());
                                se.setNaturalDischarge(s.natural());
                                se.setTotalDischarge(s.total());
                                return se;
                            })
                            .collect(Collectors.toList()));
                    estimate.setAnthropogenicDischarge(d.anthropogenic());
                    estimate.setAtmosphericDischarge(d.atmospheric());
                    estimate.setNaturalDischarge(d.natural());
                    estimate.setTotalDischarge(d.total());
                    return estimate;
                })
                .collect(Collectors.toList());

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
