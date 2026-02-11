package pompompurin.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import pompompurin.exception.PomException;
import pompompurin.task.DeadLine;
import pompompurin.task.Event;
import pompompurin.task.Todo;

/**
 * Parses user input and translates it into Command objects.
 */

public class Parser {

    /**
     * Parses the full command string entered by the user.
     *
     * @param fullCommand The raw input string from the user.
     * @return The specific Command object corresponding to the input.
     * @throws PomException If the command format is invalid or the command is unknown.
     */
    public static Command parse(String fullCommand) throws PomException {
        String command = fullCommand.trim().toLowerCase();
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) { 
            return new ListCommand();
        } else if (command.startsWith("delete ")) {
            return new DeleteCommand(parseIndex(command));
        } else if (command.startsWith("mark ")) {
            return new MarkCommand(parseIndex(command));
        } else if (command.startsWith("unmark ")) {
            return new UnmarkCommand(parseIndex(command));
        } else if (command.equals("find") || command.startsWith("find ")) {
            return parseFind(command);
        } else if (command.equals("todo") || command.startsWith("todo ")) {
            return parseTodo(command);
        } else if (command.equals("deadline") || command.startsWith("deadline ")) {
            return parseDeadline(command);
        } else if (command.equals("event") || command.startsWith("event ")) {
            return parseEvent(command);
        } else {
            throw new PomException("Beeboo =( I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int parseIndex(String command) throws PomException {
        try {
            return Integer.parseInt(command.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new PomException("Beeboo =( Please provide a valid task number.");
        }
    }

    private static Command parseFind(String command) throws PomException {
        // Accepts: "find <keyword>" (keyword may contain spaces)
        String[] parts = command.trim().split("\\s+", 2);
        String keyword = parts.length < 2 ? "" : parts[1].trim();
        if (keyword.isEmpty()) {
            throw new PomException("Beeboo =( Please tell me what to look for (e.g., find book)");
        }
        return new FindCommand(keyword);
    }

    private static Command parseTodo(String command) throws PomException {
        String description = command.length() <= 4 ? "" : command.substring(5).trim();
        if (description.isEmpty()) {
            throw new PomException("Beeboo =( Description cannot be empty.");
        }
        return new AddCommand(new Todo(description));
    }

    private static Command parseDeadline(String command) throws PomException {
        String rest = command.length() <= 8 ? "" : command.substring(9).trim();
        int byPos = rest.indexOf(" /by ");
        if (byPos == -1) {
            throw new PomException("Beeboo =( Use: deadline <desc> /by <date>");
        }
        String desc = rest.substring(0, byPos).trim();
        String by = rest.substring(byPos + 5).trim();
        try {
            return new AddCommand(new DeadLine(desc, LocalDate.parse(by)));
        } catch (DateTimeParseException e) {
            throw new PomException("Beeboo =( Date format: yyyy-mm-dd");
        }
    }

    private static Command parseEvent(String command) throws PomException {
        String rest = command.length() <= 5 ? "" : command.substring(6).trim();
        int fromPos = rest.indexOf(" /from ");
        int toPos = rest.indexOf(" /to ");
        if (fromPos == -1 || toPos == -1) {
            throw new PomException("Beeboo =( Use: event <desc> /from <date> /to <date>");
        }
        String desc = rest.substring(0, fromPos).trim();
        String from = rest.substring(fromPos + 7, toPos).trim();
        String to = rest.substring(toPos + 5).trim();
        try {
            return new AddCommand(new Event(desc, LocalDate.parse(from), LocalDate.parse(to)));
        } catch (DateTimeParseException e) {
            throw new PomException("Beeboo =( Date format: yyyy-mm-dd");
        }
    }
}
