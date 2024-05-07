package main;

import org.json.JSONArray;
import persistance.Writable;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Represents a patient having a name and an age
public class Patient implements Writable {
    private String name;
    private double age;
    List<String> symptoms;
    List<String> comorbidities;
    List<String> prescriptions;

    // EFFECT: Creates a Patient with a name and age
    public Patient(String name, double age) {
        this.name = name;
        this.age = age;
        this.symptoms = new ArrayList<>();
        this.comorbidities = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECT: adds symptom to patient's list of symptoms

    public void addSymptom(String symptom) {
        symptoms.add(symptom);
        EventLog.getInstance().logEvent(new Event("Symptom added to patient: " + this.getName() + ", Symptom: "
                + symptom));
    }


    //MODIFIES: this
    //EFFECT: adds comorbidities to patient
    public void addCormobidities(String comorbidity) {
        comorbidities.add(comorbidity);
        EventLog.getInstance().logEvent(new Event("Comorbidity added to patient: " + this.getName() + ", comorbidity: "
                + comorbidity));
    }

    //MODIFIES: this
    //EFFECT: adds prescription to patient
    public void addTreatment(String prescription) {
        prescriptions.add(prescription);
        EventLog.getInstance().logEvent(new Event("prescription added to patient: " + this.getName()
                + ", prescription: " + prescription));
    }


    //EFFECT: returns prescription of patient
    public List<String> getTreatment() {
        return prescriptions;
    }


    //EFFECT: returns patient symptom
    public List<String> getsypmtoms() {
        return symptoms;
    }


    //EFFECT: returns comorbidities
    public List<String> getComorbidities() {
        return comorbidities;
    }


    // EFFECTS: returns patients name
    public String getName() {
        return name;
    }

    // EFFECTS: returns patients age
    public double getAge() {
        return this.age;
    }

    // MODIFIES: this
    // EFFECTS: sets patients name
    public void setName(String name) {
        this.name = name;
    }

    //REQUIRES age>=0.0
    // MODIFIES: this
    // EFFECTS: sets patients age
    public void setAge(double age) {
        this.age = age;
    }

    //EFFECT: checks if a patient has a specific symptom
    public boolean hassymptom(String symptom) {
        return symptoms.contains(symptom);
    }


    @Override
    public JSONObject toJson() {
        JSONObject patientJson = new JSONObject();
        patientJson.put("name", name);
        patientJson.put("age", age);

        JSONArray symptomsArray = new JSONArray(symptoms);
        JSONArray comorbiditiesArray = new JSONArray(comorbidities);
        JSONArray prescriptionsArray = new JSONArray(prescriptions);

        patientJson.put("symptoms", symptomsArray);
        patientJson.put("prescriptions", prescriptionsArray);
        patientJson.put("comorbidities", comorbiditiesArray);

        return patientJson;
    }


}
