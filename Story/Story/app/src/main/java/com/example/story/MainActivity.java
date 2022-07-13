package com.example.story;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.story.Adapter.EnglishStoryAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ImageView share,rate,more;
    Button letsstart,removeads;
    ArrayList<StoryModel>arrayList=new ArrayList<StoryModel>();
    EnglishStoryAdapter adapter;
    //RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();


        share=findViewById(R.id.share);
        rate=findViewById(R.id.rate);
        more=findViewById(R.id.more);
        letsstart=findViewById(R.id.letsstart);
        removeads=findViewById(R.id.removeads);
         //rv=findViewById(R.id.rv);




        DatabaseHelper zDHELPER=new DatabaseHelper(MainActivity.this,"funny_stories_english.db");
        File database=getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);

        if(database.exists()==false){

            zDHELPER.getReadableDatabase();

            if (!copydatabase(MainActivity.this)){

                return;
            }
        }



        arrayList=zDHELPER.getstory();
//        adapter=new EnglishStoryAdapter(arrayList);
//        adapter.notifyDataSetChanged();
//        rv.setAdapter(adapter);



        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Sharing application!");
                startActivity(Intent.createChooser(intent, "Share Via"));
            }
        });



        rate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=my packagename"));
                startActivity(i);
            }
        });



        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=my packagename"));
                startActivity(i);
            }
        });


        letsstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.clear();
                arrayList=zDHELPER.getstory();
                Log.d("size",arrayList.size()+"");
                Intent i=new Intent(MainActivity.this, StroyListActivity.class);
                startActivity(i);

            }
        });


        removeads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,ChoicePlanActivity.class);
                startActivity(i);
            }
        });
    }

    public boolean copydatabase(Context context){

        try {
            InputStream inputStream=context.getAssets().open(DatabaseHelper.DBNAME);
            String outfilename=DatabaseHelper.DBLOCATION+DatabaseHelper.DBNAME;
            File f=new File(outfilename);
            f.getParentFile().mkdirs();
            OutputStream outputStream=new FileOutputStream(outfilename);
            byte[]buffer=new byte[1024];
            int length=0;
            while ((length=inputStream.read(buffer))>=0){
                outputStream.write(buffer,0,length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (IOException e) {
           return false;
        }
    }

}