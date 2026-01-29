import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

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
        System.out.println("Hello! I'm Pompompurin");
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