package pompompurin.command;

import pompompurin.storage.Storage;
import pompompurin.ui.Ui;
import java.util.ArrayList;

/**
 * Represents a command to find and list tasks that contain a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches for tasks containing the keyword and displays the matching list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler (not used).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        // Loop through all tasks and check if description contains keyword
        for (Task t : tasks.getAllTasks()) {
            if (t.description.contains(keyword)) {
                foundTasks.add(t);
            }
        }

        ui.showLine();
        if (foundTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + ". " + foundTasks.get(i));
            }
        }
        ui.showLine();
    }
}