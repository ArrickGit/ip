package pompompurin.command;

import pompompurin.storage.Storage;
import pompompurin.ui.Pompompurin;
import pompompurin.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Pompompurin.purinException {
        if (index < 0 || index >= tasks.size()) {
            throw new Pompompurin.purinException("Beeboo =( pompompurin.command.Task number out of range.");
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