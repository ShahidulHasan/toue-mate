package com.gmail.shahidul.er.tourmate.Event.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gmail.shahidul.er.tourmate.BaseActivity;
import com.gmail.shahidul.er.tourmate.Event.Adapter.EventListAdapter;
import com.gmail.shahidul.er.tourmate.Event.Model.Event;
import com.gmail.shahidul.er.tourmate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventListActivity extends BaseActivity {

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
        SharedPreferences getEmail = getSharedPreferences("UserInfo",MODE_PRIVATE );

        String email = getEmail.getString("email","");

        mDatabase.child("events").orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventListAdapter.notifyDataSetChanged();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    eventArrayList.add(data.getValue(Event.class));

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        eventListAdapter = new EventListAdapter(EventListActivity.this, eventArrayList);
        events.setAdapter(eventListAdapter);

        events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int eventId = eventArrayList.get(position).getId();
                float eventCost = eventArrayList.get(position).getCost();
                String eventLocation = eventArrayList.get(position).getLocation();
                String userEmail = eventArrayList.get(position).getEmail();
                String startDate = eventArrayList.get(position).getDate();
                String endDate = eventArrayList.get(position).getEndDate();
                String eventIdEachMoment = eventArrayList.get(position).getEventId();

                Event event = new Event();
                event.setId(eventId);
                event.setCost(eventCost);
                event.setLocation(eventLocation);
                event.setEmail(userEmail);
                event.setDate(startDate);
                event.setEndDate(endDate);
                event.setEventId(eventIdEachMoment);

                Intent intent = new Intent(EventListActivity.this , EventDetailActivity.class);
                intent.putExtra("EventDetails",event);
                startActivity(intent);

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
