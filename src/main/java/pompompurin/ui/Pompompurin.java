package pompompurin.ui;

import pompompurin.command.Command;
import pompompurin.command.Parser;
import pompompurin.command.TaskList;
import pompompurin.storage.Storage;

import java.io.IOException;

/**
 * The main entry point for the Pompompurin chatbot application.
 * This class initializes the user interface, storage, and task list,
 * and runs the main command loop.
 */

public class Pompompurin {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Custom exception class for Pompompurin-specific errors.
     */

    public static class purinException extends Exception {
        public purinException(String message) { super(message); }
    }

    /**
     * Initializes the Pompompurin application.
     * Attempts to load existing data from the specified file path.
     * If loading fails, starts with an empty task list.
     *
     * @param filePath The file path where task data is stored.
     */

    public Pompompurin(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program loop.
     * Continuously reads user commands, parses them, executes them,
     * and saves the state until the exit command is issued.
     */

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (purinException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Pompompurin("./data/pompompurin.txt").run();
    }
}