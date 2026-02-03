package pompompurin.ui;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 * Responsible for reading input and printing messages to the console.
 */

public class Ui {
    private Scanner scanner;

    /**
     * Reads a command from the standard input.
     *
     * @return The line entered by the user.
     */
    public Ui() {

        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and logo.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showWelcome() {
        String logo = "_____                                                       _       \n"
                + "|  __ \\                                                     (_) \n"
                + "| |__) |__  _ __ ___  _ __   ___  _ __ ___  _ __  _   _ _ __ _ _ __ \n"
                + "|  ___/ _ \\| '_ ` _ \\| '_ \\ / _ \\| '_ ` _ \\| '_ \\| | | | '__| | '_ \\ \n"
                + "| |  | (_) | | | | | | |_) | (_) | | | | | | |_) | |_| | |  | | | | | \n"
                + "|_|   \\___/|_| |_| |_| .__/ \\___/|_| |_| |_| .__/ \\__,_|_|  |_|_| |_| \n"
                + "| |                   | |                  | |\n"
                + "|_|                   |_|                  |_|\n";
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("Hello! I'm pompompurin.ui.Pompompurin");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showLine() {

        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    public void showLoadingError() {
        System.out.println("Beeboo =( Error loading file. Creating new task list.");
    }
}