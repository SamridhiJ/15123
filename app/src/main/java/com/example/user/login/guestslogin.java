package com.example.user.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//import com.google.android.gms.internal.se;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class guestslogin extends AppCompatActivity {
    private EditText name;
    private EditText passwrd;
    //private TextView info;
    private Button login;
    private Button guest;
    //private int count = 5;
    String s3,s4,s5,s6;
    Spinner s1,s2;
    final List<String> years=new ArrayList<String>();
    final List<String> sections=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestslogin);
        name = (EditText) findViewById(R.id.name);
        passwrd = (EditText)findViewById(R.id.password) ;
        s1=findViewById(R.id.yearspinner);
        s2=findViewById(R.id.sectionspinner);
        years.add("First");
        years.add("Second");
        years.add("Third");
        years.add("Fourth");
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(guestslogin.this, R.layout.support_simple_spinner_dropdown_item,years);
        yearAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        s1.setAdapter(yearAdapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s5=(String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sections.add("A");
        sections.add("B");
        sections.add("C");
        sections.add("D");
        ArrayAdapter<String> sectionAdapter = new ArrayAdapter<String>(guestslogin.this, R.layout.support_simple_spinner_dropdown_item,sections);
        sectionAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        s2.setAdapter(sectionAdapter);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s6=(String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // password = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.login);
        guest=(Button)findViewById(R.id.gbutton);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(guestslogin.this, guestwelcome.class);
                startActivity(intent1);

            }
        });


        //info.setText("no of attempts remaining: 5");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s3=name.getText().toString();
                s4=passwrd.getText().toString();
                Validate(s3,s4 );
            }
        });



    }
    public void init()
    {

    }

    private void Validate(String name, final String password) {
        FirebaseDatabase db;
        db=FirebaseDatabase.getInstance();
        final DatabaseReference ref=db.getReference("Student/year/"+s5+"/"+s6+"/User Id"+"/"+s3);
        final DatabaseReference ref2=db.getReference("Student/year/"+s5+"/"+s6+"/User Id/"+s3+"/Password");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object val = dataSnapshot.getValue();
                if(val==null)
                {
                    Toast.makeText(guestslogin.this,"Wrong Id",Toast.LENGTH_SHORT).show();
                }else
                {
                    ref2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot1) {
                            String pass = dataSnapshot1.getValue(String.class);
                            if(pass.equals(password))
                            {
                                Intent intent=new Intent(guestslogin.this,guestwelcome.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(guestslogin.this,"Wrong Id",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}


