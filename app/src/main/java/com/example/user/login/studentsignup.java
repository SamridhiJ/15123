package com.example.user.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import  android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public  class studentsignup extends AppCompatActivity {
   FirebaseDatabase db=FirebaseDatabase.getInstance();

    String s1,s2,s3,s4,s5,s6;
    Button b;
    EditText e1,e2,e3,e4,e5;
    TextView t1;
    Spinner yearspinner = findViewById(R.id.spinneryear);
    Spinner sectionspinner = findViewById(R.id.spinnersection);
    final List<String> years = new ArrayList<String>();
    final List<String> sections = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentsignup);


        b=(Button)findViewById(R.id.stunext);
        e1=(EditText) findViewById(R.id.stutName);//user
        e2=(EditText) findViewById(R.id.stupass);//pass
        e3=(EditText) findViewById(R.id.stuemail);//email
        t1=(TextView) findViewById(R.id.studentheading);
        e5=(EditText)findViewById(R.id.sturoll);//roll
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        s3=e3.getText().toString();
        //t1=e4.getText().toString();
        s5=e5.getText().toString();
        years.add("First");
        years.add("Second");
        years.add("Third");
        years.add("Fourth");

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(studentsignup.this, android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearspinner.setAdapter(yearAdapter);
      yearspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              s4=(String)adapterView.getItemAtPosition(i);
          }

          @Override
          public void onNothingSelected(AdapterView<?> adapterView) {

          }
      });
        sections.add("A");
        sections.add("B");
        sections.add("C");
        sections.add("D");

        ArrayAdapter<String> sectionAdapter = new ArrayAdapter<String>(studentsignup.this, android.R.layout.simple_spinner_item, sections);
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectionspinner.setAdapter(sectionAdapter);
        sectionspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s6= (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void stsubmit(View v)
    {
        /*DatabaseReference rf=db.getReference("Student/"+s4+"/Second/"+s6+"/User Id");
        rf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.child(s5);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


    }

}
