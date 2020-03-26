package com.supreme.ab.polymap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class EventRegisteration extends AppCompatActivity {


    EditText Eventname,Eventplace,Eventtime,Eventdate;
    EditText Eventdescription;
    String Longtide,Latitiude;
    ProgressBar progressBar4;
          FirebaseFirestore firebaseFirestore;
     FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    Button save,locate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registeration);
//            firebaseAuth=FirebaseAuth.getInstance();
        Eventname=findViewById(R.id.textView14name);
        Eventdate=findViewById(R.id.date);
        Eventplace=findViewById(R.id.textView13place);
        Eventdescription=findViewById(R.id.editText2descrip);
        Eventtime=findViewById(R.id.textView12time);
        Longtide= " ";
        Latitiude=" ";
        progressBar4=findViewById(R.id.progressBar5);

        progressBar4.setVisibility(View.INVISIBLE);
        save=findViewById(R.id.button7save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = Eventname.getText().toString();
                final String place = Eventplace.getText().toString();
                final String time = Eventtime.getText().toString();
                final String descri = Eventdescription.getText().toString();
                final String date = Eventdate.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Eventname.setError("Error ");
                }
                if (TextUtils.isEmpty(place)) {
                    Eventplace.setError("Error ");
                    return;
                }
                if (TextUtils.isEmpty(time)) {
                    Eventtime.setError("Error ");
                    return;
                }
                if (TextUtils.isEmpty(descri)) {
                    Eventdescription.setError("Error ");
                    return;
                }
                Toast.makeText(getApplicationContext(), "Loading please wait ", Toast.LENGTH_SHORT).show();
                progressBar4.setVisibility(View.VISIBLE);
                firebaseAuth= FirebaseAuth.getInstance();
                firebaseFirestore = FirebaseFirestore.getInstance();
                EventModel eventModel = new EventModel(date,descri,name,Latitiude,Longtide,time,place);
                DocumentReference accdoc = firebaseFirestore.collection("Events").document();
                accdoc.set(eventModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Data Is added", Toast.LENGTH_SHORT).show();
                        Intent torecycler = new Intent(getApplicationContext(), Main2Activity.class);
                        startActivity(torecycler);
                    }
                });


            }
        });





    }
}
