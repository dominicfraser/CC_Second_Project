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
        this.id = 0;
        this.completed = false;

    }

    public int getId(){
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

    public void setId(int id){
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
            
}
