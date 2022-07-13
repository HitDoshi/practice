package com.example.story;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.story.Adapter.EnglishStoryAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class StroyListActivity extends AppCompatActivity {

    ArrayList<StoryModel> arrayList = new ArrayList<>();
    RecyclerView rv;
    EnglishStoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroy_list);
        rv = findViewById(R.id.rv);

        getSupportActionBar().hide();

        DatabaseHelper zDHELPER=new DatabaseHelper(getApplicationContext(),"funny_stories_english.db");
        File database=getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);

        if(database.exists()==false){

            zDHELPER.getReadableDatabase();

            if (!copydatabase(getApplicationContext())){

                return;
            }
        }
        arrayList=zDHELPER.getstory();

        adapter = new EnglishStoryAdapter(arrayList,getApplicationContext());

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

    }

    public boolean copydatabase(Context context){

        return Boolean.parseBoolean(null);
    }
}