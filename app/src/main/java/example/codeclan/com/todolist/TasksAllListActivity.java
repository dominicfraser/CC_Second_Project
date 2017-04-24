package example.codeclan.com.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Collections;

import example.codeclan.com.todolist.R;
import example.codeclan.com.todolist.TaskList;
import example.codeclan.com.todolist.TasksAllListAdapter;
import example.codeclan.com.todolist.database.DatabaseHandler;
import example.codeclan.com.todolist.helpers.CompletedComparator;
import example.codeclan.com.todolist.helpers.TimeStampComparator;

public class TasksAllListActivity extends AppCompatActivity {

    private TaskList taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_all_list);
        setTitle("ToDo List > All");
        Log.d(getClass().toString(), "onCreate for TasksAllListActivity");

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d(getClass().toString(), "DB handler made");
        TaskList taskList = db.getTaskList();
        Log.d(getClass().toString(), "tasklist made");
//sort list
        ArrayList<Task> taskListasArrayList = taskList.getList();
        Collections.sort(taskListasArrayList, new TimeStampComparator());
        Collections.sort(taskListasArrayList, new CompletedComparator());


        Intent intent = getIntent();

        TasksAllListAdapter tasksAllListAdapter = new TasksAllListAdapter(this, taskListasArrayList);

        ListView listView = (ListView) findViewById(R.id.tasks_all_list);

        listView.setAdapter(tasksAllListAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.all_list_menu, menu);
        menu.findItem(R.id.all_list_menu_sort_all).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all_list_new_task:
                Intent i1 = new Intent(this,NewTaskActivity.class);
                this.startActivity(i1);
                return true;
            case R.id.all_list_menu_sort_expiry:
                Intent i2 = new Intent(this,TasksSortedExpiryActivity.class);
                this.startActivity(i2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
