/**
 * In-memory list of tasks with 1-based indexing for user-facing operations.
 */

package larry.model;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(List<Task> initial) {
        this.tasks = new ArrayList<>(initial);
    }

    /** Number of tasks currently in the list. */
    public int size() { return tasks.size(); }
    /** Underlying list view used by UI and Storage. */
    public List<Task> asList() { return java.util.Collections.unmodifiableList(tasks); }

    /** Appends the given task to the end of the list. */
    public void add(Task t) {
        tasks.add(t);
    }

    /** Removes and returns the task at the given 1-based index (callers validate first). */
    public Task delete(int oneBasedIndex) {
        return tasks.remove(oneBasedIndex - 1);
    }

    /** Returns the task at the given 1-based index (callers validate first). */
    public Task get(int oneBasedIndex) {
        return tasks.get(oneBasedIndex - 1);
    }

    /** Returns true if {@code oneBasedIndex} is within [1, size()]. */
    public boolean isValidIndex(int oneBasedIndex) {
        return oneBasedIndex >= 1 && oneBasedIndex <= tasks.size();
    }
}
