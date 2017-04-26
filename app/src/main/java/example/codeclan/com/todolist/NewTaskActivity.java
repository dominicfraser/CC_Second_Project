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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import example.codeclan.com.todolist.database.DatabaseHandler;
import example.codeclan.com.todolist.helpers.UIHelper;

public class NewTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText short_description;
    private EditText long_description;
    private TextView text_date_picker;
    private TextView text_date_picker_hidden;
    private RadioGroup new_task_radio_button_group;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        short_description = (EditText) findViewById(R.id.new_short_description);
        long_description = (EditText) findViewById(R.id.new_long_description);

//        setTitle("ToDo List > New");
//
//        Log.d(getClass().toString(), "onCreate for NewTaskActivity");
//
//        short_description = (EditText) findViewById(R.id.new_short_description);
//        long_description = (EditText) findViewById(R.id.new_long_description);
//        text_date_picker = (TextView) findViewById(R.id.text_date_picker);
//        text_date_picker_hidden = (TextView) findViewById(R.id.text_date_picker_hidden);
//        new_task_radio_button_group = (RadioGroup) findViewById(R.id.new_task_radio_button_group);
//        new_task_radio_button_group.check(R.id.radio_medium_new);
//        Log.d(getClass().toString(), "made views");
//
//        long datemilliseconds = System.currentTimeMillis()+ (86400 * 7 * 1000);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        Date resultdate = new Date(datemilliseconds);
//        String formattedDate = sdf.format(resultdate);
//    // set default date to one week after todays date
//        text_date_picker.setText(formattedDate);
//        text_date_picker_hidden.setText(String.valueOf(datemilliseconds));
//        Log.d(getClass().toString(), "set default date");

        UIHelper.hideKeyBoardWhenNotFocused(this, short_description);
        UIHelper.hideKeyBoardWhenNotFocused(this, long_description);
    }

    public void onSaveNewTaskClick(View view){
        int selectedPri = new_task_radio_button_group.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedPri);
        String selectedPriString = radioButton.getText().toString().toUpperCase();
        PriorityLevel priorit = Enum.valueOf(PriorityLevel.class, selectedPriString);

        Log.d(getClass().toString(), "got priority " + selectedPriString);


        String short_descript = short_description.getText().toString();
        String long_descript = long_description.getText().toString();
        long expiryDt = Long.valueOf(text_date_picker_hidden.getText().toString()).longValue();

        Log.d(getClass().toString(), short_descript + "priority: " + priorit + "long" + expiryDt );

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d(getClass().toString(), "about to insert...");
        db.addTask(new Task(short_descript,long_descript,priorit,expiryDt));

        Intent intent = new Intent(this,TasksAllListActivity.class);
        startActivity(intent);
    }

    public void onNewTaskDateClick(View view){
        DialogFragment picker = new DatePickerFragmentNewTask();
        picker.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(c.getTime());

        text_date_picker.setText(formattedDate);
        text_date_picker_hidden.setText(String.valueOf(c.getTimeInMillis()));
        Log.d(getClass().toString(), "set new date");

    }




}
