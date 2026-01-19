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
            System.out.println(line);
            tasks[taskCounter] = new Task(input);
            taskCounter++;
            System.out.println("added: " + input);
            System.out.println(line);
        }
    }
}
