package example.codeclan.com.todolist.helpers;

import java.util.Comparator;
import java.util.Date;

import example.codeclan.com.todolist.Task;

/**
 * Created by user on 24/04/2017.
 */

public class ExpiryDateComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        Date o1date = new Date (o1.getExpiryDate());
        Date o2date = new Date (o2.getExpiryDate());

        return ( o1date.compareTo(o2date));
    }
}
