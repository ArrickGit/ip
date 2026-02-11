package pompompurin.command;

import pompompurin.storage.Storage;
import pompompurin.task.TaskList;
import pompompurin.ui.Ui;

/**
 * Represents a command to show help information to the user.
 */
public class HelpCommand extends Command {

    /**
     * Displays help information.
     *
     * @param tasks   The list of tasks (not used).
     * @param ui      The user interface.
     * @param storage The storage handler (not used).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
