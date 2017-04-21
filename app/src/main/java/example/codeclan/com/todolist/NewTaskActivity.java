package example.codeclan.com.todolist;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;


import java.util.Calendar;

import example.codeclan.com.todolist.database.DatabaseHandler;

public class NewTaskActivity extends AppCompatActivity {

    private EditText short_description;
    private EditText long_description;
    private Spinner priority_spinner;

    private EditText text_date_picker;
    private DatePicker new_task_date_picker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        Log.d(getClass().toString(), "onCreate for NewTaskActivity");

        short_description = (EditText) findViewById(R.id.new_short_description);
        long_description = (EditText) findViewById(R.id.new_long_description);
        priority_spinner = (Spinner) findViewById(R.id.new_priority);

        new_task_date_picker = (DatePicker) findViewById(R.id.new_task_date_picker);

        UIHelper.hideKeyBoardWhenNotFocused(this, short_description);
        UIHelper.hideKeyBoardWhenNotFocused(this, long_description);


    }

    public void onSaveNewTaskClick(View view){
        String priority = priority_spinner.getSelectedItem().toString();

        String short_descript = short_description.getText().toString();
        String long_descript = long_description.getText().toString();
        PriorityLevel priorit = Enum.valueOf(PriorityLevel.class, priority.toString());

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d(getClass().toString(), "about to insert...");
        db.addTask(new Task(short_descript,long_descript,priorit));

        Intent intent = new Intent(this,TasksAllListActivity.class);
        startActivity(intent);
    }

    public void onNewTaskDateClick(View view){
        DialogFragment picker = new DatePickerFragment();
        picker.show(getFragmentManager(), "datePicker");
    }



}
