package pompompurin.command; // Update to match your folder structure
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate; // Import this!
import pompompurin.task.Event;

public class EventTest {
    @Test
    public void testEventToString() {
        LocalDate from = LocalDate.parse("2019-10-22");
        LocalDate to = LocalDate.parse("2020-10-22");

        Event event = new Event("go home", from, to);
        String expectedDisplay = "[E][ ] go home (from: Oct 22 2019 to: Oct 22 2020)";
        assertEquals(expectedDisplay, event.toString());
    }
}
