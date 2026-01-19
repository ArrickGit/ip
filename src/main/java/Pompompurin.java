import java.util.*;
public class Pompompurin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
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
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
    }
}
