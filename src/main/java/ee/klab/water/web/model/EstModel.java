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

        public static class SourceEstimate implements Serializable {

            private String source;
            private double anthropogenicDischarge;
            private double atmosphericDischarge;
            private double naturalDischarge;
            private double totalDischarge;

            public SourceEstimate() {

            }

            public String getSource() {
                return this.source;
            }

            public void setSource(String source) {
                this.source = source;
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

    }

    public static class Lake implements Serializable {

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
