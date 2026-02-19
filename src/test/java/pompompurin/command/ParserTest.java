package pompompurin.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import pompompurin.exception.PomException;

public class ParserTest {

    @Test
    public void parse_exitCommand_success() throws PomException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parse_listCommand_success() throws PomException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parse_helpCommand_success() throws PomException {
        Command command = Parser.parse("help");
        assertTrue(command instanceof HelpCommand);
    }

    @Test
    public void parse_todoCommand_success() throws PomException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_emptyTodo_throwsException() {
        assertThrows(PomException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void parse_deadlineCommand_success() throws PomException {
        Command command = Parser.parse("deadline submit /by 2026-12-25");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_deadlineWithoutBy_throwsException() {
        assertThrows(PomException.class, () -> Parser.parse("deadline submit"));
    }

    @Test
    public void parse_deadlineEmptyDescription_throwsException() {
        assertThrows(PomException.class, () -> Parser.parse("deadline  /by 2026-12-25"));
    }

    @Test
    public void parse_deadlineInvalidDate_throwsException() {
        assertThrows(PomException.class, () -> Parser.parse("deadline submit /by invalid-date"));
    }

    @Test
    public void parse_eventCommand_success() throws PomException {
        Command command = Parser.parse("event meeting /from 2026-01-01 /to 2026-01-02");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_eventWithoutFrom_throwsException() {
        assertThrows(PomException.class, () -> Parser.parse("event meeting /to 2026-01-02"));
    }

    @Test
    public void parse_eventStartAfterEnd_throwsException() {
        assertThrows(PomException.class, () ->
            Parser.parse("event meeting /from 2026-12-25 /to 2026-01-01"));
    }

    @Test
    public void parse_markCommand_success() throws PomException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parse_deleteCommand_success() throws PomException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parse_findCommand_success() throws PomException {
        Command command = Parser.parse("find book");
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        assertThrows(PomException.class, () -> Parser.parse("unknown"));
    }

    @Test
    public void parse_multipleSpaces_success() throws PomException {
        Command command = Parser.parse("delete   1");
        assertTrue(command instanceof DeleteCommand);
    }
}
