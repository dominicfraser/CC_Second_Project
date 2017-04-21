package example.codeclan.com.todolist;

import android.renderscript.RenderScript;

import java.util.ArrayList;

/**
 * Created by user on 20/04/2017.
 */

public class Task {

    private long id;
    private long timeStamp;

    private String shortDescription;
    private String longDescription;
    private PriorityLevel priority;
    private boolean completed;
    private long expiryDate;

    public Task(long id, long timeStamp, String shortDescription, String longDescription, PriorityLevel priority,
                boolean completed, long expiryDate) {
        this.timeStamp = System.currentTimeMillis();
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.priority = priority;
        this.id = id;
        this.completed = completed;
        this.expiryDate = expiryDate;

    }

    public Task(String shortDescription, String longDescription, PriorityLevel priority, long expiryDate){
        this(0, System.currentTimeMillis(), shortDescription, longDescription, priority, false, expiryDate);
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

    public long getExpiryDate(){
        return this.expiryDate;
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

    public void setExpiryDate(long expiryDate){
        this.expiryDate = expiryDate;
    }

}
