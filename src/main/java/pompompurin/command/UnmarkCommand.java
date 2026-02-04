package pompompurin.command;

import pompompurin.exception.PomException;
import pompompurin.storage.Storage;
import pompompurin.task.Task;
import pompompurin.task.TaskList;
import pompompurin.ui.Ui;

/**
 * Represents a command to mark a task as not done (incomplete).
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates a new UnmarkCommand.
     *
     * @param index The zero-based index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index as not done and saves the change.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws PomException If the index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PomException {
        if (index < 0 || index >= tasks.size()) {
            throw new PomException("Beeboo =( That task number is out of range.");
        }
        Task taskToUnmark = tasks.get(index);
        taskToUnmark.markAsNotDone();

        storage.save(tasks);

        ui.showLine();
        System.out.println("Gotcha, I've marked this task as not done yet:");
        System.out.println("  " + taskToUnmark);
        ui.showLine();
    }
}
