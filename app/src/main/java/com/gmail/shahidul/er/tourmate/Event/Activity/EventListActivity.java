package com.gmail.shahidul.er.tourmate.Event.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gmail.shahidul.er.tourmate.Event.Adapter.EventListAdapter;
import com.gmail.shahidul.er.tourmate.Event.Dao.EventDAO;
import com.gmail.shahidul.er.tourmate.Event.Model.Event;
import com.gmail.shahidul.er.tourmate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    ListView events;
    ArrayList<Event> eventArrayList = new ArrayList<>();
    EventListAdapter eventListAdapter;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        events = (ListView) findViewById(R.id.events);

//        eventArrayList = (ArrayList<Event>) getIntent().getSerializableExtra("eventArrayList");

        mDatabase.child("events").addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    eventArrayList.add(data.getValue(Event.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        eventListAdapter = new EventListAdapter(this, eventArrayList);
        events.setAdapter(eventListAdapter);

        events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int productId = eventArrayList.get(position).getId();

                Toast.makeText(EventListActivity.this, String.valueOf(productId), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void add(View view) {
        Intent intent = new Intent(EventListActivity.this, EventAddActivity.class);
        startActivity(intent);
    }
}

//{
//        "rules": {
//        ".read": "auth != null",
//        ".write": "auth != null"
//        }
//}