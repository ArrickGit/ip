package pompompurin.ui;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 * Responsible for reading input and printing messages to the console.
 */

public class Ui {
    private Scanner scanner;

    /**
     * Creates a new Ui and initializes the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the standard input.
     *
     * @return The line entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message and logo.
     */
    public void showWelcome() {
        String logo = " _____                                                       _       \n"
                + "|  __ \\                                                     (_) \n"
                + "| |__) |__  _ __ ___  _ __   ___  _ __ ___  _ __  _   _ _ __ _ _ __ \n"
                + "|  ___/ _ \\| '_ ` _ \\| '_ \\ / _ \\| '_ ` _ \\| '_ \\| | | | '__| | '_ \\ \n"
                + "| |  | (_) | | | | | | |_) | (_) | | | | | | |_) | |_| | |  | | | | | \n"
                + "|_|   \\___/|_| |_| |_| .__/ \\___/|_| |_| |_| .__/ \\__,_|_|  |_|_| |_| \n"
                + "| |                   | |                  | |\n"
                + "|_|                   |_|                  |_|\n";
        System.out.println("Hello from\n" + logo);
        showLine();
        showLines(
                "Hello! I'm Pompompurin", // a simple Varargs
                "What can I do for you?"
        );
        showLine();
    }

    /**
     * Prints each line on a new line.
     *
     * @param lines The lines to print.
     */
    public void showLines(String... lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /**
     * Displays an error message for loading failures.
     */
    public void showLoadingError() {
        System.out.println("Beeboo =( Error loading file. Creating new task list.");
    }
}
