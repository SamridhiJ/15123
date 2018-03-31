package com.example.user.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public  class Nexttologin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nexttologin);
    }


    public  void addStudent(View v)
    {
        Intent i=new Intent(Nexttologin.this,StuSignUp.class);
        startActivity(i);
    }
public void addFaculty(View v)
{
Intent i =new Intent(Nexttologin.this,FacSignUp.class);
        startActivity(i);
}
public void addNodal(View view)
{
Intent i  =new Intent(Nexttologin.this,NodalSignUp.class);
startActivity(i);
}
public void addComplaint(View view)
{
Intent i=new Intent(Nexttologin.this,AddComplaint.class);
startActivity(i);
}
public void analyzer(View v)
{

}
}
