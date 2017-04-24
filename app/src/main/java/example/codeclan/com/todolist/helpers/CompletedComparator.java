package example.codeclan.com.todolist.helpers;

import java.util.Comparator;

import example.codeclan.com.todolist.Task;

/**
 * Created by user on 24/04/2017.
 */

public class CompletedComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        int o1int = (o1.getCompleted()) ? 1 : 0;
        int o2int = (o2.getCompleted()) ? 1 : 0;

        return ( o1int>o2int ? 1 : (o1int==o2int ? 0 : -1));
    }

}
