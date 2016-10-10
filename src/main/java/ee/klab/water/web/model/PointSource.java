package ee.klab.water.web.model;

import java.io.Serializable;
import java.util.Collection;

public class PointSource implements Serializable {

    private double distance;
    private double id;
    private Collection<Measurement> measurements;
    private String type;

    public PointSource() {

    }

    public PointSource(double id) {
        this.id = id;
    }

    /**
     * @return distance (km) from the station
     */
    public double getDistance() {
        return this.distance;
    }

    /**
     * @param distance distance (km) from the station
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getId() {
        return this.id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public Collection<Measurement> getMeasurements() {
        return this.measurements;
    }

    public void setMeasurements(Collection<Measurement> measurements) {
        this.measurements = measurements;
    }

    /**
     * @return VEKA type
     */
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
