package ee.klab.water.web.model;

import java.io.Serializable;
import java.util.Collection;

public class Catchment implements Serializable {

    private int year;
    private int month;
    private double area;
    private double runoff;
    private double streamVelocity;
    private int naturalDischargeType;
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
    private Collection<Discharge> agents;
    private Collection<PointSource> pointSources;

    public Catchment() {

    }

    /**
     *
     * @return the represented year
     */
    public int getYear() {
        return this.year;
    }

    /**
     *
     * @param year the ISO proleptic year to represent
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     *
     * @return the represented month
     */
    public int getMonth() {
        return this.month;
    }

    /**
     *
     * @param month the month-of-year to represent, from 1 to 12
     */
    public void setMonth(int month) {
        this.month = month;
    }

    public double getArea() {
        return this.area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getRunoff() {
        return this.runoff;
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

    public int getNaturalDischargeType() {
        return this.naturalDischargeType;
    }

    public void setNaturalDischargeType(int naturalDischargeType) {
        this.naturalDischargeType = naturalDischargeType;
    }

    public double getForestArea() {
        return this.forestArea;
    }

    public void setForestArea(double forestArea) {
        this.forestArea = forestArea;
    }

    public double getForestSoil1Percentage() {
        return this.forestSoil1Percentage;
    }

    public void setForestSoil1Percentage(double forestSoil1Percentage) {
        this.forestSoil1Percentage = forestSoil1Percentage;
    }

    public double getForestSoil2Percentage() {
        return this.forestSoil2Percentage;
    }

    public void setForestSoil2Percentage(double forestSoil2Percentage) {
        this.forestSoil2Percentage = forestSoil2Percentage;
    }

    public double getFelledForestPercentage() {
        return this.felledForestPercentage;
    }

    public void setFelledForestPercentage(double felledForestPercentage) {
        this.felledForestPercentage = felledForestPercentage;
    }

    public double getDrainedForestPercentage() {
        return this.drainedForestPercentage;
    }

    public void setDrainedForestPercentage(double drainedForestPercentage) {
        this.drainedForestPercentage = drainedForestPercentage;
    }

    public double getFertilizedForestPercentage() {
        return this.fertilizedForestPercentage;
    }

    public void setFertilizedForestPercentage(double fertilizedForestPercentage) {
        this.fertilizedForestPercentage = fertilizedForestPercentage;
    }

    public double getWetLandArea() {
        return this.wetLandArea;
    }

    public void setWetLandArea(double wetLandArea) {
        this.wetLandArea = wetLandArea;
    }

    public double getWetLandType1Percentage() {
        return this.wetLandType1Percentage;
    }

    public void setWetLandType1Percentage(double wetLandType1Percentage) {
        this.wetLandType1Percentage = wetLandType1Percentage;
    }

    public double getWetLandType2Percentage() {
        return this.wetLandType2Percentage;
    }

    public void setWetLandType2Percentage(double wetLandType2Percentage) {
        this.wetLandType2Percentage = wetLandType2Percentage;
    }

    public double getWaterSurfaceArea() {
        return this.waterSurfaceArea;
    }

    public void setWaterSurfaceArea(double waterSurfaceArea) {
        this.waterSurfaceArea = waterSurfaceArea;
    }

    public double getAgriculturalLandArea() {
        return this.agriculturalLandArea;
    }

    public void setAgriculturalLandArea(double agriculturalLandArea) {
        this.agriculturalLandArea = agriculturalLandArea;
    }

    public double getWintergreenAgriculturalLandPercentage() {
        return this.wintergreenAgriculturalLandPercentage;
    }

    public void setWintergreenAgriculturalLandPercentage(double wintergreenAgriculturalLandPercentage) {
        this.wintergreenAgriculturalLandPercentage = wintergreenAgriculturalLandPercentage;
    }

    public double getLivestock() {
        return this.livestock;
    }

    public void setLivestock(double livestock) {
        this.livestock = livestock;
    }

    public double getDairyCows() {
        return this.dairyCows;
    }

    public void setDairyCows(double dairyCows) {
        this.dairyCows = dairyCows;
    }

    public double getDairyUncoupledSepticSystemPercentage() {
        return this.dairyUncoupledSepticSystemPercentage;
    }

    public void setDairyUncoupledSepticSystemPercentage(double dairyUncoupledSepticSystemPercentage) {
        this.dairyUncoupledSepticSystemPercentage = dairyUncoupledSepticSystemPercentage;
    }

    public double getDairyInfiltrationSepticSystemPercentage() {
        return this.dairyInfiltrationSepticSystemPercentage;
    }

    public void setDairyInfiltrationSepticSystemPercentage(double dairyInfiltrationSepticSystemPercentage) {
        this.dairyInfiltrationSepticSystemPercentage = dairyInfiltrationSepticSystemPercentage;
    }

    public double getDairyManureStorageSystemPercentage() {
        return this.dairyManureStorageSystemPercentage;
    }

    public void setDairyManureStorageSystemPercentage(double dairyManureStorageSystemPercentage) {
        this.dairyManureStorageSystemPercentage = dairyManureStorageSystemPercentage;
    }

    public double getLeakingManureStorageSystemPercentage() {
        return this.leakingManureStorageSystemPercentage;
    }

    public void setLeakingManureStorageSystemPercentage(double leakingManureStorageSystemPercentage) {
        this.leakingManureStorageSystemPercentage = leakingManureStorageSystemPercentage;
    }

    public double getIndividualWastewaterTreatmentPlants() {
        return this.individualWastewaterTreatmentPlants;
    }

    public void setIndividualWastewaterTreatmentPlants(double individualWastewaterTreatmentPlants) {
        this.individualWastewaterTreatmentPlants = individualWastewaterTreatmentPlants;
    }

    public double getIndividualDryToiletSystemPercentage() {
        return this.individualDryToiletSystemPercentage;
    }

    public void setIndividualDryToiletSystemPercentage(double individualDryToiletSystemPercentage) {
        this.individualDryToiletSystemPercentage = individualDryToiletSystemPercentage;
    }

    public double getIndividualUncoupledSepticSystemPercentage() {
        return this.individualUncoupledSepticSystemPercentage;
    }

    public void setIndividualUncoupledSepticSystemPercentage(double individualUncoupledSepticSystemPercentage) {
        this.individualUncoupledSepticSystemPercentage = individualUncoupledSepticSystemPercentage;
    }

    public double getIndividualInfilitrationSepticSystemPercentage() {
        return this.individualInfilitrationSepticSystemPercentage;
    }

    public void setIndividualInfilitrationSepticSystemPercentage(double individualInfilitrationSepticSystemPercentage) {
        this.individualInfilitrationSepticSystemPercentage = individualInfilitrationSepticSystemPercentage;
    }

    public double getAquacultureProduction() {
        return this.aquacultureProduction;
    }

    public void setAquacultureProduction(double aquacultureProduction) {
        this.aquacultureProduction = aquacultureProduction;
    }

    public Collection<Discharge> getAgents() {
        return this.agents;
    }

    public void setAgents(Collection<Discharge> agents) {
        this.agents = agents;
    }

    public Collection<PointSource> getPointSources() {
        return this.pointSources;
    }

    public void setPointSources(Collection<PointSource> pointSources) {
        this.pointSources = pointSources;
    }

    public static class Discharge implements Serializable {

        private String parameter;
        private double retentionPercentage;
        private double adjustmentCoefficient;
        private double atmosphericSpecificDischarge;

        public Discharge() {

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
