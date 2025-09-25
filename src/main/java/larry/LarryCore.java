package larry;

import larry.model.Deadline;
import larry.model.Event;
import larry.model.Task;
import larry.model.TaskList;
import larry.model.Todo;
import larry.parser.Parser;
import larry.storage.Storage;
import larry.ui.Ui;

import java.util.List;
import java.util.stream.Collectors;


public class LarryCore {
    private final Ui ui = new Ui();
    private final Storage storage = new Storage("data/larry.txt");
    private final TaskList tasks = new TaskList(storage.load());
    private boolean shouldExit = false;

    public boolean shouldExit() {
        return shouldExit;
    }

    public String getResponse(String input) {
        String line = input == null ? "" : input.trim();
        if (line.equalsIgnoreCase("bye")) {
            shouldExit = true;
            return " Bye. Hope to see you again soon!";
        }

        String cmd = Parser.commandWord(line);
        switch (cmd) {
        case "list": {
            return "Here are the tasks in your list:\n" + numbered(tasks.asList());
        }
        case "mark": {
            int idx = Parser.parseIndex(Parser.argTail(line, "mark"));
            if (!tasks.isValidIndex(idx)) {
                return "Invalid task index.";
            }
            Task t = tasks.get(idx);
            t.markDone();
            storage.save(tasks.asList());
            return "Nice! I've marked this task as done:\n  " + t;
        }
        case "unmark": {
            int idx = Parser.parseIndex(Parser.argTail(line, "unmark"));
            if (!tasks.isValidIndex(idx)) {
                return "Invalid task index.";
            }
            Task t = tasks.get(idx);
            t.markUndone();
            storage.save(tasks.asList());
            return "OK, I've marked this task as not done yet:\n  " + t;
        }
        case "delete": {
            int idx = Parser.parseIndex(Parser.argTail(line, "delete"));
            if (!tasks.isValidIndex(idx)) {
                return "Invalid task index.";
            }
            Task removed = tasks.delete(idx);
            storage.save(tasks.asList());
            return "Noted. I've removed this task:\n  " + removed
                    + "\nNow you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.";
        }
        case "todo": {
            String desc = Parser.argTail(line, "todo");
            if (desc.isEmpty()) {
                return "OOPS!!! The description of a todo cannot be empty.";
            }
            Task t = new Todo(desc);
            tasks.add(t);
            storage.save(tasks.asList());
            return "Got it. I've added this task:\n  " + t
                    + "\nNow you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.";
        }
        case "deadline": {
            String body = Parser.argTail(line, "deadline");
            int byIdx = body.toLowerCase().indexOf("/by");
            String desc = (byIdx == -1) ? body : body.substring(0, byIdx).trim();
            String by = (byIdx == -1) ? "" : body.substring(byIdx + 3).trim();
            if (desc.isEmpty()) {
                return "OOPS!!! The description of a deadline cannot be empty.";
            }
            if (byIdx == -1 || by.isEmpty()) {
                return "OOPS!!! Please specify a due time using '/by <when>'.";
            }
            Task t = new Deadline(desc, by);
            tasks.add(t);
            storage.save(tasks.asList());
            return "Got it. I've added this task:\n  " + t
                    + "\nNow you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.";
        }
        case "event": {
            String body = Parser.argTail(line, "event");
            int fromIdx = body.toLowerCase().indexOf("/from");
            int toIdx = body.toLowerCase().indexOf("/to");
            String desc = (fromIdx == -1) ? body : body.substring(0, fromIdx).trim();
            String from = (fromIdx == -1)
                    ? ""
                    : body.substring(fromIdx + 5, (toIdx == -1 ? body.length() : toIdx)).trim();
            String to = (toIdx == -1) ? "" : body.substring(toIdx + 3).trim();
            if (desc.isEmpty()) {
                return "OOPS!!! The description of an event cannot be empty.";
            }
            if (fromIdx == -1 || from.isEmpty()) {
                return "OOPS!!! Please specify a start time using '/from <start>'.";
            }
            if (toIdx == -1 || to.isEmpty()) {
                return "OOPS!!! Please specify an end time using '/to <end>'.";
            }
            Task t = new Event(desc, from, to);
            tasks.add(t);
            storage.save(tasks.asList());
            return "Got it. I've added this task:\n  " + t
                    + "\nNow you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.";
        }
        default:
            if (line.isEmpty()) {
                return ""; // ignore empty line
            }
            return "OOPS!!! I'm sorry, but I don't know what that means.";
        }
    }

    private static String numbered(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}
