/**
 * Parses raw user input into command words and arguments.
 */
package larry.parser;

import java.util.Map;

public class Parser {

    private static final Map<String, String> ALIASES = Map.ofEntries(
            Map.entry("t", "todo"),
            Map.entry("dl", "deadline"),
            Map.entry("ev", "event"),
            Map.entry("ls", "list"),
            Map.entry("mk", "mark"),
            Map.entry("um", "unmark"),
            Map.entry("del", "delete"),
            Map.entry("f", "find"),
            Map.entry("h", "help"),
            Map.entry("q", "bye")
    );

    /** Returns the lower-cased command word at the start of {@code input}, normalized to a canonical command. */
    public static String commandWord(String input) {
        assert input != null : "input must not be null";
        String trimmed = input.trim();
        int sp = trimmed.indexOf(' ');
        String head = (sp == -1) ? trimmed.toLowerCase() : trimmed.substring(0, sp).toLowerCase();
        // normalize aliases
        return ALIASES.getOrDefault(head, head);
    }

    /** Returns the remainder of {@code input} after the given {@code cmd}. */
    public static String argTail(String input, String cmd) {
        assert input != null && cmd != null : "inputs must not be null";
        if (input.length() <= cmd.length()) return "";
        return input.substring(cmd.length()).trim();
    }

    /** Parses a 1-based index; returns -1 if invalid. */
    public static int parseIndex(String s) {
        try { return Integer.parseInt(s.trim()); }
        catch (NumberFormatException e) { return -1; }
    }
}
