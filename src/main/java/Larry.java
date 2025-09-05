package larry;
public class Larry {

    private static void greet() {
        System.out.println(" Hello! I'm Larry");
        System.out.println(" What can I do for you?");
    }

    private static void exit() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        greet();
        exit();
    }
}
