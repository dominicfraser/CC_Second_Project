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


public class EditTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Task task;
    private EditText edit_short_description;
    private EditText edit_long_description;
    private TextView edit_text_date_picker;
    private TextView edit_text_date_picker_hidden;
    private RadioGroup edit_task_radio_button_group;
    private RadioButton radioButton;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Log.d(getClass().toString(), "onCreate for activity_edit_task");

        Intent intent = getIntent();
        String idString = intent.getStringExtra("taskId");
        db = new DatabaseHandler(this);
        Log.d(getClass().toString(), "got intent and made db");

        task = db.getTask(Integer.parseInt(idString));
        Log.d(getClass().toString(), "made new task object");


        edit_short_description = (EditText) findViewById(R.id.edit_short_description);
        edit_long_description = (EditText) findViewById(R.id.edit_long_description);
        edit_task_radio_button_group = (RadioGroup) findViewById(R.id.edit_task_radio_button_group);
        checkPriorityLevelFromTask();
        Log.d(getClass().toString(), "checked and set priority level");


        edit_text_date_picker = (TextView) findViewById(R.id.edit_text_date_picker);
        edit_text_date_picker_hidden = (TextView) findViewById(R.id.edit_text_date_picker_hidden);

        edit_short_description.setHint(task.getShortDescription());
        edit_long_description.setHint(task.getLongDescription());

        Date date = new Date(task.getExpiryDate());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(date);
        edit_text_date_picker.setHint(formattedDate);
        edit_text_date_picker_hidden.setText(String.valueOf(task.getExpiryDate()));
        Log.d(getClass().toString(), "set date picker hint");

        UIHelper.hideKeyBoardWhenNotFocused(this, edit_short_description);
        UIHelper.hideKeyBoardWhenNotFocused(this, edit_long_description);
    }

    public void onSaveEditTaskClick(View view){
        //        update task
        Task updatedTask = checkForUpdates();
        db.updateTasK(updatedTask);
        Intent intent = new Intent(this,TasksAllListActivity.class);
        startActivity(intent);
    }

    private void checkPriorityLevelFromTask() {
        if (task.getPriority().toString().equals("HIGH")){
            edit_task_radio_button_group.check(R.id.radio_high_edit);
        } else if (task.getPriority().toString().equals("MEDIUM")){
            edit_task_radio_button_group.check(R.id.radio_medium_edit);
        } else if (task.getPriority().toString().equals("LOW")){
            edit_task_radio_button_group.check(R.id.radio_low_edit);
        }
    }


    public void onEditTaskDateClick(View view){
        Date date = new Date(task.getExpiryDate());
        Bundle editTaskBundle = new Bundle();
        editTaskBundle.putLong("dateOfTask",date.getTime());

//        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
//        int yearFromTask = Integer.parseInt(sdfYear.format(date));
//        SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
//        int monthFromTask = Integer.parseInt(sdfMonth.format(date));
//        SimpleDateFormat sdfDay  = new SimpleDateFormat("dd");
//        int dayFromTask = Integer.parseInt(sdfDay.format(date));

        DialogFragment picker = new DatePickerFragmentEditTask();
        picker.setArguments(editTaskBundle);
        picker.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(c.getTime());

        edit_text_date_picker.setText(formattedDate);
        edit_text_date_picker_hidden.setText(String.valueOf(c.getTimeInMillis()));
        Log.d(getClass().toString(), "set new date");

    }

    public Task checkForUpdates() {
        String possible_new_short_des = edit_short_description.getText().toString();
        String short_des = new String();
        if (possible_new_short_des.equals("")) {
            short_des = task.getShortDescription();
        } else {
            short_des = possible_new_short_des;
        }

        String possible_new_long_des = edit_long_description.getText().toString();
        String long_des = new String();
        if (possible_new_long_des.equals("")){
            long_des = task.getLongDescription();
        } else {
            long_des = possible_new_long_des;
        }

        int selectedPri = edit_task_radio_button_group.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedPri);
        String selectedPriString = radioButton.getText().toString().toUpperCase();
        PriorityLevel pri = Enum.valueOf(PriorityLevel.class, selectedPriString);

        long expiry = Long.valueOf(edit_text_date_picker_hidden.getText().toString()).longValue();

        task.updateTask(short_des, long_des, pri, expiry);

        return task;
    }
}
