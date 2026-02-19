package pompompurin.task;

/**
 * Provides help messages and command documentation for users.
 */
public class Help {

    /**
     * Returns the help message with all available commands.
     *
     * @return The help message as a string.
     */
    public static String getHelpMessage() {
        return "Here are the commands you can use:\n"
                + "list\n"
                + " - Shows all tasks.\n"
                + "todo <description>\n"
                + " - Adds a todo task.\n"
                + "deadline <description> /by <dd-mm-yyyy>\n"
                + " - Adds a deadline task.\n"
                + "event <description> /from <dd-mm-yyyy> /to <dd-mm-yyyy>\n"
                + " - Adds an event task.\n"
                + "mark <task number>\n"
                + " - Marks a task as done.\n"
                + "unmark <task number>\n"
                + " - Marks a task as not done.\n"
                + "delete <task number>\n"
                + " - Deletes a task.\n"
                + "find <keyword>\n"
                + " - Finds tasks containing the keyword.\n"
                + "help\n"
                + " - Shows this help message.\n"
                + "bye\n"
                + " - Exits the app.";
    }
}
