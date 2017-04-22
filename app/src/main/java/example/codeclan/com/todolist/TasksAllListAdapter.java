package example.codeclan.com.todolist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

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
    public View getView(final int position, View listItemView, final ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_in_list, parent, false);
        }

        final DatabaseHandler db = new DatabaseHandler(parent.getContext());

        Task task = getItem(position);
        final Task task_in_db = db.getTask(position+1);

        final TextView task_in_list_short_description = (TextView) listItemView.findViewById(R.id.task_in_list_short_description);
        task_in_list_short_description.setText(task.getShortDescription().toString());


        TextView task_in_list_timestamp = (TextView) listItemView.findViewById(R.id.task_in_list_timestamp);
        Date timeStamp = new Date(task.getTimeStamp());
        task_in_list_timestamp.setText(String.valueOf(task.getCompleted()));

        final TextView task_in_list_long_description = (TextView) listItemView.findViewById(R.id.task_in_list_long_description);
        task_in_list_long_description.setText(task.getLongDescription().toString());


        Switch switch_task_completed = (Switch) listItemView.findViewById(R.id.switch_task_completed);
        setSwitchToReflectDB(switch_task_completed, task);

        switch_task_completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    task_in_db.setCompleted(true);
                    db.updateTasK(task_in_db);
                } else {
                    // The toggle is disabled
                    task_in_db.setCompleted(false);
                    db.updateTasK(task_in_db);
                }
            }
        });

        task_in_list_short_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vis = task_in_list_long_description.getVisibility();
                if (vis == View.GONE){
                    ScaleAnimation anim = new ScaleAnimation(1, 1, 0, 1);
                    task_in_list_long_description.setVisibility(View.VISIBLE);
                }
                else if(vis == View.VISIBLE) {
                    task_in_list_long_description.setVisibility(View.GONE);
                }
            }
        });


        listItemView.setTag(task);
        return listItemView;
    }


    public void setSwitchToReflectDB(Switch swit, Task task){
        if (task.getCompleted()){
            swit.setChecked(true);
        }
        else{
            swit.setChecked(false);
        }

    }


}
