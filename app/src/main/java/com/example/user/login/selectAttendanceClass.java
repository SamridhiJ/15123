package com.example.user.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class selectAttendanceClass extends Fragment {
    private Spinner sp3,sp4;
    String s1,s2;
    DataSnapshot yearSection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_select_attendance_class, container, false);
        View v= inflater.inflate(R.layout.fragment_select_attendance_class,container,false);


        sp3= v.findViewById(R.id.spinner);
        sp4 = v.findViewById(R.id.spinner2);
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref=firebaseDatabase.getReference("YearSection");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                yearSection=dataSnapshot;
                final List<String> areas = new ArrayList<String>();
                Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                for(DataSnapshot ds : it){
                    areas.add((String)ds.getKey());
                }
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, areas);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp3.setAdapter(areasAdapter);
                sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                        s1=(String) adapterView.getItemAtPosition(pos);
             //           DatabaseReference sectionRef=firebaseDatabase.getReference("YearSection/"+s1);
                        Toast.makeText(getContext(),adapterView.getItemAtPosition(pos)+" is selected",Toast.LENGTH_SHORT).show();
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
        DatabaseReference stref = firebaseDatabase.getReference("YearSection/"+s1);
        //prob();
        stref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> section = new ArrayList<String>();
                Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                for(DataSnapshot ds : it){
                    section.add((String)ds.getKey());
                }
                ArrayAdapter<String> eachsection = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, section);
                eachsection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp4.setAdapter(eachsection);
                sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                        s2=(String) adapterView.getItemAtPosition(pos);
                        //DatabaseReference sectionRef=firebaseDatabase.getReference("YearSection/"+s1);
                        Toast.makeText(getContext(),adapterView.getItemAtPosition(pos)+" is selected",Toast.LENGTH_SHORT).show();
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

return v;




    }




}
