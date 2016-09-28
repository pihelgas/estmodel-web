package ee.klab.water.web.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * EstModel output
 */
public class EstModel implements Serializable {

    public static class Estimate implements Serializable {

        private String parameter;
        private Collection<SourceEstimate> sources;
        private double anthropogenicDischarge;
        private double atmosphericDischarge;
        private double naturalDischarge;
        private double totalDischarge;

        public Estimate() {

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

    }

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

    }

}
