package com.gmail.shahidul.er.tourmate.Event.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.shahidul.er.tourmate.Event.Model.Event;
import com.gmail.shahidul.er.tourmate.EventExpense.Activity.EventExpenseAddActivity;
import com.gmail.shahidul.er.tourmate.EventMoment.Activity.EventMomentActivity;
import com.gmail.shahidul.er.tourmate.EventMoment.Activity.MomentViewActivity;
import com.gmail.shahidul.er.tourmate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EventDetailActivity extends AppCompatActivity {

    Button eventDelete;
    Button eventEdit;
    Button eventExpense;
    Button eventMoment;
    TextView eventLocation;
    TextView eventBudget;
    TextView eventStartDate;
    TextView eventEndDate;
    TextView createdAt;
    String userEmail;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;

    int eventIdForEventMoment;
    String eventIdEachMoment;
    Event event;
    Event eventDetail;
    float eachEventBudgetCost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        eventDelete = (Button) findViewById(R.id.eventDeleteBtn);
        eventExpense = (Button) findViewById(R.id.eventExpenseBtn);
        eventMoment = (Button) findViewById(R.id.eventMomentBtn);

        eventLocation = (TextView) findViewById(R.id.locationDataTV);
        eventBudget = (TextView) findViewById(R.id.budgetDataTV);
        eventStartDate = (TextView) findViewById(R.id.DateDataTV);
        eventEndDate = (TextView) findViewById(R.id.DateEndDataTV);
        createdAt = (TextView) findViewById(R.id.createdAtTV);

        tv1 = (TextView) findViewById(R.id.eventTitleTV);
        tv3 = (TextView) findViewById(R.id.locationTV);
        tv4 = (TextView) findViewById(R.id.BudgetTV);
        tv5 = (TextView) findViewById(R.id.dateTV);
        tv6 = (TextView) findViewById(R.id.endDate);
        tv7 = (TextView) findViewById(R.id.createdAt);

        Event eventDetail = (Event) getIntent().getSerializableExtra("EventDetails");

        eventLocation.setText(eventDetail.getLocation().toString());
//        eventId.setText("" + eventDetail.getId());
        eventBudget.setText("" + eventDetail.getCost());
        eventStartDate.setText(eventDetail.getDate() != null ? eventDetail.getDate().toString():"");
        eventEndDate.setText(eventDetail.getEndDate() != null ? eventDetail.getEndDate().toString():"");
        createdAt.setText(eventDetail.getEmail() != null ? eventDetail.getEmail().toString():"");
        userEmail = eventDetail.getEmail();
        eventIdForEventMoment = eventDetail.getId();
        eventIdEachMoment = eventDetail.getEventId();
        eachEventBudgetCost = eventDetail.getCost();


        SharedPreferences saveUserData = getSharedPreferences("UserInfo",MODE_PRIVATE );

        SharedPreferences.Editor editor = saveUserData.edit();
        editor.putInt("eventIdForEachEvent",eventIdForEventMoment);
        editor.putString("eventIdEachMoment",eventIdEachMoment);
        editor.apply();
        editor.commit();
    }

    public void deleteAction(View view) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        final Query applesQuery = mDatabase.child("events").orderByChild("eventId").equalTo(eventIdEachMoment);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EventDetailActivity.this);
        alertDialogBuilder
                .setMessage("Are you want to delete this moment")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,int id) {

                        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                    appleSnapshot.getRef().removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e("deleted", "onCancelled", databaseError.toException());
                            }
                        });
                        Intent intent = new Intent(EventDetailActivity.this, EventListActivity.class);
//
                        EventDetailActivity.this.startActivity(intent);
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public void expenseAction(View view) {

        Intent momentIntent = new Intent(EventDetailActivity.this, EventExpenseAddActivity.class);

        momentIntent.putExtra("eventIdForEventMoment",eventIdForEventMoment);

        momentIntent.putExtra("eventBudgetCost",eachEventBudgetCost);
        momentIntent.putExtra("eventIdEachMoment",eventIdEachMoment);
        momentIntent.putExtra("userEmail",userEmail);
        momentIntent.putExtra("status","fromEventDetail");
        startActivity(momentIntent);
    }

    public void momentAction(View view) {

        Intent momentIntent = new Intent(EventDetailActivity.this, EventMomentActivity.class);

        SharedPreferences putData = getSharedPreferences("UserInfo",MODE_PRIVATE );

        SharedPreferences.Editor editor = putData.edit();
        editor.remove("eventIdForEachEvent");
        editor.remove("eventIdEachMoment");
        editor.apply();
        editor.commit();

        momentIntent.putExtra("eventIdForEventMoment",eventIdForEventMoment);
        momentIntent.putExtra("eventIdEachMoment",eventIdEachMoment);

        momentIntent.putExtra("userEmail",userEmail);
        momentIntent.putExtra("status","fromEventDetail");
        startActivity(momentIntent);
    }
}
