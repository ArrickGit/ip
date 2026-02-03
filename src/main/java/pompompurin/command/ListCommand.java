package pompompurin.command;

import pompompurin.storage.Storage;
import pompompurin.task.TaskList;
import pompompurin.ui.Ui;

/**
 * Represents a command to list all tasks currently in the task list.
 */
public class ListCommand extends Command {

    /**
     * Displays all tasks in the list to the user.
     *
     * @param tasks   The list of tasks to display.
     * @param ui      The user interface.
     * @param storage The storage handler (not used).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        ui.showLine();
    }
}