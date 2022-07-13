package com.example.story.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.story.Display;
import com.example.story.R;
import com.example.story.StoryModel;

import java.util.ArrayList;
import java.util.List;

public class EnglishStoryAdapter extends RecyclerView.Adapter<EnglishStoryAdapter.Holder> {

    List<StoryModel>storylist;

    Context context;

    public EnglishStoryAdapter(List<StoryModel> storylist, Context context) {
        this.storylist = storylist;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v1=LayoutInflater.from(parent.getContext()).inflate(R.layout.storyitem,parent,false);
        return new Holder(v1);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.storytitle.setText(storylist.get(position).getStroytitle());
        /*holder.story.setText((storylist.get(position).getStroy()));*/

        Log.d("s",storylist.size()+"");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Display.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return storylist.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView storytitle,story;
        CardView cardView;

        public Holder(@NonNull View itemView) {
            super(itemView);


            cardView = itemView.findViewById(R.id.cardview);
            storytitle=itemView.findViewById(R.id.storytitle);
            story=itemView.findViewById(R.id.story);
        }
    }
}
