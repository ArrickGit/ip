package pompompurin.command;

import pompompurin.storage.Storage;
import pompompurin.task.TaskList;
import pompompurin.exception.PomException;
import pompompurin.ui.Ui;

/**
 * Represents an abstract command that can be executed by the application.
 * All specific command types (e.g., AddCommand, DeleteCommand) inherit from this class.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage to save or load data.
     * @throws PomException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PomException;

    /**
     * Checks if this command should terminate the application.
     *
     * @return true if the program should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}