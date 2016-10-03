package ee.klab.water.web.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

/**
 * EstModel input
 */
public class EstModel implements Serializable {

    private int year;
    private double area;
    private double streamVelocity;
    private String naturalDischargeType;
    private double forestArea;
    private double forestSoil1Percentage;
    private double forestSoil2Percentage;
    private double felledForestPercentage;
    private double drainedForestPercentage;
    private double fertilizedForestPercentage;
    private double wetLandArea;
    private double wetLandType1Percentage;
    private double wetLandType2Percentage;
    private double wetLandType3Percentage;
    private double waterSurfaceArea;
    private double agriculturalLandArea;
    private double agriculturalLandSoil1Percentage;
    private double agriculturalLandSoil2Percentage;
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
    private Collection<Discharge> discharges;
    private Collection<PointSource> pointSources;

    public static class Discharge implements Serializable {

        private double value;
        private double retentionPercentage;
        private String parameter;
        private LocalDate date;
        private double atmosphericSpecificDischarge;
        private double adjustmentCoefficient;

        public Discharge() {

        }

        public double getValue() {
            return this.value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public double getRetentionPercentage() {
            return this.retentionPercentage;
        }

        public void setRetentionPercentage(double retentionPercentage) {
            this.retentionPercentage = retentionPercentage;
        }

        public String getParameter() {
            return this.parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public LocalDate getDate() {
            return this.date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public double getAtmosphericSpecificDischarge() {
            return this.atmosphericSpecificDischarge;
        }

        public void setAtmosphericSpecificDischarge(double atmosphericSpecificDischarge) {
            this.atmosphericSpecificDischarge = atmosphericSpecificDischarge;
        }

        public double getAdjustmentCoefficient() {
            return this.adjustmentCoefficient;
        }

        public void setAdjustmentCoefficient(double adjustmentCoefficient) {
            this.adjustmentCoefficient = adjustmentCoefficient;
        }

    }

    public EstModel() {

    }

    /**
     * @return the represented year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * @param year the ISO proleptic year to represent
     */
    public void setYear(int year) {
        this.year = year;
    }

    public double getArea() {
        return this.area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    /**
     * @return stream velocity (m/s)
     */
    public double getStreamVelocity() {
        return this.streamVelocity;
    }

    public void setStreamVelocity(double streamVelocity) {
        this.streamVelocity = streamVelocity;
    }

    public String getNaturalDischargeType() {
        return this.naturalDischargeType;
    }

    public void setNaturalDischargeType(String naturalDischargeType) {
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

    public double getWetLandType3Percentage() {
        return this.wetLandType3Percentage;
    }

    public void setWetLandType3Percentage(double wetLandType3Percentage) {
        this.wetLandType3Percentage = wetLandType3Percentage;
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

    public double getAgriculturalLandSoil1Percentage() {
        return this.agriculturalLandSoil1Percentage;
    }

    public void setAgriculturalLandSoil1Percentage(double agriculturalLandSoil1Percentage) {
        this.agriculturalLandSoil1Percentage = agriculturalLandSoil1Percentage;
    }

    public double getAgriculturalLandSoil2Percentage() {
        return this.agriculturalLandSoil2Percentage;
    }

    public void setAgriculturalLandSoil2Percentage(double agriculturalLandSoil2Percentage) {
        this.agriculturalLandSoil2Percentage = agriculturalLandSoil2Percentage;
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

    public Collection<Discharge> getDischarges() {
        return this.discharges;
    }

    public void setDischarges(Collection<Discharge> discharges) {
        this.discharges = discharges;
    }

    public Collection<PointSource> getPointSources() {
        return this.pointSources;
    }

    public void setPointSources(Collection<PointSource> pointSources) {
        this.pointSources = pointSources;
    }

    /**
     * EstModel output
     */
    public static class Estimate implements Serializable {

        private int month;
        private String parameter;
        private Collection<SourceEstimate> sources;
        private double anthropogenicDischarge;
        private double atmosphericDischarge;
        private double naturalDischarge;
        private double totalDischarge;
        private double retentionPercentage;
        private double adjustmentCoefficient;

        public Estimate() {

        }

        /**
         * @return the represented month
         */
        public int getMonth() {
            return this.month;
        }

        /**
         * @param month the month-of-year to represent, from 1 to 12
         */
        public void setMonth(int month) {
            this.month = month;
        }

        public String getParameter() {
            return this.parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public Collection<SourceEstimate> getSources() {
            return this.sources;
        }

        public void setSources(final Collection<SourceEstimate> sources) {
            this.sources = sources;
        }

        public double getAnthropogenicDischarge() {
            return this.anthropogenicDischarge;
        }

        public void setAnthropogenicDischarge(double anthropogenicDischarge) {
            this.anthropogenicDischarge = anthropogenicDischarge;
        }

        public double getAtmosphericDischarge() {
            return this.atmosphericDischarge;
        }

        public void setAtmosphericDischarge(double atmosphericDischarge) {
            this.atmosphericDischarge = atmosphericDischarge;
        }

        public double getNaturalDischarge() {
            return this.naturalDischarge;
        }

        public void setNaturalDischarge(double naturalDischarge) {
            this.naturalDischarge = naturalDischarge;
        }

        public double getTotalDischarge() {
            return this.totalDischarge;
        }

        public void setTotalDischarge(double totalDischarge) {
            this.totalDischarge = totalDischarge;
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

    }

    /**
     * EstModel detailed output
     */
    public static class SourceEstimate implements Serializable {

        private String id;
        private double anthropogenicDischarge;
        private double atmosphericDischarge;
        private double naturalDischarge;
        private double totalDischarge;

        public SourceEstimate() {

        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getAnthropogenicDischarge() {
            return this.anthropogenicDischarge;
        }

        public void setAnthropogenicDischarge(double anthropogenicDischarge) {
            this.anthropogenicDischarge = anthropogenicDischarge;
        }

        public double getAtmosphericDischarge() {
            return this.atmosphericDischarge;
        }

        public void setAtmosphericDischarge(double atmosphericDischarge) {
            this.atmosphericDischarge = atmosphericDischarge;
        }

        public double getNaturalDischarge() {
            return this.naturalDischarge;
        }

        public void setNaturalDischarge(double naturalDischarge) {
            this.naturalDischarge = naturalDischarge;
        }

        public double getTotalDischarge() {
            return this.totalDischarge;
        }

        public void setTotalDischarge(double totalDischarge) {
            this.totalDischarge = totalDischarge;
        }

    }

    /**
     * Lake model input
     */
    public static class Lake implements Serializable {

        private double a;
        private double b;
        private double area;
        private double depth;
        private double flow;
        private double load;
        private String parameter;
        private String type;
        private int year;

        /**
         * Lake model output
         */
        public static class Estimate implements Serializable {

            private double concentration;
            private double load;
            private double retentionPercentage;
            private double retentionTime;

            public Estimate() {

            }

            public double getConcentration() {
                return this.concentration;
            }

            public void setConcentration(double concentration) {
                this.concentration = concentration;
            }

            public double getLoad() {
                return this.load;
            }

            public void setLoad(double load) {
                this.load = load;
            }

            public double getRetentionPercentage() {
                return this.retentionPercentage;
            }

            public void setRetentionPercentage(double retentionPercentage) {
                this.retentionPercentage = retentionPercentage;
            }

            public double getRetentionTime() {
                return this.retentionTime;
            }

            public void setRetentionTime(double retentionTime) {
                this.retentionTime = retentionTime;
            }

        }

        public Lake() {

        }

        /**
         *
         * @return constant A
         */
        public double getA() {
            return this.a;
        }

        /**
         *
         * @param a constant A
         */
        public void setA(double a) {
            this.a = a;
        }

        /**
         *
         * @return constant B
         */
        public double getB() {
            return this.b;
        }

        /**
         *
         * @param b constant B
         */
        public void setB(double b) {
            this.b = b;
        }

        /**
         *
         * @return area (km2)
         */
        public double getArea() {
            return this.area;
        }

        /**
         *
         * @param area area (km2)
         */
        public void setArea(double area) {
            this.area = area;
        }

        /**
         *
         * @return average depth (m)
         */
        public double getDepth() {
            return this.depth;
        }

        /**
         *
         * @param depth average depth (m)
         */
        public void setDepth(double depth) {
            this.depth = depth;
        }

        /**
         *
         * @return average flow (m3/s)
         */
        public double getFlow() {
            return this.flow;
        }

        /**
         *
         * @param flow average flow (m3/s)
         */
        public void setFlow(double flow) {
            this.flow = flow;
        }

        /**
         *
         * @return total phosphorus load (kg/a)
         */
        public double getLoad() {
            return this.load;
        }

        /**
         *
         * @param load total phosphorus load (kg/a)
         */
        public void setLoad(double load) {
            this.load = load;
        }

        public String getParameter() {
            return this.parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
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

    }

}
