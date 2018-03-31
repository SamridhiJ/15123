package com.example.user.login;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
   // Animation splash;
    ImageView logo;
    Button to_app;


    private int SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = (ImageView) findViewById(R.id.logo);
        to_app=(Button)findViewById(R.id.toapp);
        to_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Splash.this,UserType1.class);
                startActivity(in);
            }
        });
        //splash = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splashanim);
        //logo.startAnimation(splash);
        getSupportActionBar().hide();
        }
}