package model;

import main.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Sensor open at door");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", e.getDescription());
        long expectedTime = d.getTime();
        long actualTime = e.getDate().getTime();

        // Calculate the tolerance dynamically based on the current system time
        long currentTime = System.currentTimeMillis();
        long tolerance = currentTime - expectedTime + 10;  // Adding 10 milliseconds as a buffer

        assertTrue(Math.abs(expectedTime - actualTime) <= tolerance, "Time values differ within tolerance");
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
    }

    @Test
    public void testEqualsWithDifferentClass() {
        // Given
        Event event = new Event("Test event"); // When
        boolean result = event.equals("Not an Event"); // Then
        assertFalse(result);
    }

    @Test
    public void testEqualsWithNull() { // Given
        Event event = new Event("Test event"); // When
        boolean result = event.equals(null); // Then
        assertFalse(result);
    }

    @Test
    public void testHashCode() { // Given
        String description = "Test event";
        Event event = new Event(description); // When
        int hashCode = event.hashCode(); // Then // Ensure that the hash code is consistent and follows the expected formula
        assertEquals(13 * event.getDate().hashCode() + description.hashCode(), hashCode);
    }
}
