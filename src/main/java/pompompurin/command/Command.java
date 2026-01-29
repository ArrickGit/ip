package pompompurin.command;

import pompompurin.storage.Storage;
import pompompurin.ui.Pompompurin;
import pompompurin.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Pompompurin.purinException;

    public boolean isExit() {
        return false;
    }
}