package ee.klab.water.web.model;

import java.io.Serializable;
import java.util.Collection;

public class PointSource implements Serializable {

    private double distance;
    private Collection<Measurement> measurements;
    private String type;

    public PointSource() {

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

}
