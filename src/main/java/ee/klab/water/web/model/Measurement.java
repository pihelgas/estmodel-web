package ee.klab.water.web.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Measurement implements Serializable {

    private LocalDate date;
    private String parameter;
    private double value;

    public Measurement() {

    }

    public Measurement(LocalDate date, String parameter, double value) {
        this.date = date;
        this.parameter = parameter;
        this.value = value;
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

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
