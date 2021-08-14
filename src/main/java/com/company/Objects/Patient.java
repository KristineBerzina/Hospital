package com.company.Objects;

public class Patient {

    private String personalId;
    private String lastName;
    private String firstName;
    private int roomNr;
    private int doctorsId;
    private String diagnosis;
    private int drugsAssigned;
    private String registeringDate;
    private String dischargeDate;
    private String notes;

    public Patient() {};

    public Patient(String personalId, String lastName, String firstName, int roomNr, int doctorsId, String diagnosis, int drugsAssigned,
                   String registeringDate, String dischargeDate, String notes) {
        this.personalId = personalId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.roomNr = roomNr;
        this.doctorsId = doctorsId;
        this.diagnosis = diagnosis;
        this.drugsAssigned = drugsAssigned;
        this.registeringDate = registeringDate;
        this.dischargeDate = dischargeDate;
        this.notes = notes;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
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

    public int getRoomNr() {
        return roomNr;
    }

    public void setRoomNr(int roomNr) {
        this.roomNr = roomNr;
    }

    public int getDoctorsId() {
        return doctorsId;
    }

    public void setDoctorsId(int doctorsId) {
        this.doctorsId = doctorsId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getDrugsAssigned() {
        return drugsAssigned;
    }

    public void setDrugsAssigned(int drugsAssigned) {
        this.drugsAssigned = drugsAssigned;
    }

    public String getRegisteringDate() {
        return registeringDate;
    }

    public void setRegisteringDate(String registeringDate) {
        this.registeringDate = registeringDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
