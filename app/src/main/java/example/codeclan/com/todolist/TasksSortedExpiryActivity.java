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

public class TasksSortedExpiryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_sorted_expiry);
        setTitle("ToDo List > Expiry");
        Log.d(getClass().toString(), "onCreate for TasksSortedExpiryActivity");

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d(getClass().toString(), "DB handler made");
        TaskList taskList = db.getTaskList();
        Log.d(getClass().toString(), "tasklist made");

        ArrayList<Task> taskListasArrayList = taskList.getList();
        Collections.sort(taskListasArrayList, new ExpiryDateComparator());
        Collections.sort(taskListasArrayList, new CompletedComparator());

        TasksAllListAdapter tasksAllListAdapter = new TasksAllListAdapter(this, taskListasArrayList);

        ListView listView = (ListView) findViewById(R.id.tasks_sorted_completed_list);

        listView.setAdapter(tasksAllListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.all_list_menu, menu);
        menu.findItem(R.id.all_list_menu_sort_expiry).setVisible(false);

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
                Intent i = new Intent(this,TasksAllListActivity.class);
                this.startActivity(i);
                return true;
            case R.id.all_list_menu_sort_priority:
                Intent i3 = new Intent(this,TasksSortedPriorityActivity.class);
                this.startActivity(i3);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
