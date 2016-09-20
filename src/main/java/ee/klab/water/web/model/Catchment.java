package ee.klab.water.web.model;

import java.io.Serializable;
import java.util.Collection;

public class Catchment implements Serializable {

    private int year;
    private int month;
    private double area;
    private double runoff;
    private double streamVelocity;
    private double forestArea;
    private double forestSoil1Percentage;
    private double forestSoil2Percentage;
    private double felledForestPercentage;
    private double drainedForestPercentage;
    private double fertilizedForestPercentage;
    private double wetLandArea;
    private double wetLandType1Percentage;
    private double wetLandType2Percentage;
    private double waterSurfaceArea;
    private double agriculturalLandArea;
    private double wintergreenAgriculturalLandPercentage;
    private double livestock;
    private double dairyCows;
    private double dairyUncoupledSepticSystemPercentage;
    private double dairyInfiltrationSepticSystemPercentage;
    private double dairyManureStorageSystemPercentage;
    private double leakingManureStorageSystemPercentage;
    private double individualWastewaterTreatmentPlants;
    private double individualDryToiletSystemPercentage;
    private double individualUncoupledSepticSystemPercentage;
    private double individualInfilitrationSepticSystemPercentage;
    private double aquacultureProduction;
    private Collection<Agent> agents;
    private Collection<PointSource> pointSources;

    public Catchment() {

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getRunoff() {
        return runoff;
    }

    public void setRunoff(double runoff) {
        this.runoff = runoff;
    }

    /**
     *
     * @return stream velocity (m/s)
     */
    public double getStreamVelocity() {
        return this.streamVelocity;
    }

    public void setStreamVelocity(double streamVelocity) {
        this.streamVelocity = streamVelocity;
    }

    public double getForestArea() {
        return forestArea;
    }

    public void setForestArea(double forestArea) {
        this.forestArea = forestArea;
    }

    public double getForestSoil1Percentage() {
        return forestSoil1Percentage;
    }

    public void setForestSoil1Percentage(double forestSoil1Percentage) {
        this.forestSoil1Percentage = forestSoil1Percentage;
    }

    public double getForestSoil2Percentage() {
        return forestSoil2Percentage;
    }

    public void setForestSoil2Percentage(double forestSoil2Percentage) {
        this.forestSoil2Percentage = forestSoil2Percentage;
    }

    public double getFelledForestPercentage() {
        return felledForestPercentage;
    }

    public void setFelledForestPercentage(double felledForestPercentage) {
        this.felledForestPercentage = felledForestPercentage;
    }

    public double getDrainedForestPercentage() {
        return drainedForestPercentage;
    }

    public void setDrainedForestPercentage(double drainedForestPercentage) {
        this.drainedForestPercentage = drainedForestPercentage;
    }

    public double getFertilizedForestPercentage() {
        return fertilizedForestPercentage;
    }

    public void setFertilizedForestPercentage(double fertilizedForestPercentage) {
        this.fertilizedForestPercentage = fertilizedForestPercentage;
    }

    public double getWetLandArea() {
        return wetLandArea;
    }

    public void setWetLandArea(double wetLandArea) {
        this.wetLandArea = wetLandArea;
    }

    public double getWetLandType1Percentage() {
        return wetLandType1Percentage;
    }

    public void setWetLandType1Percentage(double wetLandType1Percentage) {
        this.wetLandType1Percentage = wetLandType1Percentage;
    }

    public double getWetLandType2Percentage() {
        return wetLandType2Percentage;
    }

    public void setWetLandType2Percentage(double wetLandType2Percentage) {
        this.wetLandType2Percentage = wetLandType2Percentage;
    }

    public double getWaterSurfaceArea() {
        return waterSurfaceArea;
    }

    public void setWaterSurfaceArea(double waterSurfaceArea) {
        this.waterSurfaceArea = waterSurfaceArea;
    }

    public double getAgriculturalLandArea() {
        return agriculturalLandArea;
    }

    public void setAgriculturalLandArea(double agriculturalLandArea) {
        this.agriculturalLandArea = agriculturalLandArea;
    }

    public double getWintergreenAgriculturalLandPercentage() {
        return wintergreenAgriculturalLandPercentage;
    }

    public void setWintergreenAgriculturalLandPercentage(double wintergreenAgriculturalLandPercentage) {
        this.wintergreenAgriculturalLandPercentage = wintergreenAgriculturalLandPercentage;
    }

    public double getLivestock() {
        return livestock;
    }

    public void setLivestock(double livestock) {
        this.livestock = livestock;
    }

    public double getDairyCows() {
        return dairyCows;
    }

    public void setDairyCows(double dairyCows) {
        this.dairyCows = dairyCows;
    }

    public double getDairyUncoupledSepticSystemPercentage() {
        return dairyUncoupledSepticSystemPercentage;
    }

    public void setDairyUncoupledSepticSystemPercentage(double dairyUncoupledSepticSystemPercentage) {
        this.dairyUncoupledSepticSystemPercentage = dairyUncoupledSepticSystemPercentage;
    }

    public double getDairyInfiltrationSepticSystemPercentage() {
        return dairyInfiltrationSepticSystemPercentage;
    }

    public void setDairyInfiltrationSepticSystemPercentage(double dairyInfiltrationSepticSystemPercentage) {
        this.dairyInfiltrationSepticSystemPercentage = dairyInfiltrationSepticSystemPercentage;
    }

    public double getDairyManureStorageSystemPercentage() {
        return dairyManureStorageSystemPercentage;
    }

    public void setDairyManureStorageSystemPercentage(double dairyManureStorageSystemPercentage) {
        this.dairyManureStorageSystemPercentage = dairyManureStorageSystemPercentage;
    }

    public double getLeakingManureStorageSystemPercentage() {
        return leakingManureStorageSystemPercentage;
    }

    public void setLeakingManureStorageSystemPercentage(double leakingManureStorageSystemPercentage) {
        this.leakingManureStorageSystemPercentage = leakingManureStorageSystemPercentage;
    }

    public double getIndividualWastewaterTreatmentPlants() {
        return individualWastewaterTreatmentPlants;
    }

    public void setIndividualWastewaterTreatmentPlants(double individualWastewaterTreatmentPlants) {
        this.individualWastewaterTreatmentPlants = individualWastewaterTreatmentPlants;
    }

    public double getIndividualDryToiletSystemPercentage() {
        return individualDryToiletSystemPercentage;
    }

    public void setIndividualDryToiletSystemPercentage(double individualDryToiletSystemPercentage) {
        this.individualDryToiletSystemPercentage = individualDryToiletSystemPercentage;
    }

    public double getIndividualUncoupledSepticSystemPercentage() {
        return individualUncoupledSepticSystemPercentage;
    }

    public void setIndividualUncoupledSepticSystemPercentage(double individualUncoupledSepticSystemPercentage) {
        this.individualUncoupledSepticSystemPercentage = individualUncoupledSepticSystemPercentage;
    }

    public double getIndividualInfilitrationSepticSystemPercentage() {
        return individualInfilitrationSepticSystemPercentage;
    }

    public void setIndividualInfilitrationSepticSystemPercentage(double individualInfilitrationSepticSystemPercentage) {
        this.individualInfilitrationSepticSystemPercentage = individualInfilitrationSepticSystemPercentage;
    }

    public double getAquacultureProduction() {
        return aquacultureProduction;
    }

    public void setAquacultureProduction(double aquacultureProduction) {
        this.aquacultureProduction = aquacultureProduction;
    }

    public Collection<Agent> getAgents() {
        return agents;
    }

    public void setAgents(Collection<Agent> agents) {
        this.agents = agents;
    }

    public Collection<PointSource> getPointSources() {
        return pointSources;
    }

    public void setPointSources(Collection<PointSource> pointSources) {
        this.pointSources = pointSources;
    }

    public static class Agent implements Serializable {

        private String parameter;
        private double retentionPercentage;
        private double adjustmentCoefficient;
        private double atmosphericSpecificDischarge;

        public Agent() {

        }

        public String getParameter() {
            return this.parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public double getRetentionPercentage() {
            return this.retentionPercentage;
        }

        public void setRetentionPercentage(double retentionPercentage) {
            this.retentionPercentage = retentionPercentage;
        }

        public double getAdjustmentCoefficient() {
            return this.adjustmentCoefficient;
        }

        public void setAdjustmentCoefficient(double adjustmentCoefficient) {
            this.adjustmentCoefficient = adjustmentCoefficient;
        }

        public double getAtmosphericSpecificDischarge() {
            return this.atmosphericSpecificDischarge;
        }

        public void setAtmosphericSpecificDischarge(double atmosphericSpecificDischarge) {
            this.atmosphericSpecificDischarge = atmosphericSpecificDischarge;
        }

    }

}
