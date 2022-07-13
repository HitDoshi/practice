package com.example.story;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyApp extends Application {

    public static LayoutInflater inflater;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context= getApplicationContext();
        DB_PATH = "/data/data/"+getPackageName()+"/databases/";
        copyDataBaseFromAssets(context);
    }


    private String DB_PATH;
    private String DB_NAME = "funny_stories_english.db";

    private void copyDataBaseFromAssets(Context context) {
        try {

            File folder = context.getDatabasePath("databases");

            if (!folder.exists())
                if (folder.mkdirs()) folder.delete();

            //Open your local db as the input stream
            InputStream myInput = context.getAssets().open(DB_NAME);

            // Path to the just created empty db
            String outFileName = DB_PATH + DB_NAME;

            //Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();

        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }
}




