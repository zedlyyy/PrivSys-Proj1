package pt.unl.fct.pds.proj1server.model;

import jakarta.persistence.*;

public class CountResponse {
    private String attribute;
    private int value;
    private int sens;
    private double budget;

    public CountResponse() {
    }

    public CountResponse(
            String attribute,
            int value, int sens, double budget) {
        this.attribute = attribute;
        this.value = value;
        this.sens = sens;
        this.budget = budget;
    }

    public String getAttribute() {
        return attribute;
    }

    public int getValue() {
        return value;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
