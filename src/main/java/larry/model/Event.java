package larry.model;

import larry.util.DateTimeFormats;

/**
 * A task that is due by a specific date/time.
 * Stores the raw input; formatted when displayed.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    protected String typeIcon() {
        return "E";
    }
    @Override
    public String toString() {
        return super.toString()
                + " (from: " + DateTimeFormats.pretty(from)
                + " to: " + DateTimeFormats.pretty(to) + ")";
    }
    public String getFrom() { return from; }
    public String getTo() { return to; }
}
