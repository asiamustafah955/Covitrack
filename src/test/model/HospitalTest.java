package model;

import main.Hospital;
import main.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HospitalTest {
    private Hospital testHospital;

    @BeforeEach
    void runBefore() {
        testHospital = new Hospital();

    }

    @Test
    void testConstructor() {
        assertTrue(testHospital.getAllpatients().isEmpty());

    }
    @Test

    void testaddSinglePatient(){
        testHospital.addPatient(new Patient("Sia", 23.0));
        assertEquals("Sia", testHospital.getAllpatients().get(0).getName());
        assertEquals(23.0, testHospital.getAllpatients().get(0).getAge());
        assertEquals(1, testHospital.getAllpatients().size());
    }
    @Test

    void testaddMultiplePatient(){
        testHospital.addPatient(new Patient("Sia", 23.0));
        testHospital.addPatient(new Patient("Mary", 55.2));
        assertEquals("Sia", testHospital.getAllpatients().get(0).getName());
        assertEquals("Mary", testHospital.getAllpatients().get(1).getName());
        assertEquals(23.0, testHospital.getAllpatients().get(0).getAge());
        assertEquals(55.2, testHospital.getAllpatients().get(1).getAge());
        assertEquals(2, testHospital.getAllpatients().size());
    }

    @Test

    void testfindPatientByname(){
        Patient P1 = new Patient("Sia", 23.0);
        Patient P2 = new Patient("Mary", 55.2);
        testHospital.addPatient(P1);
        testHospital.addPatient(P2);
        assertEquals(P2, testHospital.findPatientbyName("Mary"));
        assertEquals(P1, testHospital.findPatientbyName("Sia"));
        assertNull(testHospital.findPatientbyName("Musi"));
    }

    @Test

    void testgetPatientwithSymptom(){
        Patient P1 = new Patient("Sia", 23.0);
        P1.addSymptom("Fever");
        Patient P2 = new Patient("Mary", 55.2);
        P2.addSymptom("headache");

        testHospital.addPatient(P1);
        testHospital.addPatient(P2);


        List<Patient> PatientlistwithFever = testHospital.getPatientswithSymptom("Fever");
        List<Patient> PatientlistwithCough = testHospital.getPatientswithSymptom("headache");
        List<Patient> PatientlistWithChestPain = testHospital.getPatientswithSymptom("Chest Pain");

        assertEquals(1, PatientlistwithFever.size());
        assertEquals(P1, PatientlistwithFever.get(0));
        assertEquals(P2, PatientlistwithCough.get(0));
        assertTrue(PatientlistWithChestPain.isEmpty());
        assertEquals("Sia", PatientlistwithFever.get(0).getName());
        assertEquals(55.2, PatientlistwithCough.get(0).getAge());

    }

    @Test

    void testgetPatientwithComorbidity(){
        Patient P1 = new Patient("Sia", 23.0);
        P1.addCormobidities("Heartpain");
        Patient P2 = new Patient("Mary", 55.2);
        P2.addCormobidities("Hypertension");

        testHospital.addPatient(P1);
        testHospital.addPatient(P2);


        List<Patient> PatientListWithHypertension = testHospital.getPatientwithComorbidity("Hypertension");
        List<Patient> PatientlistChestPain = testHospital.getPatientwithComorbidity("Heartpain");
        List<Patient> PatientlistWithKidneyfailure = testHospital.getPatientwithComorbidity("Kidney failure");

        assertEquals(1, PatientlistChestPain.size());
        assertEquals(P1, PatientlistChestPain.get(0));
        assertEquals(P2, PatientListWithHypertension.get(0));
        assertTrue(PatientlistWithKidneyfailure.isEmpty());
        assertEquals("Sia", PatientlistChestPain.get(0).getName());
        assertEquals(55.2, PatientListWithHypertension.get(0).getAge());

    }

    @Test

    void testaddTreatmentBySymptom(){
        Patient P1 = new Patient("Sia", 23.0);
        P1.addSymptom("Fever");
        Patient P2 = new Patient("Mary", 55.2);
        P2.addSymptom("headache");

        testHospital.addPatient(P1);
        testHospital.addPatient(P2);
        testHospital.addTreatmentbySymptom("Paracetamol", "headache");
        testHospital.addTreatmentbySymptom("Aspirin", "Fever");

        assertTrue(P2.getTreatment().contains("Paracetamol"));
        assertTrue(P1.getTreatment().contains("Aspirin"));

    }


}