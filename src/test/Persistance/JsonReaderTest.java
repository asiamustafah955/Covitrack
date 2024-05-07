package Persistance;
import main.Hospital;
import org.junit.Test;
import persistance.JsonReader;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

public class JsonReaderTest {
    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Hospital hospital = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyHospital() {
        JsonReader reader = new JsonReader("./data/test2.json");
        try {
            Hospital hospital = reader.read();
            assertEquals(0, hospital.getAllpatients().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralHospital() {
        JsonReader reader = new JsonReader("./data/test.json");
        try {
            Hospital hospital = reader.read();
            assertEquals(2, hospital.getAllpatients().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderHospital1() {
        JsonReader reader = new JsonReader("./data/test.json");
        try {
            Hospital hospital = reader.read();
            assertEquals("Asia", hospital.getAllpatients().get(0).getName());
            assertEquals(21.0, hospital.getAllpatients().get(0).getAge());
            assertEquals("headache", hospital.getAllpatients().get(0).getsypmtoms().get(0));
            assertEquals("hypertension", hospital.getAllpatients().get(0).getComorbidities().get(0));
            assertEquals("diazepam", hospital.getAllpatients().get(0).getTreatment().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test

    public void testReaderHospital2() {
        JsonReader reader = new JsonReader("./data/test.json");
        try {
            Hospital hospital = reader.read();
            assertEquals("Tiana", hospital.getAllpatients().get(1).getName());
            assertEquals(18.0, hospital.getAllpatients().get(1).getAge());
            assertEquals("fever", hospital.getAllpatients().get(1).getsypmtoms().get(0));
            assertEquals("Kidney Failure", hospital.getAllpatients().get(1).getComorbidities().get(0));
            assertEquals("Acetaminopen", hospital.getAllpatients().get(1).getTreatment().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}

