package pompompurin.command; // 1. Fix: Package name matches the folder structure

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}