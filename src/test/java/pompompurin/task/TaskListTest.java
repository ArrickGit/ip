package pompompurin.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList tasks;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
    }

    @Test
    public void add_task_sizeIncreases() {
        Todo todo = new Todo("test task");
        tasks.add(todo);
        assertEquals(1, tasks.size());
    }

    @Test
    public void delete_validIndex_taskRemoved() {
        Todo todo1 = new Todo("task 1");
        Todo todo2 = new Todo("task 2");
        tasks.add(todo1);
        tasks.add(todo2);

        tasks.delete(0);
        assertEquals(1, tasks.size());
    }

    @Test
    public void delete_invalidIndex_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.delete(0));
    }

    @Test
    public void get_validIndex_returnsTask() {
        Todo todo = new Todo("get task");
        tasks.add(todo);

        Task retrieved = tasks.get(0);
        assertEquals("get task", retrieved.getDescription());
    }

    @Test
    public void get_invalidIndex_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.get(5));
    }
}
