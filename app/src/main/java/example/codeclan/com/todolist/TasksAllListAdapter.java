package example.codeclan.com.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 20/04/2017.
 */

class TasksAllListAdapter extends ArrayAdapter<Task> {

    public TasksAllListAdapter(Context context, ArrayList<Task> tasks){
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_in_list, parent, false);
        }

        Task task = getItem(position);

        TextView task_in_list_short_description = (TextView) listItemView.findViewById(R.id.task_in_list_short_description);
        task_in_list_short_description.setText(task.getShortDescription().toString());


        listItemView.setTag(task);

        return listItemView;
    }
}
