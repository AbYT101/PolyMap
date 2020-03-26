package com.supreme.ab.polymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.supreme.ab.polymap.R;

public class RegisterAccount extends AppCompatActivity {


    EditText fullname,email,phoneno,city,password;
    RadioGroup sexchoice;
    String sex;
    ProgressBar progressBar2;
     FirebaseFirestore firebaseFirestore;
      FirebaseAuth firebaseAuth;
    Button saveAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        fullname=findViewById(R.id.textView14Accountfullname);
        email=findViewById(R.id.textView12Accountemail);
        phoneno=findViewById(R.id.editText2phoneno);
        password=findViewById(R.id.editTextaccountpassword);
        city=findViewById(R.id.textView13Accountcity);
        sexchoice=findViewById(R.id.radiogrpsex);
        saveAccount=findViewById(R.id.button7saveAccount);

        progressBar2=findViewById(R.id.progressBar5);
        sexchoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button=findViewById(checkedId);
                sex=button.getText().toString();
            }
        });
        progressBar2.setVisibility(View.INVISIBLE);

        saveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Acccity = city.getText().toString();
                final String Accemail = email.getText().toString();
                final String Accfullname = fullname.getText().toString();
                final String Accphonen = phoneno.getText().toString();
                final String Accpassword = password.getText().toString();

                if (TextUtils.isEmpty(Acccity)) {
                    city.setError("Error");
                    return;
                }
                if (TextUtils.isEmpty(Accpassword)) {
                    password.setError("Error ");
                    return;
                }
                if (TextUtils.isEmpty(Accemail)) {
                    email.setError("Error ");
                    return;
                }
                if (TextUtils.isEmpty(Accfullname)) {
                    fullname.setError("Error");
                    return;
                }
                if (TextUtils.isEmpty(Accphonen)) {
                    phoneno.setError("Error ");
                    return;
                }
                Toast.makeText(getApplicationContext(), "Loading please wait ", Toast.LENGTH_SHORT).show();
                progressBar2.setVisibility(View.VISIBLE);
                firebaseAuth= FirebaseAuth.getInstance();
              firebaseAuth.createUserWithEmailAndPassword(Accemail,Accpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressBar2.setVisibility(View.INVISIBLE);
                            String accid=firebaseAuth.getCurrentUser().getUid();
                            AccountModel accountModel=new AccountModel(Acccity,Accemail,Accfullname,Accphonen,sex);
                            DocumentReference accdoc=firebaseFirestore.collection("Accounts").document(accid);
                            accdoc.set(accountModel).addOnSuccessListener(aVoid -> {
                                Intent torecycler=new Intent(getApplicationContext(),Main2Activity.class);
                                startActivity(torecycler);
                            });
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Data Is not added",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });


            }

        });
    }
}
