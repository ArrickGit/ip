package pompompurin.command;

import pompompurin.storage.Storage;
import pompompurin.task.Task;
import pompompurin.task.TaskList;
import pompompurin.ui.Ui;

/**
 * Represents a command to add a new task (Todo, Deadline, or Event) to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates a new AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the list, saves the updated list to storage, and displays a success message.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.save(tasks);
        ui.showLine();
        System.out.println("Gotcha. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }
}