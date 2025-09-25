package larry.util;

public final class Strings {
    private Strings() {}
    public static String lines(String... parts) {
        return String.join(System.lineSeparator(), parts);
    }
}