package com.example.user.login;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.location.LocationManager.*;

public class NodalMainActivity extends AppCompatActivity implements LocationListener{
    EditText admin;
    EditText passwordField;
    FirebaseDatabase firebaseDatabase;
    int finalVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("NULL", "woking on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodal_main);



    }

//    public void login(View v) {
//        admin = findViewById(R.id.userNameField);
//        passwordField = findViewById(R.id.passwordField);
//        String userName = admin.getText().toString();
//        String password = passwordField.getText().toString();
//
//        if (userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("1234")) {
//            admin.setText(password);
//03-30 17:09:52.549 28670-28675/app.com.example.android.nodalofficer2 I/zygote64: Compiler allocated 6MB to compile void android.view.ViewRootImpl.performTraversals()

    //            Toast.makeText(this, "Correct login", Toast.LENGTH_LONG).show();
//            Intent i = new Intent(MainActivity.this, NavDrawer.class);
//            i.putExtra("Name", userName);
//            MainActivity.this.startActivity(i);
//        }
//
//    }


    public void login(View v){
        admin=findViewById(R.id.userNameField);
        passwordField=findViewById(R.id.passwordField);
        String userName=admin.getText().toString();
        final String password=passwordField.getText().toString();
        firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference myRef=firebaseDatabase.getReference("NodalOfficer/"+userName);
        Log.v("NULL","working NULL");
        if(myRef==null){
            Log.v("NULL","IS NULL");
        }else{
            Log.v("NULL","NOT NULL");
        }

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                HashMap<String,String> value=(HashMap<String,String>)dataSnapshot.getValue();

                String correctPass=value.get("Password");
                if(password.equalsIgnoreCase(correctPass)){
                    Log.v("Correct","Password");
                    Intent i =new Intent(NodalMainActivity.this,NodalNavDrawer.class);
                    NodalMainActivity.this.startActivity(i);
                }else{
                    Log.d("Wrong","Password");
                    Log.d("Correct pass",correctPass+" "+password);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onLocationChanged(Location location) {
        Double lat=location.getLatitude();
        Double lon=location.getLongitude();
        Log.d("Coord ",lat+" "+lon);
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Location");
        ref.child("Lat").setValue(lat);
        ref.child("Lon").setValue(lon);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


//    class Login extends AsyncTask<String,Void,String>{
//
//
//        @Override
//        protected void onPostExecute(String s) {
//            admin=findViewById(R.id.userNameField);
//            admin.setText(s);
//            super.onPostExecute(s);
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            String url="http://192.168.43.83:3000";
//            String s="a ";
//
//
//
//            try {
//                Log.d("Try catch","Working");
//                URL link = new URL(url);
//                HttpClient client = new DefaultHttpClient();
//                HttpGet request = new HttpGet();
//                request.setURI(new URI(url));
//                HttpResponse response = client.execute(request);
//                BufferedReader in = new BufferedReader
//                        (new InputStreamReader(response.getEntity().getContent()));
//                s=in.readLine();
//            }catch (Exception e){
//                Log.d("Error ","error occured");
//                e.printStackTrace();
//            }
//            Log.d("Tag0",s);
//            return s;
//        }
//    }
}
