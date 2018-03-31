package com.example.user.login;

/**
 * Created by USER on 3/31/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

//import java.util.ArrayList;package app.com.example.android.nodalofficer2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Bhupi on 3/23/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<EachProblem> mDataSet;
    static Context ctx;
    static DatabaseReference toSend;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView desc;
        CardView cardView;
        String ref;
        String num;
        public ViewHolder(View v){

            super(v);
            v.setOnClickListener(this);


            title=v.findViewById(R.id.complainTitle);
            desc=v.findViewById(R.id.complainDesc);
            cardView=v.findViewById(R.id.cardView);
        }

        @Override
        public void onClick(View v) {
            Intent i=new Intent(ctx,completeComplain.class);
            i.putExtra("Number",num);
            i.putExtra("Title",title.getText());
            i.putExtra("Desc",desc.getText());
            i.putExtra("Ref",ref);
            ctx.startActivity(i);

        }
    }

    public MyAdapter(ArrayList<EachProblem> myDataSet,Context ctx){
        mDataSet = myDataSet;
        this.ctx=ctx;
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_adapter,parent,false);


        return new MyAdapter.ViewHolder(view);
    }

    @Override
    public void  onBindViewHolder(ViewHolder holder,int position){
        EachProblem problem=mDataSet.get(position);
        String title=problem.title;
        String desc=problem.desc;
        String read=problem.read;
        holder.num=problem.number;
        holder.ref=problem.reference;
        holder.title.setText(title);
        holder.desc.setText(desc);
        if(read.equalsIgnoreCase("true")){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#43A047"));
        }else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#F44336"));
        }

    }

    @Override

    public int getItemCount(){
        return mDataSet.size();
    }


}

