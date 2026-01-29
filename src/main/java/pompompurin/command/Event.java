package pompompurin.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task (a task with a start and end date).
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Creates a new Event task.
     *
     * @param description The description of the event.
     * @param from        The start date.
     * @param to          The end date.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string format for file storage (E | status | description | from | to).
     * Dates are stored in ISO-8601 format (yyyy-mm-dd).
     *
     * @return The file storage string.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    /**
     * Returns the string representation for display.
     * Dates are formatted as "MMM d yyyy".
     *
     * @return The formatted string with [E] tag and date range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}