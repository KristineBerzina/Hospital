package com.company.actions;

import com.company.Objects.Patient;
import com.company.connectToDB.dbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PatientController {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Scanner scanner = new Scanner(System.in);

    public static Patient getPatientData() {
        System.out.print("Enter the personal ID of the patient: ");
        String personalId = scanner.next();

        Patient patient = new Patient();

        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM patients WHERE personal_id='" + personalId + "'");
            rs = ps.executeQuery();

            int roomNr, doctorsId, drugs;
            String lastName, firstName, diagnosis, registeringDate, dischargeDate, notes;

            while (rs.next()) {
                personalId = rs.getString("personal_id");
                lastName = rs.getString("last_name");
                firstName = rs.getString("first_name");
                roomNr = rs.getInt("room_nr");
                doctorsId = rs.getInt("doctors_id");
                diagnosis = rs.getString("diagnosis");
                drugs = rs.getInt("drugs_assigned");
                registeringDate = rs.getString("registering_date");
                dischargeDate = rs.getString("discharge_date");
                notes = rs.getString("notes");

                patient.setPersonalId(personalId);
                patient.setLastName(lastName);
                patient.setFirstName(firstName);
                patient.setRoomNr(roomNr);
                patient.setDoctorsId(doctorsId);
                patient.setDiagnosis(diagnosis);
                patient.setDrugsAssigned(drugs);
                patient.setRegisteringDate(registeringDate);
                patient.setDischargeDate(dischargeDate);
                patient.setNotes(notes);

                System.out.println("Personal ID: " + "\t\t" + patient.getPersonalId());
                System.out.println("Last name: " + "\t\t\t" + patient.getLastName());
                System.out.println("First name: " + "\t\t" + patient.getFirstName());
                System.out.println("Room number: " + "\t\t" + patient.getRoomNr());
                System.out.println("Doctor's ID: " + "\t\t" + patient.getDoctorsId());
                System.out.println("Diagnosis: " + "\t\t\t" + patient.getDiagnosis());
                System.out.println("Drugs assigned: " + "\t" + patient.getDrugsAssigned());
                System.out.println("Registering date: " + "\t" + patient.getRegisteringDate());
                System.out.println("Discharge date: " + "\t" + patient.getDischargeDate());
                System.out.println("Notes: " + "\t\t\t\t" + patient.getNotes());
            }

            return patient;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Patient getPatientDataFromArchive() {
        System.out.print("Enter the personal ID of the patient: ");
        String personalId = scanner.next();

        Patient patient = new Patient();

        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM patient_archive WHERE personal_id='" + personalId + "'");
            rs = ps.executeQuery();

            int roomNr, doctorsId, drugs;
            String lastName, firstName, diagnosis, registeringDate, dischargeDate, notes;

            while (rs.next()) {
                personalId = rs.getString("personal_id");
                lastName = rs.getString("last_name");
                firstName = rs.getString("first_name");
                roomNr = rs.getInt("room_nr");
                doctorsId = rs.getInt("doctors_id");
                diagnosis = rs.getString("diagnosis");
                drugs = rs.getInt("drugs_assigned");
                registeringDate = rs.getString("registering_date");
                dischargeDate = rs.getString("discharge_date");
                notes = rs.getString("notes");

                patient.setPersonalId(personalId);
                patient.setLastName(lastName);
                patient.setFirstName(firstName);
                patient.setRoomNr(roomNr);
                patient.setDoctorsId(doctorsId);
                patient.setDiagnosis(diagnosis);
                patient.setDrugsAssigned(drugs);
                patient.setRegisteringDate(registeringDate);
                patient.setDischargeDate(dischargeDate);
                patient.setNotes(notes);

                System.out.println("Personal ID: " + "\t\t" + patient.getPersonalId());
                System.out.println("Last name: " + "\t\t\t" + patient.getLastName());
                System.out.println("First name: " + "\t\t" + patient.getFirstName());
                System.out.println("Room number: " + "\t\t" + patient.getRoomNr());
                System.out.println("Doctor's ID: " + "\t\t" + patient.getDoctorsId());
                System.out.println("Diagnosis: " + "\t\t\t" + patient.getDiagnosis());
                System.out.println("Drugs assigned: " + "\t" + patient.getDrugsAssigned());
                System.out.println("Registering date: " + "\t" + patient.getRegisteringDate());
                System.out.println("Discharge date: " + "\t" + patient.getDischargeDate());
                System.out.println("Notes: " + "\t\t\t\t" + patient.getNotes());
            }

            return patient;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addNewPatient() {
        System.out.print("Enter the patient's personal ID: ");
        String personalId = scanner.next();
        System.out.print("Enter the patient's last name: ");
        String lastName = scanner.next();
        System.out.print("Enter the patient's first name: ");
        String firstName = scanner.next();
        System.out.print("Enter the date of registering the patient: ");
        String registeringDate = scanner.next();
        System.out.print("Enter the assigned room number: ");
        int roomNr = scanner.nextInt();

        try {
            ps = dbConnection.getConnection().prepareStatement("INSERT INTO patients(personal_id, last_name, first_name, room_nr, registering_date) " +
                    "VALUES('" + personalId + "', '" + lastName + "', '" + firstName + "', " + roomNr + ", '" + registeringDate + "')");
            ps.execute();
            System.out.println("Successfully added basic patient data.");

        } catch (SQLException e) {
            System.out.println("Failed to add patient's data. Please try again!");
            e.printStackTrace();

        }

        System.out.println("Do you wish to add additional data for this patient? Print 1 for yes or 2 for no: ");
        int answer = scanner.nextInt();
        if (answer == 1){
            System.out.println("Please indicate which data you wish to add: ");
            System.out.println("1. Doctor's ID");
            System.out.println("2. Diagnosis");
            System.out.println("3. Drugs assigned to patient");
            System.out.println("4. Notes");

            System.out.print("Select an option by entering it's number: ");
            int option = scanner.nextInt();

            switch(option) {
                case 1:
                    System.out.print("Enter the assigned doctor's ID: ");
                    int doctorsId = scanner.nextInt();
                    try {
                        ps = dbConnection.getConnection().prepareStatement("UPDATE patients SET doctors_id=" + doctorsId + "WHERE personal_id='" + personalId + "'");
                        ps.execute();
                        System.out.println("Successfully added doctor's ID.");

                    } catch (SQLException e) {
                        System.out.println("Failed to add doctor's ID. Please try again!");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.print("Enter the diagnosis: ");
                    String diagnosis = scanner.nextLine();
                    try {
                        ps = dbConnection.getConnection().prepareStatement("UPDATE patients SET diagnosis='" + diagnosis + "'WHERE personal_id='" + personalId + "'");
                        ps.execute();
                        System.out.println("Successfully added the diagnosis.");

                    } catch (SQLException e) {
                        System.out.println("Failed to add diagnosis. Please try again!");
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.print("Enter the assigned drugs: ");
                    int drugs = scanner.nextInt();
                    try {
                        ps = dbConnection.getConnection().prepareStatement("UPDATE patients SET drugs_assigned='" + drugs + "'WHERE personal_id='" + personalId + "'");
                        ps.execute();
                        System.out.println("Successfully added prescribed drugs.");

                    } catch (SQLException e) {
                        System.out.println("Failed to add prescribed drugs. Please try again!");
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.print("Add the notes: ");
                    String notes = scanner.next();
                    notes += scanner.nextLine();

                    try {
                        ps = dbConnection.getConnection().prepareStatement("UPDATE patients SET notes='" + notes + "'WHERE personal_id='" + personalId + "'");
                        ps.execute();
                        System.out.println("Successfully added the notes.");

                    } catch (SQLException e) {
                        System.out.println("Failed to add notes. Please try again!");
                        e.printStackTrace();
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please select another option.");
            }
        }else {
            System.out.println("You have finished inputting the data.");
        }

    }

    public static void editPatientData() {
        System.out.print("Enter the ID of the patient whose details you wish to edit: ");
        String personalId = scanner.next();

        System.out.print("Please confirm the ID of the patient: ");
        String compareId = scanner.next();

        Patient patient = new Patient();

        if (compareId.equals(personalId)) {
            try {
                ps = dbConnection.getConnection().prepareStatement("SELECT * FROM patients WHERE personal_id='" + personalId + "'");
                rs = ps.executeQuery();

                int roomNr, doctorsId, drugs;
                String lastName, firstName, diagnosis, registeringDate, dischargeDate, notes;

                while (rs.next()) {
                    personalId = rs.getString("personal_id");
                    lastName = rs.getString("last_name");
                    firstName = rs.getString("first_name");
                    roomNr = rs.getInt("room_nr");
                    doctorsId = rs.getInt("doctors_id");
                    diagnosis = rs.getString("diagnosis");
                    drugs = rs.getInt("drugs_assigned");
                    registeringDate = rs.getString("registering_date");
                    dischargeDate = rs.getString("discharge_date");
                    notes = rs.getString("notes");

                    patient.setPersonalId(personalId);
                    patient.setLastName(lastName);
                    patient.setFirstName(firstName);
                    patient.setRoomNr(roomNr);
                    patient.setDoctorsId(doctorsId);
                    patient.setDiagnosis(diagnosis);
                    patient.setDrugsAssigned(drugs);
                    patient.setRegisteringDate(registeringDate);
                    patient.setDischargeDate(dischargeDate);
                    patient.setNotes(notes);

                    System.out.println("Personal ID: " + "\t\t" + patient.getPersonalId());
                    System.out.println("Last name: " + "\t\t\t" + patient.getLastName());
                    System.out.println("First name: " + "\t\t" + patient.getFirstName());
                    System.out.println("Room number: " + "\t\t" + patient.getRoomNr());
                    System.out.println("Doctor's ID: " + "\t\t" + patient.getDoctorsId());
                    System.out.println("Diagnosis: " + "\t\t\t" + patient.getDiagnosis());
                    System.out.println("Drugs assigned: " + "\t" + patient.getDrugsAssigned());
                    System.out.println("Registering date: " + "\t" + patient.getRegisteringDate());
                    System.out.println("Discharge date: " + "\t" + patient.getDischargeDate());
                    System.out.println("Notes: " + "\t\t\t\t" + patient.getNotes());
                }

            } catch (SQLException e){
                e.printStackTrace();
            }
            PatientController.edit(personalId);

        } else {
            System.out.println("The confirmation doesn't match the initial patient's ID. Please start again!");
        }
    }

    public static void edit(String personalId){
        System.out.println("Which data you wish to edit? Please choose an option: ");

        System.out.println("1. Personal ID");
        System.out.println("2. Last name");
        System.out.println("3. First name");
        System.out.println("4. Room number");
        System.out.println("5. The assigned doctor");
        System.out.println("6. Diagnosis");
        System.out.println("7. The assigned drugs");
        System.out.println("8. Notes");

        System.out.print("Select an option by entering it's number: ");
        int option = scanner.nextInt();

        switch(option) {
            case 1:
                System.out.print("Enter the new patient's ID: ");
                String newId = scanner.next();
                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE patients SET personal_id='" + newId + "'WHERE personal_id='" + personalId + "'");
                    ps.execute();
                    System.out.println("Successfully updated patient's ID.");

                } catch (SQLException e) {
                    System.out.println("Failed to update patient's ID. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.print("Enter the last name: ");
                String lastName = scanner.next();
                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE patients SET last_name='" + lastName + "'WHERE personal_id='" + personalId + "'");
                    ps.execute();
                    System.out.println("Successfully updated the patient's last name.");

                } catch (SQLException e) {
                    System.out.println("Failed to update patient's last name. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.print("Enter the first name: ");
                String firstName = scanner.next();
                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE patients SET first_name='" + firstName + "'WHERE personal_id='" + personalId + "'");
                    ps.execute();
                    System.out.println("Successfully updated the patient's first name.");

                } catch (SQLException e) {
                    System.out.println("Failed to update patient's first name. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 4:
                System.out.print("Enter the room number: ");
                int roomNr = scanner.nextInt();
                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE patients SET room_nr='" + roomNr + "'WHERE personal_id='" + personalId + "'");
                    ps.execute();
                    System.out.println("Successfully updated room number.");

                } catch (SQLException e) {
                    System.out.println("Failed to update room number. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 5:
                System.out.print("Enter the assigned doctor's ID: ");
                int doctorsId = scanner.nextInt();
                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE patients SET doctors_id='" + doctorsId + "'WHERE personal_id='" + personalId + "'");
                    ps.execute();
                    System.out.println("Successfully updated the assigned doctor.");

                } catch (SQLException e) {
                    System.out.println("Failed to update the assigned doctor. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 6:
                System.out.print("Describe the diagnosis: ");
                String diagnosis = scanner.next();
                diagnosis += scanner.nextLine();

                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE patients SET diagnosis='" + diagnosis + "'WHERE personal_id='" + personalId + "'");
                    ps.execute();
                    System.out.println("Successfully updated the patient's diagnosis.");

                } catch (SQLException e) {
                    System.out.println("Failed to update patient's diagnosis. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 7:
                System.out.print("Enter the prescribed drugs ID: ");
                int drugs = scanner.nextInt();
                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE patients SET drugs_assigned='" + drugs + "'WHERE personal_id='" + personalId + "'");
                    ps.execute();
                    System.out.println("Successfully added prescribed drugs.");

                } catch (SQLException e) {
                    System.out.println("Failed to add prescribed drugs. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 8:
                System.out.print("Add the notes: ");
                String notes = scanner.next();
                notes += scanner.nextLine();

                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE patients SET notes='" + notes + "'WHERE personal_id='" + personalId + "'");
                    ps.execute();
                    System.out.println("Successfully added the notes.");

                } catch (SQLException e) {
                    System.out.println("Failed to edit notes. Please try again!");
                    e.printStackTrace();
                }
                break;

            default:
                System.out.println("Invalid option. Please select another option.");
        }

        System.out.println("Do you wish to edit any other details? Enter 1 for yes or 2 for no: ");
        int answer = scanner.nextInt();

        if (answer==1){
            System.out.print("Please confirm the ID of the patient: ");
            String compareId = scanner.next();
            if (compareId.equals(personalId)) {
                PatientController.edit(personalId);
            } else {
                System.out.println("The confirmation doesn't match the initial patient's ID. Please start again!");
            }
        }else{
            System.out.println("You have finished editing the patient's data.");
        }
    }

    public static void dischargePatient() {
        System.out.print("Enter the patient's personal ID: ");
        String personalId = scanner.next();
        System.out.print("Enter the discharging date: ");
        String dischargeDate = scanner.next();

        try {
            ps = dbConnection.getConnection().prepareStatement("UPDATE patients SET discharge_date='" + dischargeDate + "'WHERE personal_id='" + personalId + "'");
            ps.execute();
            System.out.println("Successfully added discharging date.");
            System.out.print("The patient's data will be archived. Do you wish to continue? Enter 1 for yes or 2 for no: ");
            int answer = scanner.nextInt();

            if (answer == 1) {
                ps = dbConnection.getConnection().prepareStatement("INSERT INTO patient_archive SELECT * FROM patients WHERE personal_id='" + personalId + "'");
                ps.execute();
                ps = dbConnection.getConnection().prepareStatement("DELETE FROM patients WHERE personal_id='" + personalId + "'");
                ps.execute();

                System.out.println("Successfully archived patient's data.");
            } else {
                System.out.println("Patient's data was not archived. You can archive the data later.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to discharge and archive patient. Please try again!");
            e.printStackTrace();
        }
    }

    public static void archivePatient(){
        System.out.print("Enter the patient's personal ID: ");
        String personalId = scanner.next();

        try {
            ps = dbConnection.getConnection().prepareStatement("INSERT INTO patient_archive SELECT * FROM patients WHERE personal_id='" + personalId + "'");
            ps.execute();
            ps = dbConnection.getConnection().prepareStatement("DELETE FROM patients WHERE personal_id='" + personalId + "'");
            ps.execute();

            System.out.println("Successfully archived patient's data.");
        } catch (SQLException e) {
            System.out.println("Failed to archive patient's data. Please try again.");
            e.printStackTrace();
        }
    }

}
