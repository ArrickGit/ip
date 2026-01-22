import java.util.*;
public class Pompompurin {
    static class purinException extends Exception { //exception class
        purinException(String message) {
            super(message);
        }
    }

    private static void printError(String line, String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        // ascii art obtain from https://patorjk.com/software/taag/#p=display&f=Big&t=Pompompurin&x=none&v=4&h=4&w=80&we=false
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

            try { // for the exception
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

                    if (description.isEmpty()) {
                        throw new purinException("Beeboo =( The description of a deadline cannot be empty.");
                    }
                    if (by.isEmpty()) {
                        throw new purinException("Beeboo =( The /by part of a deadline cannot be empty.");
                    }

                    Task t = new DeadLine(description, by);
                    tasks.add(t);

                    System.out.println(line);
                    System.out.println("Gotcha. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
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

                    if (description.isEmpty()) {
                        throw new purinException("Beeboo =( The description of an event cannot be empty.");
                    }
                    if (from.isEmpty() || to.isEmpty()) {
                        throw new purinException("Beeboo =( The /from and /to parts of an event cannot be empty.");
                    }

                    Task t = new Event(description, from, to);
                    tasks.add(t);

                    System.out.println(line);
                    System.out.println("Gotcha. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                    continue;
                }

                if (input.startsWith("mark ")) {
                    String num = input.substring(5).trim();
                    int index;
                    try {
                        index = Integer.parseInt(num) - 1;
                    } catch (NumberFormatException e) {
                        throw new purinException("Beeboo =( mark expects a task number, e.g. mark 2");
                    }

                    if (index < 0 || index >= tasks.size()) {
                        throw new purinException("Beeboo =( That task number is out of range.");
                    }

                    tasks.get(index).markAsDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(index));
                    System.out.println(line);
                    continue;
                }

                if (input.startsWith("unmark ")) {
                    String num = input.substring(7).trim();
                    int index;
                    try {
                        index = Integer.parseInt(num) - 1;
                    } catch (NumberFormatException e) {
                        throw new purinException("Beeboo =( unmark expects a task number, e.g. unmark 2");
                    }

                    if (index < 0 || index >= tasks.size()) {
                        throw new purinException("Beeboo =( That task number is out of range.");
                    }

                    tasks.get(index).markAsNotDone();
                    System.out.println(line);
                    System.out.println("Gotcha, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(index));
                    System.out.println(line);
                    continue;
                }

                if (input.startsWith("delete ")) {
                    String num = input.substring(7).trim();
                    int index;
                    try {
                        index = Integer.parseInt(num) - 1;
                    } catch (NumberFormatException e) {
                        throw new purinException("Beeboo =( delete expects a task number, e.g. delete 3");
                    }

                    if (index < 0 || index >= tasks.size()) {
                        throw new purinException("Beeboo =( That task number is out of range.");
                    }

                    Task removed = tasks.remove(index);

                    System.out.println(line);
                    System.out.println("Noted. I've destroyed this task:");
                    System.out.println("  " + removed);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                    continue;
                }

                // Unknown command
                throw new purinException("Beeboo =( I'm sorry, but I don't know what that means :-(");

            } catch (purinException e) { //catch any command and print error message
                printError(line, e.getMessage());
            }
        }
    }
}
