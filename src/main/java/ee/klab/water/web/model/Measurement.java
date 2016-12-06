package ee.klab.water.web.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Measurement implements Serializable {

    private LocalDate date;
    private String parameter;
    private double discharge;

    public Measurement() {

    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getParameter() {
        return this.parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public double getDischarge() {
        return this.discharge;
    }

    public void setDischarge(double discharge) {
        this.discharge = discharge;
    }

}
