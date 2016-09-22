package ee.klab.water.web.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * EstModel output
 */
public class EstModel implements Serializable {

    private Collection<Discharge> estimates;

    public EstModel() {

    }

    public Collection<Discharge> getEstimates() {
        return estimates;
    }

    public void setEstimates(Collection<Discharge> estimates) {
        this.estimates = estimates;
    }

    public static class Discharge implements Serializable {

        private String parameter;
        private Collection<SourceDischarge> details;
        private double anthropogenic;
        private double atmospheric;
        private double natural;
        private double total;

        public Discharge() {

        }

        public String getParameter() {
            return this.parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public Collection<SourceDischarge> getDetails() {
            return this.details;
        }

        public void setDetails(final Collection<SourceDischarge> details) {
            this.details = details;
        }

        public double getAnthropogenic() {
            return this.anthropogenic;
        }

        public void setAnthropogenic(double anthropogenic) {
            this.anthropogenic = anthropogenic;
        }

        public double getAtmospheric() {
            return this.atmospheric;
        }

        public void setAtmospheric(double atmospheric) {
            this.atmospheric = atmospheric;
        }

        public double getNatural() {
            return this.natural;
        }

        public void setNatural(double natural) {
            this.natural = natural;
        }

        public double getTotal() {
            return this.total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public static class SourceDischarge implements Serializable {

            private String source;
            private double anthropogenic;
            private double atmospheric;
            private double natural;
            private double total;

            public SourceDischarge() {

            }

            public String getSource() {
                return this.source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public double getAnthropogenic() {
                return this.anthropogenic;
            }

            public void setAnthropogenic(double anthropogenic) {
                this.anthropogenic = anthropogenic;
            }

            public double getAtmospheric() {
                return this.atmospheric;
            }

            public void setAtmospheric(double atmospheric) {
                this.atmospheric = atmospheric;
            }

            public double getNatural() {
                return this.natural;
            }

            public void setNatural(double natural) {
                this.natural = natural;
            }

            public double getTotal() {
                return this.total;
            }

            public void setTotal(double total) {
                this.total = total;
            }

        }

    }

    public static class Lake implements Serializable {

        private double concentration;
        private double load;
        private double retentionPercentage;
        private double retentionTime;

        public Lake() {

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
