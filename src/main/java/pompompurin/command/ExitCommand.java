package pompompurin.command;

import pompompurin.storage.Storage;
import pompompurin.task.TaskList;
import pompompurin.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Displays a goodbye message to the user.
     *
     * @param tasks   The list of tasks (not used).
     * @param ui      The user interface.
     * @param storage The storage handler (not used).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        ui.showLine();
    }

    /**
     * Returns true to signal the application to terminate.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {

        return true;
    }
}