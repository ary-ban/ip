package larry.model;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(List<Task> initial) {
        this.tasks = new ArrayList<>(initial);
    }

    public int size() { return tasks.size(); }
    public List<Task> asList() { return java.util.Collections.unmodifiableList(tasks); }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task delete(int oneBasedIndex) {
        return tasks.remove(oneBasedIndex - 1);
    }

    public Task get(int oneBasedIndex) {
        return tasks.get(oneBasedIndex - 1);
    }

    public boolean isValidIndex(int oneBasedIndex) {
        return oneBasedIndex >= 1 && oneBasedIndex <= tasks.size();
    }
}
