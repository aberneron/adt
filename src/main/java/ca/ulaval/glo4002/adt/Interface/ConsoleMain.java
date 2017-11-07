package ca.ulaval.glo4002.adt.Interface;

import ca.ulaval.glo4002.adt.Application.Dtos.PatientDTO;
import ca.ulaval.glo4002.adt.Application.PatientService;
import ca.ulaval.glo4002.adt.Domain.Patient;

import java.util.Collection;
import java.util.Scanner;

public class ConsoleMain {
    private static Scanner scanner;
    private static PatientService patientService;

    public static void main(String[] args) {
        patientService = new PatientService();
        patientService.fillDatabase();

        try (Scanner scanner = new Scanner(System.in)) {
            ConsoleMain.scanner = scanner;
            startCommandPromptLoop();
        }

        System.exit(0);
    }

    private static void startCommandPromptLoop() {
        System.out.println("Welcome to GLO-4002's ADT!");

        boolean quit = false;

        while (!quit) {
            String option = pickOptionFromMenu();
            System.out.println("\n");
            switch (option) {
                case "1": {
                    displayPatientList();
                    break;
                }
                case "2": {
                    movePatient();
                    break;
                }
                case "3": {
                    dischargePatient();
                    break;
                }
                case "q": {
                    quit = true;
                    break;
                }
                default: {
                    System.out.println("Invalid option");
                }
            }
        }
        System.out.println("Bye bye");
    }

    private static String pickOptionFromMenu() {
        System.out.println("\n");
        System.out.println("-----------------");
        System.out.println("What do you want to do?");
        System.out.println("1) List patients");
        System.out.println("2) Move a patient");
        System.out.println("3) Discharge a patient");

        System.out.print("Choice (or q tp quit) : ");
        return scanner.nextLine();
    }

    private static void displayPatientList() {
        Collection<PatientDTO> patients = patientService.retrievePatients();

        for (PatientDTO patient : patients) {
            System.out.println(String.format("%d : %s (status = %s, department = %s)", patient.getId(),
                    patient.getName(), patient.getStatus(), patient.getDepartment()));
        }
    }

    private static void movePatient() {
        displayPatientSelectionMessage();

        System.out.print("Patient ID to move : ");
        int patientId = Integer.parseInt(scanner.nextLine());

        System.out.print("New department : ");
        String newDepartment = scanner.nextLine();

        patientService.movePatient(patientId, newDepartment);
    }

    private static void dischargePatient() {
        displayPatientSelectionMessage();

        System.out.print("Patient ID to discharge : ");
        int patientId = Integer.parseInt(scanner.nextLine());

        patientService.dischargePatient(patientId);
    }

    private static void displayPatientSelectionMessage() {
        System.out.println("First, you must select a patient : ");
        displayPatientList();
    }
}
