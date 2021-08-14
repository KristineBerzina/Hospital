package com.company.Menu;

import com.company.Objects.Drug;
import com.company.actions.DoctorController;
import com.company.actions.DrugController;
import com.company.actions.PatientController;

import java.util.Scanner;

public class Menu {

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("What would you like to do now?");
        System.out.println(" ");
        System.out.println("1. Get patient data");
        System.out.println("2. Get patient history from archive");
        System.out.println("3. Add new patient");
        System.out.println("4. Edit patient's details");
        System.out.println("5. Discharge patient and archive patient's data");
        System.out.println("6. Archive patient's data");
        System.out.println("7. Get doctor's data");
        System.out.println("8. Add new doctor");
        System.out.println("9. Edit doctor's details");
        System.out.println("10. Delete doctor's data");
        System.out.println("11. Get drug's data");
        System.out.println("12. Add new drugs");
        System.out.println("13. Edit drug description");
        System.out.println("14. Delete drugs from database");
        System.out.println("15. Finish and close the program");
        System.out.println(" ");

        System.out.print("Select an option by entering it's number: ");
        int option = scanner.nextInt();

        switch(option) {
            case 1:
                PatientController.getPatientData();
                Menu.menu();
                break;
            case 2:
                PatientController.getPatientDataFromArchive();
                Menu.menu();
                break;
            case 3:
                PatientController.addNewPatient();
                Menu.menu();
                break;
            case 4:
                PatientController.editPatientData();
                Menu.menu();
                break;
            case 5:
                PatientController.dischargePatient();
                Menu.menu();
                break;
            case 6:
                PatientController.archivePatient();
                Menu.menu();
                break;
            case 7:
                DoctorController.getDoctorById();
                Menu.menu();
                break;
            case 8:
                DoctorController.addNewDoctor();
                Menu.menu();
                break;
            case 9:
                DoctorController.editDoctorData();
                Menu.menu();
                break;
            case 10:
                DoctorController.deleteDoctor();
                Menu.menu();
                break;
            case 11:
                DrugController.getDrugsById();
                Menu.menu();
                break;
            case 12:
                DrugController.addNewDrugs();
                Menu.menu();
                break;
            case 13:
                DrugController.editDrugData();
                Menu.menu();
                break;
            case 14:
                DrugController.deleteDrugs();
                Menu.menu();
                break;
            case 15:
                System.out.println("You have finished your work session. Please close the program");
                break;
            default:
                System.out.println("Invalid option. Please select another option.");
                Menu.menu();
        }
    }

}
