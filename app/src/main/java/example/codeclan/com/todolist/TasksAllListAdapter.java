package example.codeclan.com.todolist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Date;

import example.codeclan.com.todolist.database.DatabaseHandler;

/**
 * Created by user on 20/04/2017.
 */

class TasksAllListAdapter extends ArrayAdapter<Task> {

    public TasksAllListAdapter(Context context, ArrayList<Task> tasks){
        super(context, 0, tasks);

    }

    @Override
    public View getView(final int position, View listItemView, ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_in_list, parent, false);
        }

        final DatabaseHandler db = new DatabaseHandler(parent.getContext());

        Task task = getItem(position);
        final Task task_in_db = db.getTask(position+1);

        TextView task_in_list_short_description = (TextView) listItemView.findViewById(R.id.task_in_list_short_description);
        task_in_list_short_description.setText(task.getShortDescription().toString());

        final TextView task_in_list_timestamp = (TextView) listItemView.findViewById(R.id.task_in_list_timestamp);
        Date timeStamp = new Date(task.getTimeStamp());
        task_in_list_timestamp.setText(String.valueOf(task.getCompleted()));



        Switch switch_task_completed = (Switch) listItemView.findViewById(R.id.switch_task_completed);
        setSwitchToRelfectDB(switch_task_completed, task);

        switch_task_completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    task_in_db.setCompleted(true);
                    db.updateTasK(task_in_db);
                    task_in_list_timestamp.setText(String.valueOf(task_in_db.getCompleted()));
                } else {
                    // The toggle is disabled
                    task_in_db.setCompleted(false);
                    db.updateTasK(task_in_db);
                    task_in_list_timestamp.setText(String.valueOf(task_in_db.getCompleted()));
                }
            }
        });


        listItemView.setTag(task);

        return listItemView;
    }


    public void setSwitchToRelfectDB(Switch swit, Task task){
        if (task.getCompleted()){
            swit.setChecked(true);
        }
        else{
            swit.setChecked(false);
        }

    }

}
