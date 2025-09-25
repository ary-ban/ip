package larry.parser;

/**
 * Parses raw user input into command words and arguments.
 */
public class Parser {

    /** Returns the lower-cased command word at the start of {@code input}. */
    public static String commandWord(String input) {
        assert input != null : "input must not be null";
        String trimmed = input.trim();
        int sp = trimmed.indexOf(' ');
        return (sp == -1) ? trimmed.toLowerCase() : trimmed.substring(0, sp).toLowerCase();
    }

    /** Returns the remainder of {@code input} after the given {@code cmd}. */
    public static String argTail(String input, String cmd) {
        assert input != null && cmd != null : "inputs must not be null";
        if (input.length() <= cmd.length()) {
            return "";
        }
        return input.substring(cmd.length()).trim();
    }

    /** Parses a 1-based index; returns -1 if invalid. */
    public static int parseIndex(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
