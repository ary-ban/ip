/** A task with only a description and a done/undone state. */

package larry.model;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    protected String typeIcon() {
        return "T";
    }
}
