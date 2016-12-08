package ee.klab.water.web.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Measurement implements Serializable {

    private LocalDate date;
    private String parameter;
    private double discharge;

    public Measurement() {

    }

    /**
     *
     * @return the last day of the measurement period
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     *
     * @param date the last day of the measurement period
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getParameter() {
        return this.parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     *
     * @return discharge from previous measurement to this one or from the
     * beginning of the year (kg for nitrogen and phosphorus, m3 for flow)
     */
    public double getDischarge() {
        return this.discharge;
    }

    /**
     *
     * @param discharge discharge from previous measurement to this one or from
     * the beginning of the year (kg for nitrogen and phosphorus, m3 for flow)
     */
    public void setDischarge(double discharge) {
        this.discharge = discharge;
    }

}
