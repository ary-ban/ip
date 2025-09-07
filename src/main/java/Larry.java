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
    private static void printList(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
    private static void confirmAdded(List<Task> tasks, Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.");
    }

    private static void runTaskLoop() {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage("data/larry.txt");
        List<Task> tasks = storage.load();

        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printList(tasks);
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
                storage.save(tasks);
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
                storage.save(tasks);
            } else if (input.toLowerCase().startsWith("todo")) {
                String desc = input.length() > 4 ? input.substring(4).trim() : "";
                if (desc.isEmpty()) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    continue;
                }
                Task t = new Todo(desc);
                tasks.add(t);
                confirmAdded(tasks, t);
                storage.save(tasks);
            } else if (input.toLowerCase().startsWith("delete ")) {
                int idx = parseIndex(input.substring(7));
                if (!isValidIndex(idx, tasks)) {
                    System.out.println("Invalid task index.");
                    continue;
                }
                Task removed = tasks.remove(idx - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + removed);
                System.out.println("Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.");
                storage.save(tasks);
            } else if (input.toLowerCase().startsWith("deadline")) {
                String body = input.length() > 8 ? input.substring(8).trim() : "";
                int byIdx = body.toLowerCase().indexOf("/by");
                String desc = (byIdx == -1) ? body : body.substring(0, byIdx).trim();
                String by = (byIdx == -1) ? "" : body.substring(byIdx + 3).trim();
                if (desc.isEmpty()) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                    continue;
                }
                if (byIdx == -1 || by.isEmpty()) {
                    System.out.println("OOPS!!! Please specify a due time using '/by <when>'.");
                    continue;
                }
                Task t = new Deadline(desc, by);
                tasks.add(t);
                confirmAdded(tasks, t);
                storage.save(tasks);
            } else if (input.toLowerCase().startsWith("event")) {
                String body = input.length() > 5 ? input.substring(5).trim() : "";
                int fromIdx = body.toLowerCase().indexOf("/from");
                int toIdx = body.toLowerCase().indexOf("/to");
                String desc = (fromIdx == -1) ? body : body.substring(0, fromIdx).trim();
                String from = (fromIdx == -1) ? "" : body.substring(fromIdx + 5, (toIdx == -1 ? body.length() : toIdx)).trim();
                String to = (toIdx == -1) ? "" : body.substring(toIdx + 3).trim();
                if (desc.isEmpty()) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                    continue;
                }
                if (fromIdx == -1 || from.isEmpty()) {
                    System.out.println("OOPS!!! Please specify a start time using '/from <start>'.");
                    continue;
                }
                if (toIdx == -1 || to.isEmpty()) {
                    System.out.println("OOPS!!! Please specify an end time using '/to <end>'.");
                    continue;
                }
                Task t = new Event(desc, from, to);
                tasks.add(t);
                confirmAdded(tasks, t);
                storage.save(tasks);
            } else if (!input.isEmpty()) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means.");
            }
        }
    }

    public static void main(String[] args) {
        greet();
        runTaskLoop();
        exit();
    }
}
