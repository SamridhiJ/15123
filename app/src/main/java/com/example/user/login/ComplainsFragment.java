package com.example.user.login;

/**
 * Created by USER on 3/31/2018.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ComplainsFragment extends Fragment {
    FirebaseDatabase db;
    DatabaseReference myRef;
    RecyclerView list;



    ArrayList<EachProblem> problems;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container.removeAllViewsInLayout();


        problems=new ArrayList<>();
        View v= inflater.inflate(R.layout.fragment_complains,container,false);
        list=v.findViewById(R.id.my_recycler_view);
        db=FirebaseDatabase.getInstance();

        getProblem();

        return v;
    }

    public void getProblem(){

        addProblem();






    }

    public void addProblem(){
        myRef=db.getReference("Complains/Unsolved/");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //  Iterable<DataSnapshot>=dataSnapshot.getChildren();
                Log.d("First log",dataSnapshot.toString());
                Iterable<DataSnapshot> children=dataSnapshot.getChildren();

                for(DataSnapshot sp:children) {
                    Iterable<DataSnapshot> complains = sp.getChildren();

                    for (DataSnapshot eachComplain : complains) {
                        String referencetoSend=eachComplain.getRef().toString();
                        String num=eachComplain.getKey();
                        Log.d("Second log", sp.toString());
                        Iterable<DataSnapshot> fChildren = eachComplain.getChildren();
                        String desc="" , title="";
                        String read="";
                        for (DataSnapshot sp1 : fChildren) {
                            String data = sp1.getKey();
                            if (data.equalsIgnoreCase("Desc")) {
                                desc = new String((String) sp1.getValue());
                            } else if(data.equalsIgnoreCase("Title")) {
                                title = new String((String) sp1.getValue());
                            }else{
                                read=new String((String)sp1.getValue());
                            }

                            Log.d("Third log", sp1.toString());
                        }
                        problems.add(new EachProblem(title, desc,read,referencetoSend,num));
                    }
                }
                list.setLayoutManager(new LinearLayoutManager(getContext()));

                list.setAdapter(new MyAdapter(problems,getContext()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




}
