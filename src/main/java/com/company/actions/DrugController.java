package com.company.actions;

import com.company.Objects.Drug;
import com.company.connectToDB.dbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DrugController {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Scanner scanner = new Scanner(System.in);

    public static Drug getDrugsById() {
        System.out.print("Enter the ID of the drug: ");
        int id = scanner.nextInt();

        Drug drug = new Drug();

        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM drugs WHERE id=" + id);
            rs = ps.executeQuery();

            int drugId, prescription;
            String name, description, sideEffects;

            while (rs.next()) {
                drugId = rs.getInt("id");
                name = rs.getString("name");
                description = rs.getString("description");
                sideEffects = rs.getString("side_effects");
                prescription = rs.getInt("prescription_needed");

                drug.setId(drugId);
                drug.setName(name);
                drug.setDescription(description);
                drug.setSideEffects(sideEffects);
                drug.setPrescription(prescription);

                System.out.println("Drug ID: " + "\t\t\t\t" + drug.getId());
                System.out.println("Drug name: " + "\t\t\t\t" + drug.getName());
                System.out.println("Description: " + "\t\t\t" + drug.getDescription());
                System.out.println("Side effects: " + "\t\t\t" + drug.getSideEffects());
                System.out.println("Prescription needed: " + "\t" + drug.getPrescription());
            }

            return drug;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addNewDrugs() {
        System.out.print("Enter the drug's ID number: ");
        int id = scanner.nextInt();
        System.out.print("Enter the drug's name: ");
        String name = scanner.next();
        System.out.print("Enter the drug's description: ");
        String description = scanner.next();
        description += scanner.nextLine();
        System.out.print("Enter the drug's side effects: ");
        String sideEffects = scanner.next();
        sideEffects += scanner.nextLine();
        System.out.println("Please confirm if these drugs need prescription! Type 0 if no or 1 if yes.");
        int prescription = scanner.nextInt();

        try {
            ps = dbConnection.getConnection().prepareStatement("INSERT INTO drugs(id, name, description, side_effects, prescription_needed) " +
                    "VALUES(" + id + ", '" + name + "', '" + description + "', '" + sideEffects + "', " + prescription + ")");
            ps.execute();
            System.out.println("Successfully added data in drug database.");

        } catch (SQLException e) {
            System.out.println("Failed to add data. Please try again!");
            e.printStackTrace();
        }

        System.out.println("Do you wish to enter information about any other drugs? Print 1 for yes or 2 for no: ");
        int answer = scanner.nextInt();
        if (answer == 1) {
            DrugController.addNewDrugs();
        }else {
            System.out.println("You have finished inputting the data.");
        }
    }

    public static void editDrugData() {
        System.out.print("Enter the ID of the drug which you wish to edit: ");
        int id = scanner.nextInt();

        System.out.print("Please confirm the ID of the drug: ");
        int compareId = scanner.nextInt();

        if (compareId == id) {
            DrugController.edit(id);

        } else {
            System.out.println("The confirmation doesn't match the initial drug's ID. Please start again!");
        }
    }

    public static void edit(int id){
        System.out.println("Which data you wish to edit? Please choose an option: ");

        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Description");
        System.out.println("4. Side effects");
        System.out.println("5. The need for prescription");

        System.out.print("Select an option by entering it's number: ");
        int option = scanner.nextInt();

        switch(option) {
            case 1:
                System.out.print("Enter the new drug ID: ");
                int newId = scanner.nextInt();
                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE drugs SET id=" + newId + "WHERE id=" + id);
                    ps.execute();
                    System.out.println("Successfully updated drug's ID.");

                } catch (SQLException e) {
                    System.out.println("Failed to update drug's ID. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.print("Enter the edited name: ");
                String name = scanner.next();
                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE drugs SET name='" + name + "'WHERE id=" + id);
                    ps.execute();
                    System.out.println("Successfully updated the drug's name.");

                } catch (SQLException e) {
                    System.out.println("Failed to update drug's name. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.print("Enter the drug description: ");
                String description = scanner.next();
                description += scanner.nextLine();
                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE drugs SET description='" + description + "'WHERE id=" + id);
                    ps.execute();
                    System.out.println("Successfully updated the drug's description.");

                } catch (SQLException e) {
                    System.out.println("Failed to update drug's description. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 4:
                System.out.print("Enter the drug's side effects: ");
                String sideEffects = scanner.next();
                sideEffects += scanner.nextLine();

                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE drugs SET side_effects='" + sideEffects + "'WHERE id=" + id);
                    ps.execute();
                    System.out.println("Successfully updated the drug's side effects.");

                } catch (SQLException e) {
                    System.out.println("Failed to update drug's side effects. Please try again!");
                    e.printStackTrace();
                }
                break;
            case 5:
                System.out.print("Please confirm if these drugs need prescription! Type 0 if no or 1 if yes. ");
                int prescription = scanner.nextInt();
                try {
                    ps = dbConnection.getConnection().prepareStatement("UPDATE drugs SET prescription_needed='" + prescription + "'WHERE id=" + id);
                    ps.execute();
                    System.out.println("Successfully updated prescription information.");

                } catch (SQLException e) {
                    System.out.println("Failed to update prescription information. Please try again!");
                    e.printStackTrace();
                }
                break;

            default:
                System.out.println("Invalid option. Please select another option.");
        }

        System.out.println("Do you wish to edit any other details? Enter 1 for yes or 2 for no: ");
        int answer = scanner.nextInt();

        if (answer==1){
            System.out.print("Please confirm the ID of the drugs: ");
            int compareId = scanner.nextInt();
            if (compareId == id) {
                DrugController.edit(id);
            } else {
                System.out.println("The confirmation doesn't match the initial drug's ID. Please start again!");
            }
        }else{
            System.out.println("You have finished editing the drug data.");
        }

    }

    public static void deleteDrugs() {
        System.out.print("Enter the ID of the drug you wish to remove: ");
        int id = scanner.nextInt();

        try {
            ps = dbConnection.getConnection().prepareStatement("DELETE FROM drugs WHERE id=" + id);
            ps.execute();

            System.out.println("Drugs removed successfully");

        } catch (SQLException e) {
            System.out.println("Could not remove drug's data. Try again!");
            e.printStackTrace();
        }

    }

}
