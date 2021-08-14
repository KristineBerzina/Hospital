package com.company.Objects;

public class Doctor {

    private int id;
    private String lastName;
    private String firstName;
    private String speciality;

    public Doctor() {};

    public Doctor (int id, String lastName, String firstName, String speciality) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.speciality = speciality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
