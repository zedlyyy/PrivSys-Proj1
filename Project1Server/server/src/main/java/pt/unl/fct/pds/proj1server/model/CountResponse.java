package pt.unl.fct.pds.proj1server.model;

import jakarta.persistence.*;

public class CountResponse {
    private String attribute;
    private double value;
    private int countSens;
    private int sumSens;
    private double budget;

    public CountResponse() {
    }

    public CountResponse(
            String attribute,
            double value, int countSens, double budget) {
        this.attribute = attribute;
        this.value = value;
        this.countSens = countSens;
        this.budget = budget;
    }

    public CountResponse(
            String attribute,
            double value, int countSens, int sumSens, double budget) {
        this.attribute = attribute;
        this.value = value;
        this.countSens = countSens;
        this.sumSens = sumSens;
        this.budget = budget;
    }

    public String getAttribute() {
        return attribute;
    }

    public double getValue() {
        return value;
    }

    public int getCountSens() {
        return countSens;
    }

    public int getSumSens() {
        return sumSens;
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

    public void setCountSens(int sens){
            this.countSens = sens;
    }

    public void setSumSens(int sens){
            this.sumSens = sens;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

}
