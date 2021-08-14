package com.company.Objects;

public class Drug {

    private int id;
    private String name;
    private String description;
    private String sideEffects;
    private int prescription;

    public Drug() {};

    public Drug (int id, String name, String description, String sideEffects, int prescription){
        this.id = id;
        this.name = name;
        this.description = description;
        this.sideEffects = sideEffects;
        this.prescription = prescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public int getPrescription() {
        return prescription;
    }

    public void setPrescription(int prescription) {
        this.prescription = prescription;
    }
}
