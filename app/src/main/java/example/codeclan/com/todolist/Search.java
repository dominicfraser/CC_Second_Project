package example.codeclan.com.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import example.codeclan.com.todolist.database.DatabaseHandler;
import example.codeclan.com.todolist.helpers.CompletedComparator;
import example.codeclan.com.todolist.helpers.TimeStampComparator;

/**
 * Created by user on 25/04/2017.
 */

public class Search extends Activity {
    private TaskList taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_all_list);
//        setTitle("ToDo List > All");
        Log.d(getClass().toString(), "onCreate for Search");

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d(getClass().toString(), "DB handler made");
        TaskList taskList = db.getTaskList();
        Log.d(getClass().toString(), "tasklist made");

        ArrayList<Task> taskListasArrayList = taskList.getList();


        //sort list
        Collections.sort(taskListasArrayList, new TimeStampComparator());
        Collections.sort(taskListasArrayList, new CompletedComparator());

        Intent intent = getIntent();

        TasksAllListAdapter tasksAllListAdapter = new TasksAllListAdapter(this, taskListasArrayList);

        ListView listView = (ListView) findViewById(R.id.tasks_all_list);

        listView.setAdapter(tasksAllListAdapter);

}
