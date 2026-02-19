package pompompurin.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadLineTest {

    @Test
    public void toString_newDeadline_correctFormat() {
        LocalDate date = LocalDate.parse("2026-12-25");
        DeadLine deadline = new DeadLine("submit report", date);
        String expected = "[D][ ] submit report (by: Dec 25 2026)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toFileString_newDeadline_correctFormat() {
        LocalDate date = LocalDate.parse("2026-12-25");
        DeadLine deadline = new DeadLine("submit report", date);
        String expected = "D | 0 | submit report | 2026-12-25";
        assertEquals(expected, deadline.toFileString());
    }

    @Test
    public void toString_markedDeadline_correctFormat() {
        LocalDate date = LocalDate.parse("2026-03-15");
        DeadLine deadline = new DeadLine("assignment", date);
        deadline.markAsDone();
        String expected = "[D][X] assignment (by: Mar 15 2026)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toFileString_markedDeadline_correctFormat() {
        LocalDate date = LocalDate.parse("2026-06-30");
        DeadLine deadline = new DeadLine("project", date);
        deadline.markAsDone();
        String expected = "D | 1 | project | 2026-06-30";
        assertEquals(expected, deadline.toFileString());
    }
}
