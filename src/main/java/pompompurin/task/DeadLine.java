package pompompurin.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task (a task with a due date).
 */
public class DeadLine extends Task {
    protected LocalDate by;

    /**
     * Creates a new Deadline task.
     *
     * @param description The description of the task.
     * @param by          The due date of the task.
     */
    public DeadLine(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string format for file storage (D | status | description | date).
     * The date is stored in ISO-8601 format (yyyy-mm-dd).
     *
     * @return The file storage string.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Returns the string representation for display.
     * The date is formatted as "MMM d yyyy".
     *
     * @return The formatted string with [D] tag and date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}