package pompompurin.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadLineTest {

    @Test
    public void toString_newDeadline_correctFormat() {
        LocalDate date = LocalDate.parse("2026-12-25");
        DeadLine deadline = new DeadLine("submit report", date);
        String expected = "[D][ ] submit report (by: 25 Dec 2026)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toFileString_newDeadline_correctFormat() {
        LocalDate date = LocalDate.parse("2026-12-25");
        DeadLine deadline = new DeadLine("submit report", date);
        String expected = "D | 0 | submit report | 25-12-2026";
        assertEquals(expected, deadline.toFileString());
    }

    @Test
    public void toString_markedDeadline_correctFormat() {
        LocalDate date = LocalDate.parse("2026-03-15");
        DeadLine deadline = new DeadLine("assignment", date);
        deadline.markAsDone();
        String expected = "[D][X] assignment (by: 15 Mar 2026)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toFileString_markedDeadline_correctFormat() {
        LocalDate date = LocalDate.parse("2026-06-30");
        DeadLine deadline = new DeadLine("project", date);
        deadline.markAsDone();
        String expected = "D | 1 | project | 30-06-2026";
        assertEquals(expected, deadline.toFileString());
    }
}
