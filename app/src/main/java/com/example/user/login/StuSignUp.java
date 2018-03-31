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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class StuSignUp extends AppCompatActivity {
    FirebaseDatabase db=FirebaseDatabase.getInstance();

    String s4,s5,s6;
    Button b;
    EditText e1,e2,e3,e4,e5;
    TextView t1;

    final List<String> years = new ArrayList<String>();
    final List<String> sections = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_sign_up);

        Spinner yearspinner = findViewById(R.id.spinneryear);
        Spinner sectionspinner = findViewById(R.id.spinnersection);
        b=(Button)findViewById(R.id.stunext);
        e1=(EditText) findViewById(R.id.stutName);//user
        e2=(EditText) findViewById(R.id.stupass);//pass
        e3=(EditText) findViewById(R.id.stuemail);//email
        t1=(TextView) findViewById(R.id.studentheading);
        e5=(EditText)findViewById(R.id.sturoll);//roll

        //t1=e4.getText().toString();

        years.add("First");
        years.add("Second");
        years.add("Third");
        years.add("Fourth");

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(StuSignUp.this, android.R.layout.simple_spinner_item, years);
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

        ArrayAdapter<String> sectionAdapter = new ArrayAdapter<String>(StuSignUp.this, android.R.layout.simple_spinner_item, sections);
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
    public static class Complain{
        String Username;
        String Password;
        String email;
        public Complain(String title,String desc,String email){
            this.Username=title;
            this.Password=desc;
            this.email=email;
        }
    }
    public void stsubmit(View v)
    {
        s5=e5.getText().toString();
        DatabaseReference rf=db.getReference("Student/year/"+s4+"/"+s6+"/User Id/"+s5);

        String s1=e1.getText().toString();
        String s2=e2.getText().toString();
        String s3=e3.getText().toString();
        rf.setValue(new Complain(s1,s2,s3));
    }

}
