package com.gmail.shahidul.er.tourmate.Event.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.gmail.shahidul.er.tourmate.Event.Model.Event;
import com.gmail.shahidul.er.tourmate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class EventAddActivity extends AppCompatActivity {

    int i = 0;
    EditText locationET, costET, dateET;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        locationET = (EditText) findViewById(R.id.location);
        costET = (EditText) findViewById(R.id.cost);
        dateET = (EditText) findViewById(R.id.date);

        mDatabase.child("events").addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    i++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void eventSave(View view) {

        if (i == 0){ i = 1;} else { i++; }
        final String location = locationET.getText().toString().trim();
        final String cost = costET.getText().toString().trim();
//        final String date = dateET.getText().toString().trim();
        SharedPreferences saveUserData = getSharedPreferences("UserInfo",MODE_PRIVATE );

        mDatabase = FirebaseDatabase.getInstance().getReference("events");
        Event event = new Event();
        String email = saveUserData.getString("email","");
        String eventId = mDatabase.push().getKey();
        event.setId(i);
        event.setLocation(location);
        event.setCost(Float.valueOf(cost));
        event.setCreatedAt(new Date());
        event.setExpense(null);
        event.setEmail(email);
//        event.setDate(date);

        mDatabase.child(eventId).setValue(event);

        Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
        startActivity(intent);
    }
}
