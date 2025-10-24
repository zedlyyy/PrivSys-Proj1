package pt.unl.fct.pds.proj1server.model;

import jakarta.persistence.*;

public class CountResponse {
    private String attribute;
    private double value;
    private int sens;
    private double budget;

    public CountResponse() {
    }

    public CountResponse(
            String attribute,
            double value, int sens, double budget) {
        this.attribute = attribute;
        this.value = value;
        this.sens = sens;
        this.budget = budget;
    }

    public String getAttribute() {
        return attribute;
    }

    public double getValue() {
        return value;
    }

    public int getSens() {
        return sens;
    }

    public double getBudget() {
        return budget;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setSens(int sens){
            this.sens = sens;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

}
