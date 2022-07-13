package com.example.story;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static String DBNAME;
    public static String TABLE;
    public static final String DBLOCATION = "/data/data" + MyApp.context.getPackageName() + "/databases/";
    private Context zcontext;
    private SQLiteDatabase database;

    public DatabaseHelper(Context context, String DBname) {
        super(context, DBNAME, null, 1);
        DBNAME = DBname ;
        TABLE = "funny_stories";
        zcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void OpenDatabase() {

        String DBpath = zcontext.getDatabasePath(DBNAME).getPath();
        if (database != null && database.isOpen()) {
            return;
        }

        database = SQLiteDatabase.openDatabase(DBpath, null, SQLiteDatabase.OPEN_READWRITE);
    }


    public void closedatabase(){
        if(database!=null){
            database.close();
        }
    }
    public ArrayList<StoryModel>getstory()
    {

        StoryModel storyModel=null;
        ArrayList<StoryModel>arrayList=new ArrayList<StoryModel>();
        OpenDatabase();
        Cursor cursor=database.rawQuery("select*from "+TABLE,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            storyModel = new StoryModel(cursor.getString(1), cursor.getString(2));
            arrayList.add(storyModel);
            cursor.moveToNext();
        }

        cursor.close();
        closedatabase();
        return arrayList;
    }

}
