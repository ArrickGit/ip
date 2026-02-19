package pompompurin.ui;

import java.io.IOException;

import pompompurin.command.Command;
import pompompurin.command.Parser;
import pompompurin.exception.PomException;
import pompompurin.storage.Storage;
import pompompurin.task.TaskList;

/**
 * The main entry point for the Pompompurin chatbot application.
 * This class initializes the user interface, storage, and task list,
 * and runs the main command loop.
 */
public class Pompompurin {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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
            } catch (PomException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Returns the welcome message for GUI usage.
     *
     * @return The welcome message output.
     */
    public String getWelcomeMessage() {
        return formatForGui(captureOutput(ui::showWelcome));
    }

    /**
     * Executes a single command and returns the output for GUI usage.
     *
     * @param input The user input.
     * @return CommandResult containing output and exit flag.
     */
    public String getResponse(String input) {
        String output = captureOutput(() -> {
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
            } catch (PomException e) {
                ui.showError(e.getMessage());
            }
        });
        return formatForGui(output);
    }

    private String formatForGui(String output) {
        String[] lines = output.split("\\R");
        StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            if (line.matches("_+")) {
                continue;
            }
            if (builder.length() > 0) {
                builder.append(System.lineSeparator());
            }
            builder.append(line);
        }
        return builder.toString().replaceAll("\\R+$", "");
    }

    /**
     * Checks if the given input is an exit command.
     *
     * @param input The user input.
     * @return true if the parsed command requests exit, false otherwise.
     */
    public boolean isExitCommand(String input) {
        try {
            Command c = Parser.parse(input);
            return c.isExit();
        } catch (PomException e) {
            return false;
        }
    }

    private String captureOutput(Runnable action) {
        java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();
        java.io.PrintStream original = System.out;
        try {
            System.setOut(new java.io.PrintStream(buffer));
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        new Pompompurin("./data/pompompurin.txt").run();
    }
}
