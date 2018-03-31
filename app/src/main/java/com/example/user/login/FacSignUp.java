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

public  class FacSignUp extends AppCompatActivity {
    FirebaseDatabase db=FirebaseDatabase.getInstance();

    String s4,s5,s6;
    Button b;
    EditText e1,e2,e3,e4,e5;
    TextView t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_sign_up);


        b=(Button)findViewById(R.id.stunext);
        e1=(EditText) findViewById(R.id.stutName);//user
        e2=(EditText) findViewById(R.id.stupass);//pass
        e3=(EditText) findViewById(R.id.stuemail);//email
        t1=(TextView) findViewById(R.id.studentheading);
        e5=(EditText)findViewById(R.id.sturoll);//id

        //t1=e4.getText().toString();


    }
    public static class Complain{
        String Username;
        String Password;
        String Email;
        public Complain(String title,String desc,String email){
            this.Username=title;
            this.Password=desc;
            this.Email=email;
        }
    }
    public void facSubmit(View v)
    {
        s5=e5.getText().toString();
        DatabaseReference rf=db.getReference("Faculty/UserId/"+s5);

        String s1=e1.getText().toString();
        String s2=e2.getText().toString();
        String s3=e3.getText().toString();
        rf.setValue(new Complain(s1,s2,s3));
    }

}
