package example.codeclan.com.todolist.helpers;

import java.util.Comparator;

import example.codeclan.com.todolist.Task;

/**
 * Created by user on 25/04/2017.
 */

public class PriorityComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        String o1pri = o1.getPriority().toString();
        String o2pri = o2.getPriority().toString();

        switch (o1pri) {
            case "HIGH":
                if (!o2pri.equals("HIGH")) {
                    return -1;
                }
            case "MEDIUM":
                if (!o2pri.equals("MEDIUM")) {
                    return (o2pri.equals("HIGH") ? 1 : -1);
                }
            case "LOW":
                if (o2pri.equals("LOW")) {
                    return 1;
                }
        }
        return 0;

    }
}