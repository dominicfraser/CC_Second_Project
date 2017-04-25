package example.codeclan.com.todolist;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
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
import example.codeclan.com.todolist.helpers.PriorityComparator;

public class TasksSortedPriorityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_sorted_priority);

        setTitle("ToDo List > Priority");
        Log.d(getClass().toString(), "onCreate for TasksSortedPriorityActivity");

        DatabaseHandler db = new DatabaseHandler(this);
        TaskList taskList = db.getTaskList();

        ArrayList<Task> taskListasArrayList = taskList.getList();
        Log.d(getClass().toString(), "got ArrayList<Task> size " + taskListasArrayList.size());

        Collections.sort(taskListasArrayList, new PriorityComparator());
        Collections.sort(taskListasArrayList, new CompletedComparator());

        TasksAllListAdapter tasksAllListAdapter = new TasksAllListAdapter(this, taskListasArrayList);

        Log.d(getClass().toString(), "made Adapter");

        ListView listView = (ListView) findViewById(R.id.tasks_sorted_priority_list);

        listView.setAdapter(tasksAllListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.all_list_menu, menu);
        menu.findItem(R.id.all_list_menu_sort_priority).setVisible(false);

        // Add SearchWidget.
        SearchManager searchManager = (SearchManager) getSystemService( Context.SEARCH_SERVICE );
        SearchView searchView = (SearchView) menu.findItem( R.id.menu_search ).getActionView();
        searchView.setSearchableInfo( searchManager.getSearchableInfo( getComponentName() ) );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all_list_new_task:
                Intent i1 = new Intent(this,NewTaskActivity.class);
                this.startActivity(i1);
                return true;
            case R.id.all_list_menu_sort_all:
                Intent i2 = new Intent(this,TasksAllListActivity.class);
                this.startActivity(i2);
                return true;
            case R.id.all_list_menu_sort_expiry:
                Intent i3 = new Intent(this,TasksSortedExpiryActivity.class);
                this.startActivity(i3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

