package example.codeclan.com.todolist.helpers;

import java.util.Comparator;
import java.util.Date;

import example.codeclan.com.todolist.Task;

/**
 * Created by user on 24/04/2017.
 */

public class TimeStampComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        Date o1date = new Date (o1.getTimeStamp());
        Date o2date = new Date (o2.getTimeStamp());

        return ( o2date.compareTo(o1date));
    }

}
