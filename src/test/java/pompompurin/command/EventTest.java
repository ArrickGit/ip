package pompompurin.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import pompompurin.task.Event;

public class EventTest {
    @Test
    public void testEventToString() {
        LocalDate from = LocalDate.parse("2019-10-22");
        LocalDate to = LocalDate.parse("2020-10-22");

        Event event = new Event("go home", from, to);
        String expectedDisplay = "[E][ ] go home (from: Oct 22 2019 to: Oct 22 2020)";
        assertEquals(expectedDisplay, event.toString());
    }

    @Test
    public void toFileString_newEvent_correctFormat() {
        LocalDate from = LocalDate.parse("2019-10-22");
        LocalDate to = LocalDate.parse("2020-10-22");
        Event event = new Event("conference", from, to);
        String expected = "E | 0 | conference | 2019-10-22 | 2020-10-22";
        assertEquals(expected, event.toFileString());
    }

    @Test
    public void toString_markedEvent_correctFormat() {
        LocalDate from = LocalDate.parse("2019-10-22");
        LocalDate to = LocalDate.parse("2020-10-22");
        Event event = new Event("meeting", from, to);
        event.markAsDone();
        String expected = "[E][X] meeting (from: Oct 22 2019 to: Oct 22 2020)";
        assertEquals(expected, event.toString());
    }
}

