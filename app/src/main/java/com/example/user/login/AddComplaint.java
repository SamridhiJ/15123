package com.example.user.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AddComplaint extends AppCompatActivity {
    DatabaseReference rf;
    String s;
    EditText e;
    FirebaseDatabase db;
Button b;
myListener myListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaint);
    e=(EditText)findViewById(R.id.comptype);
    b=(Button)findViewById(R.id.addcomp);
    }
   public void complaintsubmit(View view)
   {
       db=FirebaseDatabase.getInstance();
       rf=db.getReference("CTypeNo");
       myListener=new myListener();
       rf.addValueEventListener(myListener);
       s=e.getText().toString();

   }
   class myListener implements ValueEventListener{

       @Override
       public void onDataChange(DataSnapshot dataSnapshot) {
            Long  num=(Long)dataSnapshot.getValue();
            ++num;
            rf.removeEventListener(myListener);
            rf.setValue(num);
            rf=db.getReference("Complain types/Type "+num);
            rf.setValue(s);
       }

       @Override
       public void onCancelled(DatabaseError databaseError) {

       }
   }
}
