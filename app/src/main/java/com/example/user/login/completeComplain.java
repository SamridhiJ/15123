package com.example.user.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.media.Image;
import android.net.Uri;
import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;
//import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;

public class completeComplain extends AppCompatActivity {
    static String title;
    ImageView problemimage;
    static String desc;
    TextView titleView, descView;
    FirebaseDatabase db;
    DatabaseReference ref;
    DataSnapshot dataSnapshot2;
    String problemType,problemName;
    myListener listener;
    String num;
    Uri dwnld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent i = getIntent();
        title = i.getStringExtra("Title");
        desc = i.getStringExtra("Desc");
        num = i.getStringExtra("Number");

        setContentView(R.layout.activity_complete_complain);
        db = FirebaseDatabase.getInstance();
        ref = db.getReferenceFromUrl(getIntent().getStringExtra("Ref"));
        super.onCreate(savedInstanceState);
        problemimage=findViewById(R.id.problemImage);
        titleView = findViewById(R.id.completeTitle);
        descView = findViewById(R.id.completeDesc);
        titleView.setText(title);
        descView.setText(desc);
        final StorageReference storageReference= FirebaseStorage.getInstance().getReference();

        final ImageView problemImage=findViewById(R.id.problemImage);

        storageReference.child("images/"+num+"/problemImage.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //uri=storageReference.child("imageProblem.jpg").getDownloadUrl().getResult();
                Picasso.with(completeComplain.this).load(uri).fit().centerCrop().into(problemImage);
            }
        });



    }

    public void markSolved(View v) {
        listener=new myListener();
        ref.addValueEventListener(listener);







    }

    public void showImage(View v){

    }

    public void markRead(View v) {


        ref.child("Read").setValue("true");





    }





    class myListener implements ValueEventListener{

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            final DatabaseReference problemTypeRef = ref.getParent();
            problemType=problemTypeRef.getKey();
            problemName=ref.getKey();
            dataSnapshot2=dataSnapshot;
            ref.removeEventListener(listener);
            ref.removeValue();
            DatabaseReference ref2= db.getReference("Complains/Solved");

            ref2.child(ref.getParent().getKey()).child(ref.getKey()).setValue(dataSnapshot2.getValue());


            {
                String studentMail="Default";
                Iterable<DataSnapshot> dataSnapshots=dataSnapshot2.getChildren();
                for(DataSnapshot sp:dataSnapshots){
                    if(sp.getKey().equalsIgnoreCase("Email")){
                        studentMail=(String)sp.getValue();
                    }
                }

                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL,new String[]{studentMail});

                i.putExtra(Intent.EXTRA_SUBJECT,"Your problem has been solved");
                i.putExtra(Intent.EXTRA_TEXT,"Your problem titled "+title+" has been solved");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }




}


