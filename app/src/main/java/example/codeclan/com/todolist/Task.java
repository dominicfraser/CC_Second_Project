package example.codeclan.com.todolist;

import android.renderscript.RenderScript;

import java.util.ArrayList;

/**
 * Created by user on 20/04/2017.
 */

public class Task {

    private long id;
    private String shortDescription;
    private String longDescription;
    private PriorityLevel priority;
    private boolean completed;
    private long timeStamp;

    public Task(long id, String shortDescription, String longDescription, PriorityLevel priority, boolean completed, long timeStamp) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.priority = priority;
        this.id = id;
        this.completed = completed;
        this.timeStamp = System.currentTimeMillis();

    }

    public Task(String shortDescription, String longDescription, PriorityLevel priority){
        this(0, shortDescription, longDescription, priority, false, System.currentTimeMillis());
    }

    public long getId(){
        return this.id;
    }

    public String getShortDescription(){
        return this.shortDescription;
    }

    public String getLongDescription(){
        return this.longDescription;
    }

    public PriorityLevel getPriority(){
        return this.priority;
    }

    public boolean getCompleted(){
        return this.completed;
    }

    public long getTimeStamp(){
        return this.timeStamp;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setShortDescription(String description){
        this.shortDescription = description;
    }

    public void setLongDescription(String description){
        this.longDescription = description;
    }

    public void setPriority(PriorityLevel priority){
        this.priority = priority;
    }

    public void setCompleted(boolean status){
        this.completed = status;
    }

    public void setTimeStamp(long timeStamp){
        this.timeStamp = System.currentTimeMillis();
    }

}
