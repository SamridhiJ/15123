package com.example.user.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import java.util.jar.Attributes;

public class StudentSignIn extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Info;
    private int count=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_in);

        Name =(EditText)findViewById(R.id.etname);
        Password=(EditText)findViewById(R.id.etpass);
        Login = (Button)findViewById(R.id.btn);
        Info = (TextView)findViewById(R.id.textView2);
        Info.setText("Number of counts remaining : 5");
        Login.setOnClickListener(

                new Button.OnClickListener(){
                    public void onClick(View view) {
                        validate(Name.getText().toString(),Password.getText().toString());

                    }
                }
        );

    }

    private void validate(String username,String password) {
        if(username.equals("Admin")&&password.equals("1234"))
        {
            Intent intent=new Intent(StudentSignIn.this,NextTosignIn.class);
            startActivity(intent);
        }
        else {
            int i = count--;
            Info.setText("Number of attempts ramaining:"+String.valueOf(i));
            if(i==0)
                Login.setEnabled(false);
        }
    }

}
