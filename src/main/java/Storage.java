package larry;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Storage {
    private final Path savePath;

    public Storage(String relativePath) {
        this.savePath = Path.of(relativePath);
    }
    public List<Task> load() {
        try {
            ensureParentDir();
            if (!Files.exists(savePath)) {
                Files.createFile(savePath);
                return new ArrayList<>();
            }
            List<String> lines = Files.readAllLines(savePath, StandardCharsets.UTF_8);
            List<Task> tasks = new ArrayList<>();
            for (String line : lines) {
                if (line.isBlank()) continue;
                String[] parts = line.split("\\|");
                String type = parts[0];
                boolean done = "1".equals(parts[1]);
                String desc = parts.length > 2 ? parts[2] : "";

                Task t;
                switch (type) {
                    case "T":
                        t = new Todo(desc);
                        break;
                    case "D":
                        String by = parts.length > 3 ? parts[3] : "";
                        t = new Deadline(desc, by);
                        break;
                    case "E":
                        String from = parts.length > 3 ? parts[3] : "";
                        String to   = parts.length > 4 ? parts[4] : "";
                        t = new Event(desc, from, to);
                        break;
                    default:
                        t = new Task(desc);
                        break;
                }
                if (done) t.markDone();
                tasks.add(t);
            }
            return tasks;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void save(List<Task> tasks) {
        try {
            ensureParentDir();
            try (BufferedWriter bw = Files.newBufferedWriter(savePath, StandardCharsets.UTF_8)) {
                for (Task t : tasks) {
                    bw.write(serialize(t));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
        }
    }

    private void ensureParentDir() throws IOException {
        Path parent = savePath.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }

    private String serialize(Task t) {
        String done = t.isDone() ? "1" : "0";
        if (t instanceof Todo) {
            return "T|" + done + "|" + ((Todo) t).getDescription();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D|" + done + "|" + d.getDescription() + "|" + d.getBy();
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return "E|" + done + "|" + e.getDescription() + "|" + e.getFrom() + "|" + e.getTo();
        } else {
            return "?|" + done + "|" + t.getDescription();
        }
    }
}
