package ee.klab.water.web.model;

import java.io.Serializable;

/**
 * Lake model input
 */
public class Lake implements Serializable {

    private double a;
    private double b;
    private double area;
    private double depth;
    private double flow;
    private double load;
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
