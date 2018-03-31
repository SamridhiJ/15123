
package com.example.user.login;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.*;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;

import java.io.IOException;
import java.util.*;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;

import static android.app.Activity.RESULT_OK;

/**
 * Created by USER on 3/16/2018.
 */

public class fragerp extends Fragment {
    Long  num;
    String cTitle,cDesc,cEmail;
    ImageButton sendPic;
    private    Spinner sp;
   // public static final int PICK_IMAGE_REQUEST=231;
    String s1;
    EditText title,desc,email;
   // Button b;
    private DatabaseReference mref;
    Long complainNo;
    Button b;
    private static int PICK_IMAGE_REQUEST=234;
    //private Uri filepath;
    ImageView logo;
    StorageReference storageReference;


    private Uri filepath;
    myListener listener;
    DatabaseReference rf;
    FirebaseDatabase db;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST||resultCode==RESULT_OK||data!=null||data.getData()!=null)
        {
            filepath=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),filepath);
                upload();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.erpfrag, container, false);
        sp = myview.findViewById(R.id.spinner);
        title = myview.findViewById(R.id.title);
        desc = myview.findViewById(R.id.desc);
        email = myview.findViewById(R.id.facemail);
        mref=FirebaseDatabase.getInstance().getReference();
        sendPic = myview.findViewById(R.id.button4);
        b = myview.findViewById(R.id.prob_submit);
        //b=(Button)findViewById(R.id.send);
        storageReference= FirebaseStorage.getInstance().getReference();
        sendPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "select image"),PICK_IMAGE_REQUEST);
            }
        });
        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("Complain types");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> areas = new ArrayList<String>();


                Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                for (DataSnapshot ds : it) {
                    areas.add((String) ds.getValue());
                }
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, areas);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp.setAdapter(areasAdapter);
                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                        s1 = (String) adapterView.getItemAtPosition(pos);
                        Toast.makeText(getContext(), adapterView.getItemAtPosition(pos) + " is selected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        prob();
//        send();
        return myview;
    }
    protected void upload(){
        if(filepath!=null) {
            StorageReference riversRef = storageReference.child("images/"+num+"/"+"problemImage.jpg");


            riversRef.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded contenaskSnapshot.is)

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                        }
                    });
        }
    }






       static class Complain {
            String Title;
            String Desc;
            String Email;
            String Read;

            public Complain(String title, String desc, String email) {
                Title = title;
                Desc = desc;
                Email = email;
                Read = "false";
            }
        }

    public void prob() {
        b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        rf= firebaseDatabase.getReference("Complains/Key");
        listener =new myListener();
        cTitle=title.getText().toString();
        cDesc=desc.getText().toString();
        cEmail=email.getText().toString();
        rf.addValueEventListener(listener);
        title.setText("");
        desc.setText("");
        email.setText("");
        }
    });
    }

    class myListener implements ValueEventListener{

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            num=(Long)dataSnapshot.getValue();
            ++num;
            rf.removeEventListener(listener);
            rf.setValue(num);
            upload();
            rf=db.getReference("Complains/Unsolved/"+s1+"/"+num);
            rf.setValue(new Complain(cTitle,cDesc,cEmail));
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }
}


