import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadLine extends Task {
    protected LocalDate by; // Change to LocalDate

    public DeadLine(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileString() {
        // Save as ISO format (yyyy-MM-dd) which is the default toString for LocalDate
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        // Print in a nicer format: Oct 15 2019
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}