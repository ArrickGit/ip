package pompompurin.command;

import pompompurin.storage.Storage;
import pompompurin.task.Task;
import pompompurin.task.TaskList;
import pompompurin.exception.PomException;
import pompompurin.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Creates a new MarkCommand.
     *
     * @param index The zero-based index of the task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index as done and saves the change.
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

        Task taskToMark = tasks.get(index);
        taskToMark.markAsDone();

        storage.save(tasks);

        ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskToMark);
        ui.showLine();
    }
}