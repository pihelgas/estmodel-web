package ee.klab.water.web.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * PointSource input for EstModel
 */
public class PointSource implements Serializable {

    private Collection<Measurement> measurements;
    private String type;
    private double distance;
    private double flow;

    public PointSource() {

    }

    public Collection<Measurement> getMeasurements() {
        return this.measurements;
    }

    public void setMeasurements(Collection<Measurement> measurements) {
        this.measurements = measurements;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return distance (km) from the station
     */
    public double getDistance() {
        return this.distance;
    }

    /**
     *
     * @param distance distance (km) from the station
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     *
     * @return flow (m3/s)
     */
    public double getFlow() {
        return this.flow;
    }

    /**
     *
     * @param flow flow (m3/s)
     */
    public void setFlow(double flow) {
        this.flow = flow;
    }

    public static class Measurement implements Serializable {

        private double concentration;
        private double halfRetentionTime;
        private double maxRetentionPercentage;
        private String parameter;

        public Measurement() {

        }

        public double getConcentration() {
            return this.concentration;
        }

        public void setConcentration(double concentration) {
            this.concentration = concentration;
        }

        public double getHalfRetentionTime() {
            return this.halfRetentionTime;
        }

        public void setHalfRetentionTime(double halfRetentionTime) {
            this.halfRetentionTime = halfRetentionTime;
        }

        public double getMaxRetentionPercentage() {
            return this.maxRetentionPercentage;
        }

        public void setMaxRetentionPercentage(double maxRetentionPercentage) {
            this.maxRetentionPercentage = maxRetentionPercentage;
        }

        public String getParameter() {
            return this.parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

    }

}
