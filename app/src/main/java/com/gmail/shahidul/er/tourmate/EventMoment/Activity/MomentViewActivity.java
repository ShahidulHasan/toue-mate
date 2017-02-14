package com.gmail.shahidul.er.tourmate.EventMoment.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.gmail.shahidul.er.tourmate.Event.Activity.EventListActivity;
import com.gmail.shahidul.er.tourmate.EventMoment.Adapter.EventMomentListAdapter;
import com.gmail.shahidul.er.tourmate.EventMoment.Model.EventMoment;
import com.gmail.shahidul.er.tourmate.Home.Activity.HomeActivity;
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

        SharedPreferences getEmail = getSharedPreferences("UserInfo",MODE_PRIVATE );
        String email = getEmail.getString("email","");

        mDatabase.child("eventMoments").orderByChild("userEmail").equalTo(email).addValueEventListener(new ValueEventListener() {
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
        SharedPreferences saveUserData = getSharedPreferences("UserInfo",MODE_PRIVATE );

        SharedPreferences.Editor editor = saveUserData.edit();
        int eventId = saveUserData.getInt("eventIdForEachEvent",0);
        editor.remove("eventIdForEachEvent");
        editor.apply();
        editor.commit();

    }

    public void addEventMomentAction(View view) {

        Intent intent = new Intent(MomentViewActivity.this, EventListActivity.class);
        startActivity(intent);
        /*Intent eventMomentIntent = new Intent(MomentViewActivity.this,EventMomentActivity.class);
        eventMomentIntent.putExtra("status","fromMomentList");
        startActivity(eventMomentIntent);*/
    }
}
