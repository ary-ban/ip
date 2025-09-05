package larry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Larry {


    private static void greet() {
        System.out.println(" Hello! I'm Larry");
        System.out.println(" What can I do for you?");
    }

    private static void exit() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    private static void runTaskLoop() {
        Scanner sc = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (!input.isEmpty()) {
                tasks.add(input);
                System.out.println("added: " + input);
            }
        }
    }

    public static void main(String[] args) {
        greet();
        runTaskLoop();
        exit();
    }
}
