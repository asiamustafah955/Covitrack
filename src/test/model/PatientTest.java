package model;

import main.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    Patient P1;
    Patient P2;
    Patient P3;

    @BeforeEach

     void runBefore(){
        P1 = new Patient("Mary", 65.0);
        P2 = new Patient("Shakira", 20.5);
        P3 = new Patient("Fatuma", 40.0);
        P1.addSymptom("Fever");
        P1.addSymptom("Headache");
        P1.addSymptom("Joint Pain");
        P2.addCormobidities("Hypertention");
        P3.addTreatment("Paracetamol");
        P1.setName("Anna");




    }

    @Test
     void testConstructor(){
        assertEquals("Anna", P1.getName());
        assertEquals("Shakira", P2.getName());
        assertEquals("Fatuma", P3.getName());
        assertEquals( "Hypertention", P2.getComorbidities().get(0));
        assertEquals( "Paracetamol", P3.getTreatment().get(0));
        assertEquals( 65.0, P1.getAge());
        P2.setAge(50.2);
        assertEquals(50.2, P2.getAge());


    }

    @Test
    void testAddsinglesymptoms(){
        List<String> symptoms = new ArrayList<>();

        symptoms.add("Fever");

        assertEquals( 1, symptoms.size());
        assertEquals("Fever", symptoms.get(0));

    }

    @Test
    void testAddmultiplesymptoms(){
        List<String> symptoms = new ArrayList<>();

        symptoms.add("Fever");
        symptoms.add("Headache");

        assertEquals( 2, symptoms.size());
        assertEquals("Fever", symptoms.get(0));
        assertEquals("Headache", symptoms.get(1));

    }

    @Test
    void testAddsinglecomorbidity(){
        List<String> comorbidity = new ArrayList<>();

        comorbidity.add("Hypertension");

        assertEquals( 1, comorbidity.size());
        assertEquals("Hypertension", comorbidity.get(0));

    }

    @Test
    void testAddmultiplecomorbidities(){
        List<String> comorbidities = new ArrayList<>();

        comorbidities.add("Hypertension");
        comorbidities.add("Cardiac failure");

        assertEquals( 2, comorbidities.size());
        assertEquals("Hypertension", comorbidities.get(0));
        assertEquals("Cardiac failure", comorbidities.get(1));

    }

    @Test
    void testAddsingletreatment(){
        List<String> treatment = new ArrayList<>();

        treatment.add("Zinc");

        assertEquals( 1, treatment.size());
        assertEquals("Zinc", treatment.get(0));

    }

    @Test
    void testAddmultipletreatment(){
        List<String> treatments = new ArrayList<>();

        treatments.add("Zinc");
        treatments.add("Paxlovid");

        assertEquals( 2, treatments.size());
        assertEquals("Zinc", treatments.get(0));
        assertEquals("Paxlovid", treatments.get(1));

    }




    }
