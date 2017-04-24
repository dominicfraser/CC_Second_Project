package example.codeclan.com.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 24/04/2017.
 */

public class DatePickerFragmentEditTask extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//  Read the passed bundle from the activity
        Bundle editTaskBundle = this.getArguments();
        Long dateOfTask = editTaskBundle.getLong("dateOfTask");

// Set current date to default date in dateOfTask
        final Calendar c = Calendar.getInstance();

        c.setTime(new Date((dateOfTask)));

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

// Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener)getActivity(),
                year, month, day);
    }
}
