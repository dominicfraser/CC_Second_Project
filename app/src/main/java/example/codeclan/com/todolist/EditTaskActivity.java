package example.codeclan.com.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;

import example.codeclan.com.todolist.database.DatabaseHandler;

public class EditTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        Intent intent = getIntent();
        String idString = intent.getStringExtra("taskId");
        DatabaseHandler db = new DatabaseHandler(this);

        Task task = db.getTask(Integer.parseInt(idString));

        EditText edit_short_description = (EditText) findViewById(R.id.edit_short_description);
        EditText edit_long_description = (EditText) findViewById(R.id.edit_long_description);
//        radio group?
        TextView edit_text_date_picker = (TextView) findViewById(R.id.edit_text_date_picker);

        edit_short_description.setHint(task.getShortDescription());
        edit_long_description.setHint(task.getLongDescription());

        Date date = new Date(task.getExpiryDate());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(date);
        edit_text_date_picker.setHint(formattedDate);

        db.updateTasK(task);
    }
}
