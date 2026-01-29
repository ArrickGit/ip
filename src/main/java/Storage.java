import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // If file or directory doesn't exist, create them and return empty list
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create directory (e.g., ./data/)
            file.createNewFile();          // Create file (e.g., pompompurin.txt)
            return tasks;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // Split by " | "
            // We use regex " \\| " because | is a special character in regex
            String[] parts = line.split(" \\| ");

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task t = null;
            switch (type) {
                case "T":
                    t = new Todo(description);
                    break;
                case "D":
                    String by = parts[3];
                    LocalDate byDate = LocalDate.parse(by);
                    t = new DeadLine(description, byDate);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    LocalDate fromDate = LocalDate.parse(from);
                    LocalDate toDate = LocalDate.parse(to);
                    t = new Event(description, fromDate, toDate);
                    break;
            }

            if (t != null) {
                if (isDone) {
                    t.markAsDone();
                }
                tasks.add(t);
            }
        }
        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : tasks.getAllTasks()) {
                fw.write(t.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Beeboo =( Error saving tasks: " + e.getMessage());
        }
    }
}