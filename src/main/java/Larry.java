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
    private static int parseIndex(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    private static boolean isValidIndex(int idx, List<Task> tasks) {
        return idx >= 1 && idx <= tasks.size();
    }
    private static void runTaskLoop() {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (input.toLowerCase().startsWith("mark ")) {
                int idx = parseIndex(input.substring(5));
                if (!isValidIndex(idx, tasks)) {
                    System.out.println("Invalid task index.");
                    continue;
                }
                Task t = tasks.get(idx - 1);
                t.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + t);
            } else if (input.toLowerCase().startsWith("unmark ")) {
                int idx = parseIndex(input.substring(7));
                if (!isValidIndex(idx, tasks)) {
                    System.out.println("Invalid task index.");
                    continue;
                }
                Task t = tasks.get(idx - 1);
                t.markUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + t);
            } else if (!input.isEmpty()) {
                Task t = new Task(input);
                tasks.add(t);
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
