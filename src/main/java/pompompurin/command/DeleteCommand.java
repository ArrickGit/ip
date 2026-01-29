package pompompurin.command;

import pompompurin.storage.Storage;
import pompompurin.ui.Pompompurin;
import pompompurin.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a new DeleteCommand.
     *
     * @param index The zero-based index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the specified index, saves the change, and displays a message.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws Pompompurin.purinException If the index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Pompompurin.purinException {
        if (index < 0 || index >= tasks.size()) {
            throw new Pompompurin.purinException("Beeboo =( Task number out of range.");
        }
        Task removed = tasks.delete(index);
        storage.save(tasks);
        ui.showLine();
        System.out.println("Noted. I've destroyed this task:");
        System.out.println("  " + removed);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }
}