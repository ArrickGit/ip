import java.util.*;
public class Pompompurin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCounter = 0;
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
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            if (input.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(line);
                continue;
            }
            if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5).trim()) - 1; // obtain the 6th index because mark + space is already 5 index
                tasks[index].markAsDone();
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + tasks[index]);
                System.out.println(line);
                continue;
            }
            if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7).trim()) - 1; // obtain the 8th index because unmark + space is already 7 index
                tasks[index].markAsNotDone();
                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + tasks[index]);
                System.out.println(line);
                continue;
            }
            if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();

                tasks[taskCounter] = new Todo(description);
                taskCounter++;
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCounter - 1]);
                System.out.println("Now you have " + taskCounter + " tasks in the list.");
                System.out.println(line);
                continue;
            }

            if (input.startsWith("deadline ")) {
                String rest = input.substring(9).trim();
                int byPos = rest.indexOf(" /by ");
                if (byPos == -1) {
                    System.out.println(line);
                    System.out.println("Please use: deadline <description> /by <when>");
                    System.out.println(line);
                    continue;
                }

                String description = rest.substring(0, byPos).trim();
                String by = rest.substring(byPos + 5).trim();
                tasks[taskCounter] = new DeadLine(description, by);
                taskCounter++;
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCounter - 1]);
                System.out.println("Now you have " + taskCounter + " tasks in the list.");
                System.out.println(line);
                continue;
            }

            if (input.startsWith("event ")) {
                String rest = input.substring(6).trim();
                int fromPos = rest.indexOf(" /from ");
                int toPos = rest.indexOf(" /to ");

                if (fromPos == -1 || toPos == -1 || toPos < fromPos) {
                    System.out.println(line);
                    System.out.println("Please use: event <description> /from <start> /to <end>");
                    System.out.println(line);
                    continue;
                }

                String description = rest.substring(0, fromPos).trim();
                String from = rest.substring(fromPos + 7, toPos).trim(); //+7 because space /from space
                String to = rest.substring(toPos + 5).trim(); //+5 because space  /to space

                tasks[taskCounter] = new Event(description, from, to);
                taskCounter++;

                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCounter - 1]);
                System.out.println("Now you have " + taskCounter + " tasks in the list.");
                System.out.println(line);
                continue;
            }
            System.out.println(line);
            tasks[taskCounter] = new Todo(input);
            taskCounter++;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks[taskCounter - 1]);
            System.out.println("Now you have " + taskCounter + " tasks in the list.");
            System.out.println(line);
        }
    }
}
