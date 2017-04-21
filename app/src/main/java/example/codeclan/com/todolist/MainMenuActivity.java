package example.codeclan.com.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Log.d(getClass().toString(), "onCreate for MainMenuActivity");
    }

    public void onSeeTasksClick(View view) {
        Log.d(getClass().toString(), "onSeeTasksClick clicked");

        Intent intent = new Intent(this,TasksAllListActivity.class);
        startActivity(intent);
    }

    public void onNewTasksClick(View View){
        Log.d(getClass().toString(), "onNewTasksClick clicked");

        Intent intent = new Intent(this,NewTaskActivity.class);
        startActivity(intent);
    }


}
