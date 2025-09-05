package larry;

public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    protected String typeIcon() {
        return "D";
    }
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
