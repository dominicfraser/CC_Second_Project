package example.codeclan.com.todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.RenderScript;

import example.codeclan.com.todolist.PriorityLevel;
import example.codeclan.com.todolist.Task;
import example.codeclan.com.todolist.TaskList;

/**
 * Created by user on 20/04/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ToDo_Database";

    private static final String TABLE_TASKLIST = "taskList";

    private static final String KEY_ID = "id";
    private static final String KEY_SHORTDESCRIPTION = "shortDescription";
    private static final String KEY_LONGDESCRIPTION = "longDescription";
    private static final String KEY_PRIORITYLEVEL = "priority";
    private static final String KEY_COMPLETED = "completed";
    private static final String KEY_TIMESTAMP = "timeStamp";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
///CREATE TABLES
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TASKLIST_TABLE = "CREATE TABLE " + TABLE_TASKLIST + "(" + KEY_ID
                + " INTEGER PRIMARY KEY," + KEY_SHORTDESCRIPTION + " TEXT," +
                KEY_LONGDESCRIPTION + " TEXT," + KEY_PRIORITYLEVEL + " TEXT," +
                KEY_COMPLETED + " BOOLEAN," + KEY_TIMESTAMP + "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + ")";
        db.execSQL(CREATE_TASKLIST_TABLE);
    }
//UPGRADE DATABASE
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKLIST);
        onCreate(db);
    }
//ADD TASK
    public void addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_SHORTDESCRIPTION, task.getShortDescription());
        values.put(KEY_LONGDESCRIPTION, task.getLongDescription());
        values.put(KEY_PRIORITYLEVEL, task.getPriority().toString());
        values.put(KEY_COMPLETED, task.getCompleted());

        db.insert(TABLE_TASKLIST, null, values);
        db.close();
    }
//GET/READ TASK
    public Task getTask(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TASKLIST, new String[] {KEY_ID, KEY_SHORTDESCRIPTION,
        KEY_LONGDESCRIPTION, KEY_PRIORITYLEVEL, KEY_COMPLETED, KEY_TIMESTAMP}, KEY_ID + "=?",
                new String[] {String.valueOf(id) }, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();


        Task task = new Task(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), Enum.valueOf(PriorityLevel.class,
                cursor.getString(3)), cursor.getInt(4)!=0, cursor.getLong(5) );
        return task;
    }
// GET/READ ALL TASKS
    public TaskList getTaskList(){
        String query = "SELECT * FROM " + TABLE_TASKLIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        TaskList list = new TaskList();

        while(cursor.moveToNext()){
            list.addToList(new Task(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), Enum.valueOf(PriorityLevel.class,
                    cursor.getString(3)), cursor.getInt(4)!=0, cursor.getLong(5) ));
        }

        cursor.close();
        return list;
    }

//COUNT TASKS
    public int getTaskCount(){
        String countQuery = "SELECT * FROM " + TABLE_TASKLIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
//UPDATE SINGLE TASK
    public int updateTasK(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SHORTDESCRIPTION, task.getShortDescription());
        values.put(KEY_LONGDESCRIPTION, task.getLongDescription());
        values.put(KEY_PRIORITYLEVEL, task.getPriority().toString());
        values.put(KEY_COMPLETED, task.getCompleted());

        return db.update(TABLE_TASKLIST, values, KEY_ID + " = ?",
                new String[] {String.valueOf(task.getId())});
    }
//DELETE TASK
    public void deleteTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKLIST, KEY_ID + " = ?", new String[]
                { String.valueOf(task.getId())});
        db.close();
    }

}
