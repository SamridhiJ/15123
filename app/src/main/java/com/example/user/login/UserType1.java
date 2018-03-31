package com.example.user.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class UserType1 extends AppCompatActivity {
    private ImageButton b1;
    private ImageButton b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type1);
        b1= (ImageButton)findViewById(R.id.button1);
        b2=(ImageButton)findViewById(R.id.button2);
        getSupportActionBar().hide();
    }
    public void opensysuser(View view)
    {
        Intent intent=new Intent(UserType1.this,UserType3.class);
        startActivity(intent);
    }
    public void openappuser(View view)
    {
        Intent intent=new Intent(UserType1.this,UserType2.class);
        startActivity(intent);
    }
}
