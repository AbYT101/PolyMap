package com.supreme.ab.polymap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {

    FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    public Button button,button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button=findViewById(R.id.button);
        button.setOnClickListener(v -> {
                Intent intent=new Intent(getApplicationContext(), DisplayAllEvent.class);
                startActivity(intent);
        });


        button2=findViewById(R.id.button2);


        button2.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        button3=findViewById(R.id.button3);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.LogOut){
            firebaseAuth.signOut();
            startActivity(new Intent(this, LoginAccount.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
