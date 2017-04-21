package example.codeclan.com.todolist;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by user on 21/04/2017.
 */

public class UIHelper {

    public static void hideKeyBoardWhenNotFocused(final Context context, View view){
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                        hideKeyboard(context, v);
                }
            }
        });
    }


    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
