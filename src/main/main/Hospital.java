package main;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents a Hospital having a list of patients

public class Hospital implements Writable {
    List<Patient> patients;

    public Hospital() {
        patients = new ArrayList<>();
    }

    //Modifies: this
    //Effect: adds a patient to the list of patients.
    public void addPatient(Patient patient) {
        patients.add(patient);
        EventLog.getInstance().logEvent(new Event("Added Patient: " + patient.getName() + " " + patient.getAge()
                + "years"));
    }

    //Modifies: this
    //Effect: returns a patient using patient name from the list, null if can't find
    public Patient findPatientbyName(String name) {
        for (Patient patient : patients) {
            if (name.equalsIgnoreCase(patient.getName())) {
                return patient;
            }

        }
        return null;
    }

    //modifies: this
    //Effects: returns all patients
    public List<Patient> getAllpatients() {
        return patients;
    }


    //modifies: this
    //Effects: gets a list of patients with a given symptom
    public List<Patient> getPatientswithSymptom(String symptom) {
        List<Patient> names = new ArrayList<>();

        for (Patient patient : patients) {
            for (String s : patient.getsypmtoms()) {
                if (s.equalsIgnoreCase(symptom)) {
                    names.add(patient);
                }
            }

        }
        return names;
    }

    //Effects: gets a list of patients with a given comorbidity
    public List<Patient> getPatientwithComorbidity(String comorbidity) {
        List<Patient> comorbidities = new ArrayList<>();
        for (Patient patient : patients) {
            for (String c : patient.getComorbidities()) {
                if (c.equalsIgnoreCase(comorbidity)) {
                    comorbidities.add(patient);
                }
            }
        }
        return comorbidities;
    }

    //Effect: Adds treatment to patient by symptom
    public void addTreatmentbySymptom(String prescription, String symptom) {
        for (Patient patient : patients) {
            if (patient.hassymptom(symptom)) {
                patient.addTreatment(prescription);
            }
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject hospitalJson = new JSONObject();
        hospitalJson.put("patients", patientstoJson());

        return hospitalJson;
    }

    // EFFECTS: returns patients in this Hospital as a JSON array
    private JSONArray patientstoJson() {
        JSONArray patientsArray = new JSONArray();

        for (Patient p : patients) {
            patientsArray.put(p.toJson());
        }

        return patientsArray;
    }
}

