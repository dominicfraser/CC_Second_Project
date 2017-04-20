package example.codeclan.com.todolist;

import android.renderscript.RenderScript;

/**
 * Created by user on 20/04/2017.
 */

public class Task {

    private int id;
    private String shortDescription;
    private String longDescription;
    private PriorityLevel priority;
    private boolean completed;

    public Task(String shortDescription, String longDescription, PriorityLevel priority){
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.priority = priority;
    }


}
