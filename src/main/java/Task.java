package larry;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    private String statusIcon() {
        return isDone ? "X" : " ";
    }
    protected String typeIcon() {
        return "?";
    }
    @Override
    public String toString() {
        return "[" + typeIcon() + "][" + statusIcon() + "] " + description;
    }
    public String getDescription() { return description; }
    public boolean isDone() { return isDone; }
}
