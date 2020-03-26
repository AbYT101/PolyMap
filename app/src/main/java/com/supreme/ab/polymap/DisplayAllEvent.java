package com.supreme.ab.polymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class DisplayAllEvent extends AppCompatActivity {


    private RecyclerView recyclerView;
    private AccountModel acc;
//    private FirestoreRecyclerAdapter<EventModel,EventHolder> adapter;
    EventModel eventModel;
    Button logout;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_event);
        recyclerView = findViewById(R.id.recyclaerView4);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        List<EventModel> list= new ArrayList<>();
        EventModel eventModel1= new EventModel("March 28/ 2020","Flutter Basics", "GDG Event", "", "", "7:00 AM","American Cornor");
        EventModel eventModel2= new EventModel("March 30/ 2020","Firebase Jam", "GDG Event", "", "", "7:00 AM","Peda Campus");
        EventModel eventModel3= new EventModel("April 28/ 2020","Easter Concert", "Jorka Concert", "", "", "7:00 AM","Bahir Dar");
        list.add(eventModel1);
        list.add(eventModel2);
        list.add(eventModel3);

        adapter adp= new adapter(this,list );
        recyclerView.setAdapter(adp);

////        private FirestoreRecyclerAdapter<EventModel,EventHolder> adapter;
//        String userId;
//        FirebaseAuth firebaseAuth;
//        Button logout;
//        FirebaseFirestore db;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//            Toolbar toolbar = findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);
//
//            FloatingActionButton fab = findViewById(R.id.fab);
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(getApplicationContext(), EventRegisteration.class);
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                    startActivity(intent);
//                }
//            });
//            recyclerView = findViewById(R.id.recyclaerView);
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//            recyclerView.setLayoutManager(linearLayoutManager);
//            db = FirebaseFirestore.getInstance();
//            firebaseAuth = FirebaseAuth.getInstance();
//            Query query = db.collection("Events").orderBy("Eventname", Query.Direction.ASCENDING);
//            FirestoreRecyclerOptions<EventModel> options = new FirestoreRecyclerOptions.Builder<EventModel>()
//                    .setQuery(query, EventModel.class).build();
//
//
//            adapter = new FirestoreRecyclerAdapter<EventModel, EventHolder>(options) {
//                @NonNull
//                @Override
//                public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclorsingle, null);
//                    return new EventHolder(view);
//                }
//
//                @Override
//                protected void onBindViewHolder(@NonNull EventHolder eventHolder, final int i, @NonNull final EventModel eventModel) {
//                    eventHolder.Eventname.setText(eventModel.getEventName());
//                    eventHolder.Eventplace.setText(eventModel.getPlace());
//                    eventHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent toeventDescription = new Intent(getApplicationContext(), EventDescription.class);
//                            toeventDescription.putExtra("name", eventModel.getEventName().toString());
//                            toeventDescription.putExtra("place", eventModel.getPlace().toString());
//                            toeventDescription.putExtra("date", eventModel.getDate().toString());
//                            toeventDescription.putExtra("description", eventModel.getDiscription().toString());
//                            toeventDescription.putExtra("time", eventModel.getTime().toString());
////                        toeventDescription.putExtra("latitude", eventModel.getLatitude());
////                        toeventDescription.putExtra("longitude", eventModel.getLongitude());
//                            startActivity(toeventDescription);
//                        }
//                    });
//                }
//            };
//            recyclerView.setAdapter(adapter);
//
//        }
//
//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            // Inflate the menu; this adds items to the action bar if it is present.
//            getMenuInflater().inflate(R.menu.menu_main, menu);
//            return true;
//        }
//
//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            // Handle action bar item clicks here. The action bar will
//            // automatically handle clicks on the Home/Up button, so long
//            // as you specify a parent activity in AndroidManifest.xml.
//            if (item.getItemId()==R.id.action_settings){
//                firebaseAuth.signOut();
//            }
//            return super.onOptionsItemSelected(item);
//        }
//        @Override
//        protected void onStart() {
//            super.onStart();
//            adapter.startListening();
//        }
//
//        @Override
//        protected void onStop() {
//            super.onStop();
//            if(adapter!=null){
//                adapter.stopListening();
//            }
//        }
//
//    }


}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main3, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.registerEvent){
            firebaseAuth.signOut();
            startActivity(new Intent(this, EventRegisteration.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
