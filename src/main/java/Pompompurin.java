import java.util.*;
import java.io.IOException; // Import IOException
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Pompompurin {

    // ... (Keep your purinException and printError classes exactly as they are) ...
    static class purinException extends Exception {
        purinException(String message) { super(message); }
    }

    private static void printError(String line, String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Setup Storage
        Storage storage = new Storage("./data/pompompurin.txt");
        ArrayList<Task> tasks;

        // 2. Load Tasks
        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("Beeboo =( Error loading file. Creating new list.");
            tasks = new ArrayList<>();
        }

        // ... (Keep your logo and welcome message logic) ...
        String logo = "_____                                                       _       \n"
                + "|  __ \\                                                     (_) \n"
                + "| |__) |__  _ __ ___  _ __   ___  _ __ ___  _ __  _   _ _ __ _ _ __ \n"
                + "|  ___/ _ \\| '_ ` _ \\| '_ \\ / _ \\| '_ ` _ \\| '_ \\| | | | '__| | '_ \\ \n"
                + "| |  | (_) | | | | | | |_) | (_) | | | | | | |_) | |_| | |  | | | | | \n"
                + "|_|   \\___/|_| |_| |_| .__/ \\___/|_| |_| |_| .__/ \\__,_|_|  |_|_| |_| \n"
                + "| |                   | |                  | |\n"
                + "|_|                   |_|                  |_|\n";
        System.out.println("Hello from\n" + logo);
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Pompompurin");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    System.out.println(line);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                }

                if (input.equals("list")) {
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println(line);
                    continue;
                }

                if (input.equals("todo") || input.startsWith("todo ")) {
                    String description = input.length() == 4 ? "" : input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new purinException("Beeboo =( The description of a todo cannot be empty.");
                    }

                    Task t = new Todo(description);
                    tasks.add(t);
                    storage.save(tasks);

                    System.out.println(line);
                    System.out.println("Gotcha. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                    continue;
                }

                if (input.equals("deadline") || input.startsWith("deadline ")) {
                    String rest = input.length() == 8 ? "" : input.substring(9).trim();
                    int byPos = rest.indexOf(" /by ");
                    if (byPos == -1) {
                        throw new purinException("Beeboo =( Please use: deadline <description> /by <when>");
                    }
                    String description = rest.substring(0, byPos).trim();
                    String by = rest.substring(byPos + 5).trim();

                    if (description.isEmpty()) throw new purinException("Beeboo =( Description empty.");
                    if (by.isEmpty()) throw new purinException("Beeboo =( Date empty.");

                    try {
                        // Parse the user input into a date object
                        LocalDate date = LocalDate.parse(by);
                        Task t = new DeadLine(description, date);
                        tasks.add(t);
                        storage.save(tasks);

                        System.out.println(line);
                        System.out.println("Gotcha. I've added this task:");
                        System.out.println("  " + t);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(line);
                    } catch (DateTimeParseException e) {
                        throw new purinException("Beeboo =( Please enter date in yyyy-mm-dd format (e.g., 2019-10-15)");
                    }

                    continue;
                }

                if (input.equals("event") || input.startsWith("event ")) {
                    String rest = input.length() == 5 ? "" : input.substring(6).trim();
                    int fromPos = rest.indexOf(" /from ");
                    int toPos = rest.indexOf(" /to ");

                    if (fromPos == -1 || toPos == -1 || toPos < fromPos) {
                        throw new purinException("Beeboo =( Please use: event <description> /from <start> /to <end>");
                    }
                    String description = rest.substring(0, fromPos).trim();
                    String from = rest.substring(fromPos + 7, toPos).trim();
                    String to = rest.substring(toPos + 5).trim();

                    if (description.isEmpty()) throw new purinException("Beeboo =( Description empty.");
                    if (from.isEmpty() || to.isEmpty()) throw new purinException("Beeboo =( Times empty.");

                    try {
                        // Parse the dates provided by the user
                        LocalDate fromDate = LocalDate.parse(from);
                        LocalDate toDate = LocalDate.parse(to);

                        Task t = new Event(description, fromDate, toDate);
                        tasks.add(t);
                        storage.save(tasks);

                        System.out.println(line);
                        System.out.println("Gotcha. I've added this task:");
                        System.out.println("  " + t);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(line);
                    } catch (java.time.format.DateTimeParseException e) {
                        throw new purinException("Beeboo =( Please enter dates in yyyy-mm-dd format (e.g., event team building /from 2019-10-15 /to 2019-10-16)");
                    }
                    continue;
                }

                if (input.startsWith("mark ")) {
                    // ... (Keep existing parsing logic) ...
                    String num = input.substring(5).trim();
                    int index;
                    try {
                        index = Integer.parseInt(num) - 1;
                    } catch (NumberFormatException e) {
                        throw new purinException("Beeboo =( mark expects a number");
                    }
                    if (index < 0 || index >= tasks.size()) {
                        throw new purinException("Beeboo =( Number out of range.");
                    }

                    tasks.get(index).markAsDone();
                    storage.save(tasks); // <--- SAVE!

                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(index));
                    System.out.println(line);
                    continue;
                }

                if (input.startsWith("unmark ")) {
                    // ... (Keep existing parsing logic) ...
                    String num = input.substring(7).trim();
                    int index;
                    try {
                        index = Integer.parseInt(num) - 1;
                    } catch (NumberFormatException e) {
                        throw new purinException("Beeboo =( unmark expects a number");
                    }
                    if (index < 0 || index >= tasks.size()) {
                        throw new purinException("Beeboo =( Number out of range.");
                    }

                    tasks.get(index).markAsNotDone();
                    storage.save(tasks); // <--- SAVE!

                    System.out.println(line);
                    System.out.println("Gotcha, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(index));
                    System.out.println(line);
                    continue;
                }

                if (input.startsWith("delete ")) {
                    // ... (Keep existing parsing logic) ...
                    String num = input.substring(7).trim();
                    int index;
                    try {
                        index = Integer.parseInt(num) - 1;
                    } catch (NumberFormatException e) {
                        throw new purinException("Beeboo =( delete expects a number");
                    }
                    if (index < 0 || index >= tasks.size()) {
                        throw new purinException("Beeboo =( Number out of range.");
                    }

                    Task removed = tasks.remove(index);
                    storage.save(tasks); // <--- SAVE!

                    System.out.println(line);
                    System.out.println("Noted. I've destroyed this task:");
                    System.out.println("  " + removed);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                    continue;
                }

                throw new purinException("Beeboo =( I'm sorry, but I don't know what that means :-(");

            } catch (purinException e) {
                printError(line, e.getMessage());
            }
        }
    }
}