package com.example.user.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class UserType3 extends AppCompatActivity {
    ImageButton admin, nodal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type3);
        getSupportActionBar().hide();
        admin=(ImageButton)findViewById(R.id.admin);
        nodal=(ImageButton)findViewById(R.id.nodal);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(UserType3.this, SystemLogin.class);
                startActivity(intent);
            }
        });

    nodal.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(UserType3.this,NodalMainActivity.class);
            startActivity(i);
        }
    });



    }




}
