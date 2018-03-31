package com.example.user.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelectAttendance extends AppCompatActivity {
    private Spinner sp3,sp4;
    String s1,s2;

    DataSnapshot finalSnapshot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_attendance);
        sp3= (Spinner) findViewById(R.id.spinner);
        sp4 = (Spinner) findViewById(R.id.spinner2);
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref=firebaseDatabase.getReference("YearSection");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                //yearSection=dataSnapshot;
                final List<String> areas = new ArrayList<String>();
                Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                for(DataSnapshot ds : it){
                    areas.add((String)ds.getKey());
                }
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(SelectAttendance.this, android.R.layout.simple_spinner_item, areas);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp3.setAdapter(areasAdapter);
                sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                        s1=(String) adapterView.getItemAtPosition(pos);
                        //           DatabaseReference sectionRef=firebaseDatabase.getReference("YearSection/"+s1);
                        Toast.makeText(SelectAttendance.this,adapterView.getItemAtPosition(pos)+" is selected",Toast.LENGTH_SHORT).show();
                        DatabaseReference stref = firebaseDatabase.getReference("YearSection/"+s1);
                        //prob();
                        stref.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // Is better to use a List, because you don't know the size
                                // of the iterator returned by dataSnapshot.getChildren() to
                                // initialize the array
                                final List<String> section = new ArrayList<String>();
                                finalSnapshot=dataSnapshot;
                                Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                                for(DataSnapshot ds : it){
                                    section.add((String)ds.getKey());

                                }
                                ArrayAdapter<String> eachsection = new ArrayAdapter<String>(SelectAttendance.this, android.R.layout.simple_spinner_item, section);
                                eachsection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp4.setAdapter(eachsection);
                                sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                                        s2=(String) adapterView.getItemAtPosition(pos);
                                        //DatabaseReference sectionRef=firebaseDatabase.getReference("YearSection/"+s1);

                                        Toast.makeText(SelectAttendance.this,adapterView.getItemAtPosition(pos)+" is selected",Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }

                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    public void goToAttendance(View v){
        DataSnapshot sectionSnapshot=finalSnapshot.child(s2);
        String loc=sectionSnapshot.getValue().toString();
        Intent i=new Intent(SelectAttendance.this,Attendance.class);
        i.putExtra("Location",loc);
        startActivity(i);

    }
}
