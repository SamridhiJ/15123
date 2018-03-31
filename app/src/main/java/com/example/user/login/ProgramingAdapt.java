package com.example.user.login;

/**
 * Created by USER on 3/25/2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USER on 3/22/2018.
 */

public class ProgramingAdapt extends RecyclerView.Adapter<ProgramingAdapt.programingviewholder>{

    ArrayList<Attendance1> data;
    public ProgramingAdapt(ArrayList<Attendance1> data)
    {
        this.data=data;
    }

    @Override
    public programingviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_layout,parent,false);




        return new  programingviewholder(view);
    }

    @Override
    public void onBindViewHolder(programingviewholder holder, int position) {
       Attendance1 obj=data.get(position);
       String firstText = obj.getfirstText();
       String secondTex= obj.getsecondText();
        holder.textView.setText(firstText);
        holder.textView2.setText(secondTex);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class programingviewholder extends RecyclerView.ViewHolder {

        TextView textView2;
        TextView textView;

        public programingviewholder(View itemView) {
            super(itemView);
            textView2=(TextView) itemView.findViewById(R.id.studentRollNo);
            textView=(TextView)itemView.findViewById(R.id.studentName);

        }
    }
}

