package pompompurin.task;

/**
 * Represents a Todo task (a task without a date).
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string format for file storage (T | status | description).
     *
     * @return The file storage string.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the string representation for display.
     *
     * @return The formatted string with [T] tag.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}