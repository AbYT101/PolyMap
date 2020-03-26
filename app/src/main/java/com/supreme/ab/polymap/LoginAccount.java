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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.supreme.ab.polymap.R;

public class LoginAccount extends AppCompatActivity {



    EditText email;
    EditText password;
    Button login,create;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_account);

        firebaseAuth= FirebaseAuth.getInstance();
      if(firebaseAuth.getCurrentUser()!=null){
            Intent toyurpage=new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(toyurpage);
            finish();
        }
        email=findViewById(R.id.textView5email);
        password=findViewById(R.id.textView6password);
        create=findViewById(R.id.button2create);
        login=findViewById(R.id.buttonlogin);
        progressBar=findViewById(R.id.progressBarlogin);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegisterAccount.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String emailS = email.getText().toString();
                String passwordS = password.getText().toString();

                if (TextUtils.isEmpty(emailS)) {
                    email.setError("Error");
                    return;
                }
                if (TextUtils.isEmpty(passwordS)) {
                    password.setError("Error nigga");
                    return;
                }
                //Authenticate from Firebase
                Toast.makeText(getApplicationContext(), "Loading please wait ", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(emailS, passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Intent tomainactivtiy = new Intent(LoginAccount.this, Main2Activity.class);
                            startActivity(tomainactivtiy);
                        } else {
                            Toast.makeText(getApplicationContext(), "Unable to Connect,Connect to Internet", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });

            }});

    }
}
