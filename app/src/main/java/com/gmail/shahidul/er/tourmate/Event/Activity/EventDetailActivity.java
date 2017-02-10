package com.gmail.shahidul.er.tourmate.Event.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.shahidul.er.tourmate.Event.Model.Event;
import com.gmail.shahidul.er.tourmate.EventMoment.Activity.EventMomentActivity;
import com.gmail.shahidul.er.tourmate.R;

public class EventDetailActivity extends AppCompatActivity {

    Button eventDelete;
    Button eventEdit;
    Button eventExpense;
    Button eventMoment;
    TextView eventId;
    TextView eventLocation;
    TextView eventBudget;
    TextView eventDate;
    TextView createdAt;
    String userEmail;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;

    int eventIdForEventMoment;
    Event event;
    Event eventDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        eventDelete = (Button) findViewById(R.id.eventDeleteBtn);
        eventEdit = (Button) findViewById(R.id.eventEditBtn);
        eventExpense = (Button) findViewById(R.id.eventExpenseBtn);
        eventMoment = (Button) findViewById(R.id.eventMomentBtn);

        eventId = (TextView) findViewById(R.id.eventIdDataTV);
        eventLocation = (TextView) findViewById(R.id.locationDataTV);
        eventBudget = (TextView) findViewById(R.id.budgetDataTV);
        eventDate = (TextView) findViewById(R.id.DateDataTV);
        createdAt = (TextView) findViewById(R.id.createdAtTV);

        tv1 = (TextView) findViewById(R.id.eventTitleTV);
        tv2 = (TextView) findViewById(R.id.eventIdTV);
        tv3 = (TextView) findViewById(R.id.locationTV);
        tv4 = (TextView) findViewById(R.id.BudgetTV);
        tv5 = (TextView) findViewById(R.id.dateTV);
        tv6 = (TextView) findViewById(R.id.createdAt);

        Event eventDetail = (Event) getIntent().getSerializableExtra("EventDetails");

        eventLocation.setText(eventDetail.getLocation().toString());
        eventId.setText("" + eventDetail.getId());
        eventBudget.setText("" + eventDetail.getCost());
        createdAt.setText(eventDetail.getEmail() != null ? eventDetail.getEmail().toString():"");
        userEmail = eventDetail.getEmail();
        eventIdForEventMoment = eventDetail.getId();
        Toast.makeText(this, eventDetail.getLocation(), Toast.LENGTH_SHORT).show();
    }

    public void deleteAction(View view) {
        Toast.makeText(this, userEmail, Toast.LENGTH_SHORT).show();
    }

    public void editAction(View view) {

    }

    public void expenseAction(View view) {

    }

    public void momentAction(View view) {

        Intent momentIntent = new Intent(EventDetailActivity.this, EventMomentActivity.class);
        momentIntent.putExtra("eventId",eventIdForEventMoment);
        momentIntent.putExtra("userEmail",userEmail);
        momentIntent.putExtra("status","fromEventDetail");
        startActivity(momentIntent);
    }
}
