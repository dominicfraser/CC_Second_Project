package example.codeclan.com.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
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
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_in_list_old, parent, false);
        }
        Log.d(getClass().toString(), "getView for TasksAllListAdapter");
//        set up tasks and db
        final DatabaseHandler db = new DatabaseHandler(parent.getContext());
        final Task task = getItem(position);

        Log.d(getClass().toString(), "got task from position " + position);
        Log.d(getClass().toString(), "highest id in db " + db.getHighestID());
        Log.d(getClass().toString(), "timestap =  " + task.getTimeStamp());

        int task_id = (int) task.getId();
        final Task task_in_db = db.getTask(task_id);

        Log.d(getClass().toString(), "id = " + (task_in_db.getId()));

        Log.d(getClass().toString(), "got task from db");

//        find and set up views
        final ImageView arrow_for_main_list = (ImageView) listItemView.findViewById(R.id.arrow_for_main_list);
        final ImageView edit_in_all_list = (ImageView) listItemView.findViewById(R.id.edit_in_all_list);
        final ImageView delete_in_all_list = (ImageView) listItemView.findViewById(R.id.delete_in_all_list);

        final TextView task_in_list_short_description = (TextView) listItemView.findViewById(R.id.task_in_list_short_description);
        task_in_list_short_description.setText(task.getShortDescription().toString());


        TextView task_in_list_expiry = (TextView) listItemView.findViewById(R.id.task_in_list_timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(new Date(task.getExpiryDate()));
        task_in_list_expiry.setText("Expires: " + formattedDate);


        final TextView task_in_list_long_description = (TextView) listItemView.findViewById(R.id.task_in_list_long_description);
        task_in_list_long_description.setText(task.getLongDescription().toString());

        LinearLayout side_bar_main_list = (LinearLayout) listItemView.findViewById(R.id.side_bar_main_list);

        Switch switch_task_completed = (Switch) listItemView.findViewById(R.id.switch_task_completed);
        setSwitchToReflectDB(switch_task_completed, task);

//        actions using views
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
//  click on short description
        task_in_list_short_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vis = task_in_list_long_description.getVisibility();
                if (vis == View.GONE){
                    task_in_list_long_description.setVisibility(View.VISIBLE);
                    arrow_for_main_list.setImageResource(R.drawable.collapse_arrow);
                }
                else if(vis == View.VISIBLE) {
                    task_in_list_long_description.setVisibility(View.GONE);
                    arrow_for_main_list.setImageResource(R.drawable.expand_arrow);

                }
            }
        });
//  click on expand/collapse icon
        side_bar_main_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vis = task_in_list_long_description.getVisibility();
                if (vis == View.GONE){
                    task_in_list_long_description.setVisibility(View.VISIBLE);
                    arrow_for_main_list.setImageResource(R.drawable.collapse_arrow);
                }
                else if(vis == View.VISIBLE) {
                    task_in_list_long_description.setVisibility(View.GONE);
                    arrow_for_main_list.setImageResource(R.drawable.expand_arrow);

                }
            }
        });
//  click on edit icon
        edit_in_all_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(parent.getContext(),EditTaskActivity.class);
                editIntent.putExtra("taskId", String.valueOf(task_in_db.getId()));
                parent.getContext().startActivity(editIntent);
            }
        });
//  click on delete icon
        final View.OnClickListener myCLDeleteSnackbar = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addTask(task_in_db);
                Intent redirectDeleteUndo = new Intent(parent.getContext(), TasksAllListActivity.class);
                parent.getContext().startActivity(redirectDeleteUndo);
                Log.d(getClass().toString(), String.valueOf(task_in_db.getId()));
                Log.d(getClass().toString(), String.valueOf(task.getId()));
            }
        };

        delete_in_all_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                db.deleteTask(task_in_db);
                Snackbar.make(vi, "Task deleted", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", myCLDeleteSnackbar)
                        .show();
                edit_in_all_list.setImageResource(R.drawable.ic_edit_grey);
                edit_in_all_list.setClickable(false);
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
