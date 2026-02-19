package pompompurin.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import pompompurin.task.Todo;

public class TodoTest {

    @Test
    public void toFileString_newTodo_correctFormat() {
        Todo todo = new Todo("read book");
        String expected = "T | 0 | read book";
        assertEquals(expected, todo.toFileString());
    }

    @Test
    public void toString_newTodo_correctFormat() {
        Todo todo = new Todo("read book");
        String expected = "[T][ ] read book";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void markAsDone_todo_markedCorrectly() {
        Todo todo = new Todo("exercise");
        todo.markAsDone();
        String expected = "[T][X] exercise";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void toFileString_markedTodo_correctFormat() {
        Todo todo = new Todo("homework");
        todo.markAsDone();
        String expected = "T | 1 | homework";
        assertEquals(expected, todo.toFileString());
    }

    @Test
    public void markAsNotDone_todo_unmarkedCorrectly() {
        Todo todo = new Todo("sleep");
        todo.markAsDone();
        todo.markAsNotDone();
        String expected = "[T][ ] sleep";
        assertEquals(expected, todo.toString());
    }
}
