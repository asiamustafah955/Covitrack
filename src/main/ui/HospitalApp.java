package ui;

import main.Hospital;
import main.Patient;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Represents the Hospital application
public class HospitalApp {
    private static final String JSON_STORE = "./data/test.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Scanner scanner;
    private boolean keepgoing;
    private Hospital patientlist;


    // EFFECTS: constructs Hospital and runs application
    public HospitalApp() throws FileNotFoundException {
        scanner = new Scanner(System.in);
        patientlist = new Hospital();
        keepgoing = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runHospital();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runHospital() {
        while (keepgoing) {
            printMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    addSymptomtoPatient();
                    break;
                case 3:
                    addComorbidity();
                    break;
                case 4:
                    addTreatmentmethod();
                    break;
                default:
                    switchHelper(choice);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void switchHelper(int choice) {
        switch (choice) {
            case 5:
                findPatientByname();
                break;
            case 6:
                findPatientwithSymptom();
                break;
            case 7:
                allPatients();
                break;
            case 8:
                saveHospital();
                break;
            case 9:
                loadHospital();
                break;
            case 10:
                exitMethod();
                break;

            default:
                System.out.println("Invalid choice, Please try again!");
        }
    }

    //EFFECTS: prints menu on console
    private void printMenu() {
        System.out.println("**** COVID Patient Tracking System ****");
        System.out.println("1. Add Patient");
        System.out.println("2. Add Symptom to Patient");
        System.out.println("3. Add comorbidity to Patient");
        System.out.println("4. Add Treatment to Patient");
        System.out.println("5. Find Patient by Name");
        System.out.println("6. Find Patients with Symptom");
        System.out.println("7. Get All Patients");
        System.out.println("8. Save Patients to file");
        System.out.println("9. load patients from file");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    //EFFECTS: Creates an exit case 10
    private void exitMethod() {
        System.out.println("Goodbye!");
        scanner.close();
        System.exit(0);
    }

    //EFFECTS: Prints all patients to the console
    private void allPatients() {
        System.out.println("All patients in the system include: ");
        List<Patient> allPatients = patientlist.getAllpatients();
        if (allPatients != null) {
            for (Patient p : allPatients) {
                System.out.println("-" + p.getName() + " " + p.getAge());
            }
        } else {
            System.out.println("No patients in the system");
        }
    }

    //EFFECTS: finds patient by name
    private void findPatientwithSymptom() {
        System.out.println("Enter symptom: ");
        String symptoms = scanner.nextLine();
        List<Patient> patientwithSymptom = patientlist.getPatientswithSymptom(symptoms);
        if (patientwithSymptom != null) {
            System.out.println("Patients include: ");
            for (Patient p : patientwithSymptom) {
                System.out.println("-" + p.getName() + " " + p.getAge());
            }
        } else {
            System.out.println("No Patient found");
        }
    }

    //EFFECTS: Adds comorbidity to comorbidity list.
    private void addComorbidity() {
        System.out.println("Enter patient name: ");
        String cname = scanner.nextLine();
        System.out.println("Enter comorbidity: ");
        String comorbidity = scanner.nextLine();
        patientlist.findPatientbyName(cname).addCormobidities(comorbidity);
        System.out.println("Comorbidities successfully added!");
    }


    //EFFECTS: Adds Patient to the list from the console
    private void addPatient() {
        System.out.println("Enter Patient's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Patient's age: ");
        double age = scanner.nextInt();
        Patient patient = new Patient(name, age);
        patientlist.addPatient(patient);
        System.out.println("Patient successfully added!");
    }


    //EFFECTS: adds treatment to the patient
    private void addTreatmentmethod() {
        System.out.println("Enter Patient name: ");
        String tname = scanner.nextLine();
        System.out.println("Enter prescription name: ");
        String prescription = scanner.nextLine();
        patientlist.findPatientbyName(tname).addTreatment(prescription);
        System.out.println("Prescription fully added!");
    }

    //EFFECTS: finds patient by name and returns it else returns Patient not found
    private void findPatientByname() {
        System.out.println("Enter Patient's name: ");
        String fname = scanner.nextLine();
        Patient foundPatient = patientlist.findPatientbyName(fname);
        if (foundPatient != null) {
            System.out.println(foundPatient.getName() + " " + foundPatient.getAge());
        } else {
            System.out.println("Patient not found");
        }
    }

    //Adds symptom to existing patient
    private void addSymptomtoPatient() {
        System.out.println("Enter patient name: ");
        String pname = scanner.nextLine();
        System.out.println("Enter symptom: ");
        String symptom = scanner.nextLine();
        patientlist.findPatientbyName(pname).addSymptom(symptom);
        System.out.println("Symptom successfully added!");

    }

    // EFFECTS: saves the patientList to file
    private void saveHospital() {
        try {
            jsonWriter.open();
            jsonWriter.write(patientlist);
            jsonWriter.close();
            System.out.println("Saved patient to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads patientList from file
    private void loadHospital() {
        try {
            patientlist = jsonReader.read();
            System.out.println("Loaded patients from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}









