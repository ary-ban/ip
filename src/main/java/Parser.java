package larry;

public class Parser {

    public static String commandWord(String input) {
        String trimmed = input.trim();
        int sp = trimmed.indexOf(' ');
        return (sp == -1) ? trimmed.toLowerCase() : trimmed.substring(0, sp).toLowerCase();
    }

    public static String argTail(String input, String cmd) {
        if (input.length() <= cmd.length()) return "";
        return input.substring(cmd.length()).trim();
    }

    public static int parseIndex(String s) {
        try { return Integer.parseInt(s.trim()); }
        catch (NumberFormatException e) { return -1; }
    }
}
