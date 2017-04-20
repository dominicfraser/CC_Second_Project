package example.codeclan.com.todolist;

import java.util.ArrayList;

/**
 * Created by user on 20/04/2017.
 */

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(){
        this.list = new ArrayList<Task>();
    }

    public ArrayList<Task> getList(){
        return new ArrayList<Task>(list);
    }

    public void addToList(Task task){
        list.add(task);
    }

    public int sizeOfList(){
        return list.size();
    }

    public Task entry(int index) {
        return list.get(index);
    }
}
