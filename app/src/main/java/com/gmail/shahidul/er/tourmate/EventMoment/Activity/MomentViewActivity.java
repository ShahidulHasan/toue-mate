package com.gmail.shahidul.er.tourmate.EventMoment.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;

import com.gmail.shahidul.er.tourmate.EventMoment.Adapter.EventMomentListAdapter;
import com.gmail.shahidul.er.tourmate.EventMoment.Model.EventMoment;
import com.gmail.shahidul.er.tourmate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MomentViewActivity extends AppCompatActivity {


    ListView eventMoments;
    ImageView imageView;
    ArrayList<EventMoment> eventMomentArrayList = new ArrayList<>();

    EventMomentListAdapter eventMomentListAdapter;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment_view);

      //  EventMoment eventMoment = (EventMoment) getIntent().getSerializableExtra("eventMoment");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        eventMoments = (ListView) findViewById(R.id.eventMomentsList);
//        events = (ListView) findViewById(events);

//        eventArrayList = (ArrayList<Event>) getIntent().getSerializableExtra("eventArrayList");

        mDatabase.child("eventMoments").addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventMomentListAdapter.notifyDataSetChanged();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    eventMomentArrayList.add(data.getValue(EventMoment.class));

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        eventMomentListAdapter = new EventMomentListAdapter(MomentViewActivity.this, eventMomentArrayList);
        eventMoments.setAdapter(eventMomentListAdapter);

      /*  events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int eventId = eventArrayList.get(position).getId();
                float eventCost = eventArrayList.get(position).getCost();
                String eventLocation = eventArrayList.get(position).getLocation();
                String userEmail = eventArrayList.get(position).getEmail();

                Event event = new Event();
                event.setId(eventId);
                event.setCost(eventCost);
                event.setLocation(eventLocation);
                event.setEmail(userEmail);

                Intent intent = new Intent(MomentViewActivity.this , EventDetailActivity.class);
                intent.putExtra("EventDetails",event);
                startActivity(intent);

            }
        });*/

    }

}
