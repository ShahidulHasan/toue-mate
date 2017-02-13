package com.gmail.shahidul.er.tourmate.EventExpense.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.gmail.shahidul.er.tourmate.Event.Adapter.EventListAdapter;
import com.gmail.shahidul.er.tourmate.Event.Model.Event;
import com.gmail.shahidul.er.tourmate.EventExpense.Adapter.ExpenseListAdapter;
import com.gmail.shahidul.er.tourmate.EventExpense.Model.EventExpense;
import com.gmail.shahidul.er.tourmate.Home.Activity.HomeActivity;
import com.gmail.shahidul.er.tourmate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventExpenseActivity extends AppCompatActivity {

    ListView eventExpenses;
    ArrayList<EventExpense> eventExpenseArrayList = new ArrayList<>();

    ExpenseListAdapter eventExpenseListAdapter;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_expense);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        eventExpenses = (ListView) findViewById(R.id.eventExpenseListLV);

        //get user info from shared preference

        SharedPreferences getEmail = getSharedPreferences("UserInfo",MODE_PRIVATE );
        String email = getEmail.getString("email","");

        mDatabase.child("eventExpenses").orderByChild("userEmail").equalTo(email).addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                eventExpenseListAdapter.notifyDataSetChanged();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    eventExpenseArrayList.add(data.getValue(EventExpense.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        eventExpenseListAdapter = new ExpenseListAdapter(this, eventExpenseArrayList);
        eventExpenses.setAdapter(eventExpenseListAdapter);
    }

    public void addEventExpenseFormAction(View view) {

        Intent intent = new Intent(EventExpenseActivity.this, EventExpenseAddActivity.class);
        startActivity(intent);
    }
}
