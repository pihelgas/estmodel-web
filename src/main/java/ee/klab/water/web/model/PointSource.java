package ee.klab.water.web.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * PointSource input for EstModel
 */
public class PointSource implements Serializable {

    private Collection<Measurement> agents;
    private double distance;
    private double flow;
    private String type;

    public PointSource() {

    }

    public Collection<Measurement> getAgents() {
        return this.agents;
    }

    public void setAgents(Collection<Measurement> agents) {
        this.agents = agents;
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class Measurement implements Serializable {

        private double concentration;
        private double halfRetentionTime;
        private double maxRetention;
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

        public double getMaxRetention() {
            return this.maxRetention;
        }

        public void setMaxRetention(double maxRetention) {
            this.maxRetention = maxRetention;
        }

        public String getParameter() {
            return this.parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

    }

}
