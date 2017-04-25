package example.codeclan.com.todolist;

import android.app.Activity;
import android.app.SearchManager;
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

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_sort);
        setTitle("ToDo List > Search");
        Log.d(getClass().toString(), "onCreate for Search");
//  Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    private void doMySearch(String query) {
        Log.d(getClass().toString(), "AHHH query: " + query);

        db = new DatabaseHandler(this);
        Log.d(getClass().toString(), "DB handler made");
        Log.d(getClass().toString(), "Running search...");

        TaskList taskList = db.search(query);
        Log.d(getClass().toString(), "tasklist made");

        ArrayList<Task> taskListAsArrayList = taskList.getList();

// sort list
        Collections.sort(taskListAsArrayList, new TimeStampComparator());
        Collections.sort(taskListAsArrayList, new CompletedComparator());

        Intent intent = getIntent();

        TasksAllListAdapter tasksAllListAdapter = new TasksAllListAdapter(this, taskListAsArrayList);

        ListView listView = (ListView) findViewById(R.id.tasks_sorted_search);

        listView.setAdapter(tasksAllListAdapter);

    }

}
