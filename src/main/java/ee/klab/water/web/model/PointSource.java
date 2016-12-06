package ee.klab.water.web.model;

import java.io.Serializable;
import java.util.Collection;

public class PointSource implements Serializable {

    private String id;
    private String type;
    private double distance;
    private Collection<Measurement> measurements;

    public PointSource() {

    }

    public PointSource(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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

    /**
     * @return VEKA type
     */
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<Measurement> getMeasurements() {
        return this.measurements;
    }

    public void setMeasurements(Collection<Measurement> measurements) {
        this.measurements = measurements;
    }

}
