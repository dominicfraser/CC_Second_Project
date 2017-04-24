package example.codeclan.com.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import example.codeclan.com.todolist.database.DatabaseHandler;

public class TasksSortedCompletedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_sorted_completed);
        Log.d(getClass().toString(), "onCreate for TasksSortedCompletedActivity");

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d(getClass().toString(), "DB handler made");
        TaskList taskList = db.getTaskList();
        Log.d(getClass().toString(), "tasklist made");

        ArrayList<Task> taskListSortedCompleted = taskList.sortCompleted();
// refactor all this to use as a second sorted list, above and below

        Intent intent = getIntent();

        TasksAllListAdapter tasksAllListAdapter = new TasksAllListAdapter(this, taskList.getList());

        ListView listView = (ListView) findViewById(R.id.tasks_sorted_completed_list);

        listView.setAdapter(tasksAllListAdapter);
    }
}
