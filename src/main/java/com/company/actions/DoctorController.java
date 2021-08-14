package com.company.actions;

import com.company.Objects.Doctor;
import com.company.connectToDB.dbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DoctorController {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Scanner scanner = new Scanner(System.in);

    public static Doctor getDoctorById() {
        System.out.print("Enter the ID of the doctor: ");
        int id = scanner.nextInt();

        Doctor doctor = new Doctor();

        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM doctors WHERE id=" + id);
            rs = ps.executeQuery();

            int doctorId;
            String lastName, firstName, speciality;

            while (rs.next()) {
                doctorId = rs.getInt("id");
                lastName = rs.getString("last_name");
                firstName = rs.getString("first_name");
                speciality = rs.getString("speciality");

                doctor.setId(doctorId);
                doctor.setLastName(lastName);
                doctor.setFirstName(firstName);
                doctor.setSpeciality(speciality);

                System.out.println("Doctor's ID: " + "\t\t\t" + doctor.getId());
                System.out.println("Last name: " + "\t\t\t\t" + doctor.getLastName());
                System.out.println("First name: " + "\t\t\t" + doctor.getFirstName());
                System.out.println("Speciality: " + "\t\t\t" + doctor.getSpeciality());
            }

            return doctor;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addNewDoctor() {
        System.out.print("Enter the doctor's ID number: ");
        String id = scanner.next();
        System.out.print("Enter the doctor's last name: ");
        String lastName = scanner.next();
        System.out.print("Enter the doctor's first name: ");
        String firstName = scanner.next();
        System.out.print("Enter the doctor's speciality: ");
        String speciality = scanner.next();
        speciality += scanner.nextLine();

        try {
            ps = dbConnection.getConnection().prepareStatement("INSERT INTO doctors(id, last_name, first_name, speciality) " +
                    "VALUES(" + id + ", '" + lastName + "', '" + firstName + "', '" + speciality + "')");
            ps.execute();
            System.out.println("Successfully added doctor's data.");

        } catch (SQLException e) {
            System.out.println("Failed to add doctor's data. Please try again!");
            e.printStackTrace();
        }

        System.out.println("Do you wish to enter another doctor's data? Print 1 for yes or 2 for no: ");
        int answer = scanner.nextInt();
        if (answer == 1) {
            DoctorController.addNewDoctor();
        }else {
            System.out.println("You have finished inputting the data.");
        }
    }

    public static void editDoctorData() {
        System.out.print("Enter the ID of the doctor whose details you wish to edit: ");
        int id = scanner.nextInt();

        System.out.print("Please confirm the ID of the doctor: ");
        int compareId = scanner.nextInt();

        if (compareId == id) {
            DoctorController.edit(id);

        } else {
            System.out.println("The confirmation doesn't match the initial doctor's ID. Please start again!");
        }
    }

    public static void edit(int id){
        System.out.println("Which data you wish to edit? Please choose an option: ");

        System.out.println("1. ID");
        System.out.println("2. Last name");
        System.out.println("3. First name");
        System.out.println("4. Speciality");

        System.out.print("Select an option by entering it's number: ");
        int option = scanner.nextInt();

        switch(option) {
            case 1:
                System.out.print("Enter the new doctor's ID: ");
                int newId = scanner.nextInt();
                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE doctors SET id=" + newId + "WHERE id=" + id);
                    ps.execute();
                    System.out.println("Successfully updated doctor's ID.");

                } catch (SQLException e) {
                    System.out.println("Failed to update doctor's ID. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.print("Enter the last name: ");
                String lastName = scanner.next();
                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE doctors SET last_name='" + lastName + "'WHERE id=" + id);
                    ps.execute();
                    System.out.println("Successfully updated the doctor's last name.");

                } catch (SQLException e) {
                    System.out.println("Failed to update doctor's last name. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.print("Enter the first name: ");
                String firstName = scanner.next();
                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE doctors SET first_name='" + firstName + "'WHERE id=" + id);
                    ps.execute();
                    System.out.println("Successfully updated the doctor's first name.");

                } catch (SQLException e) {
                    System.out.println("Failed to update doctor's first name. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 4:
                System.out.print("Enter the doctor's speciality: ");
                String speciality = scanner.next();
                speciality += scanner.nextLine();

                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE doctors SET speciality='" + speciality + "'WHERE id=" + id);
                    ps.execute();
                    System.out.println("Successfully updated the doctor's speciality.");

                } catch (SQLException e) {
                    System.out.println("Failed to update doctor's speciality. Please try again!");
                    e.printStackTrace();
                }
                break;

            default:
                System.out.println("Invalid option. Please select another option.");
        }

        System.out.println("Do you wish to edit any other details? Enter 1 for yes or 2 for no: ");
        int answer = scanner.nextInt();

        if (answer==1){
            System.out.print("Please confirm the ID of the doctor: ");
            int compareId = scanner.nextInt();
            if (compareId == id) {
                DoctorController.edit(id);
            } else {
                System.out.println("The confirmation doesn't match the initial doctor's ID. Please start again!");
            }
        }else{
            System.out.println("You have finished editing the doctor's data.");
        }

    }

    public static void deleteDoctor() {
        System.out.print("Enter the ID of the doctor you wish to remove: ");
        int id = scanner.nextInt();

        try {
            ps = dbConnection.getConnection().prepareStatement("DELETE FROM doctors WHERE id=" + id);
            ps.execute();

            System.out.println("Doctor removed successfully");

        } catch (SQLException e) {
            System.out.println("Could not remove doctor's data. Try again!");
            e.printStackTrace();
        }

    }

}
