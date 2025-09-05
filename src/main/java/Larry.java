package larry;
import java.util.Scanner;
public class Larry {


    private static void greet() {
        System.out.println(" Hello! I'm Larry");
        System.out.println(" What can I do for you?");
    }

    private static void exit() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    private static void runEchoLoop() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println(input);
        }
    }

    public static void main(String[] args) {
        greet();
        runEchoLoop();
        exit();
    }
}
