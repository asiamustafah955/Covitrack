package persistance;

import main.Hospital;
import main.Patient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads Hospital from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //Reads Hospital from file and returns it
    //throws IOException if file cannot be read

    public Hospital read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHospital(jsonObject);

    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Hospital from JSON object and returns it
    private Hospital parseHospital(JSONObject jsonObject) {
        Hospital hospital = new Hospital();
        JSONArray patientArray = jsonObject.getJSONArray("patients");

        for (Object p : patientArray) {
            JSONObject nextPatient = (JSONObject) p;

            Patient patient = createPatient(nextPatient);

            hospital.addPatient(patient);

        }
        return hospital;
    }
    // MODIFIES: hospital
    // EFFECTS: parses Patient from JSON object and adds them to Hospital

    private Patient createPatient(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double age = jsonObject.getDouble("age");
        JSONArray symptomsArray = jsonObject.getJSONArray("symptoms");
        JSONArray comorbiditiesArray = jsonObject.getJSONArray("comorbidities");
        JSONArray prescriptionArray = jsonObject.getJSONArray("prescriptions");

        Patient patient = new Patient(name, age);

        for (Object symptom : symptomsArray) {
            patient.addSymptom(symptom.toString());
        }

        for (Object comorbidity : comorbiditiesArray) {
            patient.addCormobidities(comorbidity.toString());
        }

        for (Object prescription : prescriptionArray) {
            patient.addTreatment(prescription.toString());
        }
        return patient;


    }


}
