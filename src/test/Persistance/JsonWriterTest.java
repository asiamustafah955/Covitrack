package Persistance;

import main.Hospital;
import main.Patient;
import org.junit.Test;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

public class JsonWriterTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            Hospital hospital = new Hospital();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyHospital() {
        try {
            Hospital hospital = new Hospital();
            JsonWriter writer = new JsonWriter("./data/test2.json");
            writer.open();
            writer.write(hospital);
            writer.close();
//
//            JsonReader reader = new JsonReader("./data/test.json");
//            hospital = reader.read();
//            assertEquals(null, hospital.getAllpatients());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    public void testWriterGeneralHospital() {
        try {
            Hospital hospital = new Hospital();
            Patient p1 = new Patient("Asia", 21.0);
            Patient p2 = new Patient("Tiana", 18.0);
            p1.addCormobidities("hypertension");
            p1.addTreatment("diazepam");
            p1.addSymptom("headache");
            p2.addSymptom("fever");
            p2.addCormobidities("Kidney Failure");
            p2.addTreatment("Acetaminopen");
            hospital.addPatient(p1);
            hospital.addPatient(p2);
            JsonWriter writer = new JsonWriter("./data/test.json");
            writer.open();
            writer.write(hospital);
            writer.close();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testHospitalGeneral2() throws IOException {
        JsonReader reader = new JsonReader("./data/test.json");
        Hospital hospital = reader.read();
        List<Patient> patients = hospital.getAllpatients();
        assertEquals(2, patients.size());
        Patient hospital1 = hospital.getAllpatients().get(0);
        assertEquals("Asia", hospital1.getName());
        assertEquals(21.0, hospital1.getAge());
        assertEquals("headache", hospital1.getsypmtoms().get(0));
        assertEquals("hypertension", hospital1.getComorbidities().get(0));
        assertEquals("diazepam", hospital1.getTreatment().get(0));
        Patient hospital2 = hospital.getAllpatients().get(1);
        assertEquals("Tiana", hospital2.getName());
        assertEquals(18.0, hospital2.getAge());
        assertEquals("fever", hospital2.getsypmtoms().get(0));
        assertEquals("Kidney Failure", hospital2.getComorbidities().get(0));
        assertEquals("Acetaminopen", hospital2.getTreatment().get(0));
    }


}

