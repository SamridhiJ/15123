package com.example.user.login;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Attendance extends AppCompatActivity {

    String roll;
    String username="";
    RecyclerView pr;
    ArrayList<Attendance1> list = new ArrayList<Attendance1>();
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);



        firebaseDatabase =FirebaseDatabase.getInstance();
        DatabaseReference rf = firebaseDatabase.getReference("Student/year/Second/A/User Id");
        rf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

           Iterable<DataSnapshot> children  = dataSnapshot.getChildren();
           for (DataSnapshot sp:children){
               roll= sp.getKey();
               Iterable<DataSnapshot> fchildren = sp.getChildren();
               //String username="";
               for(DataSnapshot sp1:fchildren)
               {
                   String data = sp1.getKey();
                   if(data.equalsIgnoreCase("Username"))
                   {
                       username = new String((String)sp1.getValue());
                   }
               }
               list.add(new Attendance1(username,roll));
           }



                 pr = (RecyclerView)findViewById(R.id.recycle);
                pr.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                pr.setAdapter(new ProgramingAdapt(list));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //String []languages = {"java",".NET","php","node.js","android","sih","python","rubi","swift","smalltalk","r","c/c++"};

    }
    int currentAttendance;
    public void submitAttendance(View v){

        Intent it=getIntent();
        String loc=it.getStringExtra("Location");
        View radioButtonView;

        for(int i=0;i<list.size();++i){
            radioButtonView=pr.findViewHolderForAdapterPosition(i).itemView;
            TextView rollNoTextView=radioButtonView.findViewById(R.id.studentRollNo);
            String rollNo=rollNoTextView.getText().toString();
            RadioGroup radioGroup=radioButtonView.findViewById(R.id.attendace_radio_group);
            int selectedRadioButtonId=radioGroup.getCheckedRadioButtonId();

            DatabaseReference attendanceRef=firebaseDatabase.getReference(loc+"/"+rollNo);
            attendanceRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    currentAttendance=(int)dataSnapshot.getValue();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            if(selectedRadioButtonId==R.id.radioPresent){
                attendanceRef.setValue("Present");
            }else{
                attendanceRef.setValue("Absent");
            }

        }


    }


}
