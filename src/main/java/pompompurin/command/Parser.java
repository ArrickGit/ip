package pompompurin.command;

import pompompurin.ui.Pompompurin;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String fullCommand) throws Pompompurin.purinException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteCommand(parseIndex(fullCommand));
        } else if (fullCommand.startsWith("mark ")) {
            // Assuming you created pompompurin.command.MarkCommand class
            return new MarkCommand(parseIndex(fullCommand));
        } else if (fullCommand.startsWith("unmark ")) {
            // Assuming you created pompompurin.command.UnmarkCommand class
            return new UnmarkCommand(parseIndex(fullCommand));
        } else if (fullCommand.startsWith("todo")) {
            return parseTodo(fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            return parseDeadline(fullCommand);
        } else if (fullCommand.startsWith("event")) {
            return parseEvent(fullCommand);
        } else {
            throw new Pompompurin.purinException("Beeboo =( I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int parseIndex(String command) throws Pompompurin.purinException {
        try {
            return Integer.parseInt(command.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new Pompompurin.purinException("Beeboo =( Please provide a valid task number.");
        }
    }

    private static Command parseTodo(String command) throws Pompompurin.purinException {
        String description = command.length() <= 4 ? "" : command.substring(5).trim();
        if (description.isEmpty()) throw new Pompompurin.purinException("Beeboo =( Description cannot be empty.");
        return new AddCommand(new Todo(description));
    }

    private static Command parseDeadline(String command) throws Pompompurin.purinException {
        String rest = command.length() <= 8 ? "" : command.substring(9).trim();
        int byPos = rest.indexOf(" /by ");
        if (byPos == -1) throw new Pompompurin.purinException("Beeboo =( Use: deadline <desc> /by <date>");
        String desc = rest.substring(0, byPos).trim();
        String by = rest.substring(byPos + 5).trim();
        try {
            return new AddCommand(new DeadLine(desc, LocalDate.parse(by)));
        } catch (DateTimeParseException e) {
            throw new Pompompurin.purinException("Beeboo =( Date format: yyyy-mm-dd");
        }
    }

    private static Command parseEvent(String command) throws Pompompurin.purinException {
        String rest = command.length() <= 5 ? "" : command.substring(6).trim();
        int fromPos = rest.indexOf(" /from ");
        int toPos = rest.indexOf(" /to ");
        if (fromPos == -1 || toPos == -1) throw new Pompompurin.purinException("Beeboo =( Use: event <desc> /from <date> /to <date>");
        String desc = rest.substring(0, fromPos).trim();
        String from = rest.substring(fromPos + 7, toPos).trim();
        String to = rest.substring(toPos + 5).trim();
        try {
            return new AddCommand(new Event(desc, LocalDate.parse(from), LocalDate.parse(to)));
        } catch (DateTimeParseException e) {
            throw new Pompompurin.purinException("Beeboo =( Date format: yyyy-mm-dd");
        }
    }
}