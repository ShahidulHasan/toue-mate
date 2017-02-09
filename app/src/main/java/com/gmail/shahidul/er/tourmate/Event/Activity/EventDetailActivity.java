package com.gmail.shahidul.er.tourmate.Event.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.shahidul.er.tourmate.Event.Model.Event;
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
    String userEmail;
    int EventId;
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
        eventLocation = (TextView) findViewById(R.id.LocationDataTV);
        eventBudget = (TextView) findViewById(R.id.budgetDataTV);
        eventDate = (TextView) findViewById(R.id.DateDataTV);

        Event eventDetail = (Event) getIntent().getSerializableExtra("EventDetails");


//        eventId.setText(eventDetail.getId());

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

    }
}
