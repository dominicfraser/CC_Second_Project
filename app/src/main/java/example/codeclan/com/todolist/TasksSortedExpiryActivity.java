package example.codeclan.com.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import example.codeclan.com.todolist.database.DatabaseHandler;
import example.codeclan.com.todolist.helpers.CompletedComparator;
import example.codeclan.com.todolist.helpers.ExpiryDateComparator;

public class TasksSortedExpiryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_sorted_expiry);
        Log.d(getClass().toString(), "onCreate for TasksSortedExpiryActivity");

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d(getClass().toString(), "DB handler made");
        TaskList taskList = db.getTaskList();
        Log.d(getClass().toString(), "tasklist made");

        ArrayList<Task> taskListasArrayList = taskList.getList();
        Collections.sort(taskListasArrayList, new ExpiryDateComparator());
        Collections.sort(taskListasArrayList, new CompletedComparator());

        Intent intent = getIntent();

        TasksAllListAdapter tasksAllListAdapter = new TasksAllListAdapter(this, taskListasArrayList);

        ListView listView = (ListView) findViewById(R.id.tasks_sorted_completed_list);

        listView.setAdapter(tasksAllListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.all_list_menu, menu);
        menu.findItem(R.id.all_list_menu_sort_expiry).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all_list_menu_sort_all:
                Intent i = new Intent(this,TasksAllListActivity.class);
                this.startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
