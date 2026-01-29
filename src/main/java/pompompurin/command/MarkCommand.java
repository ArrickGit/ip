package pompompurin.command;

import pompompurin.storage.Storage;
import pompompurin.ui.Pompompurin;
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
     * @throws Pompompurin.purinException If the index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Pompompurin.purinException {
        if (index < 0 || index >= tasks.size()) {
            throw new Pompompurin.purinException("Beeboo =( That task number is out of range.");
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