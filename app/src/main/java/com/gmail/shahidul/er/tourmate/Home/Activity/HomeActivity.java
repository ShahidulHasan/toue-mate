package com.gmail.shahidul.er.tourmate.Home.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gmail.shahidul.er.tourmate.BaseActivity;
import com.gmail.shahidul.er.tourmate.Event.Activity.EventListActivity;
import com.gmail.shahidul.er.tourmate.Event.Model.Event;
import com.gmail.shahidul.er.tourmate.EventMoment.Activity.EventMomentActivity;
import com.gmail.shahidul.er.tourmate.Location.Activity.MapsActivity;
import com.gmail.shahidul.er.tourmate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    private DatabaseReference mDatabase;
    ArrayList<Event> eventArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDatabase = FirebaseDatabase.getInstance().getReference();

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
    }

    public void event(View view) {
        Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
        intent.putExtra("eventArrayList", eventArrayList);
        startActivity(intent);
    }

    public void nearByAction(View view) {

        Intent nearByIntent = new Intent(HomeActivity.this,MapsActivity.class);
        startActivity(nearByIntent);
    }

    public void eventMomentAction(View view) {

        Intent eventMomentIntent = new Intent(HomeActivity.this,EventMomentActivity.class);
        startActivity(eventMomentIntent);
    }
}
