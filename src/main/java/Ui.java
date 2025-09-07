package larry;

import java.util.List;

public class Ui {

    public void showGreeting() {
        System.out.println(" Hello! I'm Larry");
        System.out.println(" What can I do for you?");
    }
    public void showExit() {
        System.out.println(" Bye. Hope to see you again soon!");
    }
    public void showList(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
    public void showAdded(Task t, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + size + " task" + (size == 1 ? "" : "s") + " in the list.");
    }
    public void showMarked(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t);
    }
    public void showUnmarked(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t);
    }
    public void showDeleted(Task t, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + size + " task" + (size == 1 ? "" : "s") + " in the list.");
    }
    public void showError(String msg) {
        System.out.println(msg);
    }
}
