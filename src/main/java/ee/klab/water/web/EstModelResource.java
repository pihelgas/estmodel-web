package ee.klab.water.web;

import ee.klab.water.EstModel.Builder;
import ee.klab.water.EstModel.Parameter;
import static ee.klab.water.model.Lake.concentration;
import static ee.klab.water.model.Lake.retentionTime;
import static ee.klab.water.model.Lake.totalEpilimnionPhosphorusConcentration;
import ee.klab.water.web.model.Catchment;
import ee.klab.water.web.model.EstModel;
import ee.klab.water.web.model.EstModel.Estimation;
import ee.klab.water.web.model.EstModel.Estimation.Discharge;
import ee.klab.water.web.model.Lake;
import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.DateTimeException;
import static java.time.temporal.ChronoUnit.SECONDS;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static ee.klab.water.model.Lake.totalPhosphorusConcentration;
import static ee.klab.water.model.Lake.volume;
import java.time.Duration;

@Path("/")
public class EstModelResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EstModel calculate(Catchment catchment) {

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

        Builder builder = new Builder(startDate, endDate)
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
                .municipalWastewaterTreatmentPlantsNitrogenDischarge(
                        load(catchment, period, Parameter.NITROGEN))
                .municipalWastewaterTreatmentPlantsPhosphorusDischarge(
                        load(catchment, period, Parameter.PHOSPHORUS));

        double nitrogenRetentionPercentage = 0;
        double phosphorusRetentionPercentage = 0;

        if (catchment.getAgents() != null) {

            final Optional<Catchment.Agent> nAgent = catchment.getAgents()
                    .stream()
                    .filter(a -> Parameter.NITROGEN.toString()
                            .equalsIgnoreCase(a.getParameter()))
                    .findFirst();

            final Optional<Catchment.Agent> pAgent = catchment.getAgents()
                    .stream()
                    .filter(a -> Parameter.PHOSPHORUS.toString()
                            .equalsIgnoreCase(a.getParameter()))
                    .findFirst();

            if (nAgent.isPresent()) {
                Catchment.Agent agent = nAgent.get();
                builder = builder
                        .atmosphericNitrogenSpecificDischarge(agent
                                .getAtmosphericSpecificDischarge())
                        .nitrogenRetentionPercentage(agent
                                .getRetentionPercentage())
                        .nitrogenAdjustmentCoefficient(agent
                                .getAdjustmentCoefficient());
                nitrogenRetentionPercentage = agent.getRetentionPercentage();
            }

            if (pAgent.isPresent()) {
                Catchment.Agent agent = pAgent.get();
                builder = builder
                        .atmosphericPhosphorusSpecificDischarge(agent
                                .getAtmosphericSpecificDischarge())
                        .phosphorusRetentionPercentage(agent
                                .getRetentionPercentage())
                        .phosphorusAdjustmentCoefficient(agent
                                .getAdjustmentCoefficient());
                phosphorusRetentionPercentage = agent.getRetentionPercentage();
            }

        }

        return convert(builder.build(),
                nitrogenRetentionPercentage, phosphorusRetentionPercentage);

    }

    @POST
    @Path("/lake")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Lake.Estimation calculateLake(Lake lake) {

        double concentration = concentration(lake.getLoad(), lake.getFlow());

        double volume = volume(lake.getArea(), lake.getDepth());

        int retentsionTime = (int) retentionTime(lake.getFlow(), volume);

        double p = totalPhosphorusConcentration(concentration, retentsionTime);

        Lake.Estimation estimation = new Lake.Estimation();

        estimation.setConcentration(p);

        return estimation;

    }

    @POST
    @Path("/lake/limnological")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Lake.Estimation calculateLimnologicalLake(Lake lake) {

        double concentration = concentration(lake.getLoad(), lake.getFlow());

        double volume = volume(lake.getArea(), lake.getDepth());

        int retentsionTime = (int) retentionTime(lake.getFlow(), volume);

        double p = totalPhosphorusConcentration(concentration, retentsionTime,
                lake.getA(), lake.getB());

        Lake.Estimation estimation = new Lake.Estimation();

        estimation.setConcentration(p);
        estimation.setRetentionTime(retentsionTime);

        return estimation;

    }

    @POST
    @Path("/lake/stratified")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Lake.Estimation calculateStratifiedLake(Lake lake) {

        double volume = volume(lake.getArea(), lake.getDepth());

        double retentsionTime = retentionTime(lake.getFlow(), volume);

        double p = totalEpilimnionPhosphorusConcentration(lake.getLoad(),
                retentsionTime, lake.getDepth());

        Lake.Estimation estimation = new Lake.Estimation();

        estimation.setConcentration(p);
        estimation.setRetentionTime(retentsionTime);

        return estimation;

    }

    private static EstModel convert(ee.klab.water.EstModel estModel,
            double nitrogenRetentionPercentage,
            double phosphorusRetentionPercentage) {

        EstModel adapter = new EstModel();

        adapter.setEstimates(estModel.getEstimations()
                .stream()
                .map(e -> {
                    Estimation estimation = new Estimation();
                    estimation.setParameter(e.getParameter()
                            .toString()
                            .toLowerCase());
                    estimation.setDetails(e.getDetails()
                            .stream()
                            .map(d -> {
                                Discharge discharge = new Discharge();

                                String source = Stream
                                        .of(d.getSource()
                                                .toString()
                                                .split("[^a-zA-Z0-9]"))
                                        .map(v -> v.substring(0, 1)
                                                .toUpperCase()
                                                + v.substring(1)
                                                .toLowerCase())
                                        .collect(Collectors.joining());
                                source = source
                                        .toLowerCase()
                                        .substring(0, 1)
                                        + source.substring(1);

                                discharge.setSource(source);

                                discharge.setAnthropogenic(d.anthropogenic());
                                discharge.setAtmospheric(d.atmospheric());
                                discharge.setNatural(d.natural());
                                discharge.setTotal(d.total());
                                return discharge;
                            })
                            .collect(Collectors.toList()));
                    estimation.setAnthropogenic(estimation.getDetails()
                            .stream()
                            .mapToDouble(Discharge::getAnthropogenic)
                            .sum());
                    estimation.setAtmospheric(e.anthropogenic());
                    estimation.setNatural(e.natural());
                    estimation.setTotal(e.total());
                    return estimation;
                })
                .collect(Collectors.toList()));

        return adapter;

    }

    /**
     *
     * @param catchment
     * @param parameter
     * @param period arvutuse ajasamm sekundites
     * @return
     */
    public double load(Catchment catchment, long period, Parameter parameter) {

        if (catchment.getPointSources() != null) {
            return catchment.getPointSources().stream().mapToDouble(point
                    -> point.getAgents()
                    .stream()
                    .filter(agent -> parameter.name()
                            .equalsIgnoreCase(agent.getParameter()))
                    .mapToDouble(agent -> {

                        double time = point.getDistance() * 1000 / catchment.getStreamVelocity() / Duration.ofDays(1).getSeconds(); // aeg punktallikast vaadeldava kohani ööpäevades

                        double retention = agent.getMaxRetention() * time / (agent.getHalfRetentionTime() + time);

                        double load = point.getFlow() * agent.getConcentration() * period / 1000; //punktallikast tulev koormus perioodil kilogrammides

                        return load - (load * retention / 100);

                    })
                    .sum()
            ).sum();
        } else {
            return 0;
        }

    }

}
