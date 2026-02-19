package pompompurin.task;

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
     * Dates are stored in dd-MM-yyyy format.
     *
     * @return The file storage string.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from.format(formatter) + " | " + to.format(formatter);
    }

    /**
     * Returns the string representation for display.
     * Dates are formatted as "d MMM yyyy".
     *
     * @return The formatted string with [E] tag and date range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
