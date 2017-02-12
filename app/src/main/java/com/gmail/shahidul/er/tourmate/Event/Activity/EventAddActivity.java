package com.gmail.shahidul.er.tourmate.Event.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.gmail.shahidul.er.tourmate.Event.Model.Event;
import com.gmail.shahidul.er.tourmate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EventAddActivity extends AppCompatActivity {

    int i = 0;
    EditText locationET, costET, startDateET,endDateET;
    private DatabaseReference mDatabase;
    Calendar myCalendar = Calendar.getInstance();
    Calendar myCalendarEnd = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);

        mDatabase = FirebaseDatabase.getInstance().getReference();
//        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        locationET = (EditText) findViewById(R.id.location);
        costET = (EditText) findViewById(R.id.cost);
        startDateET = (EditText) findViewById(R.id.startDate);
        endDateET = (EditText) findViewById(R.id.endDate);
        /*event Date Picker*/

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        final DatePickerDialog.OnDateSetListener dateEnd = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();
            }

        };

        startDateET.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EventAddActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDateET.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EventAddActivity.this, dateEnd, myCalendarEnd
                        .get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH),
                        myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        /*end of date picker*/

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
        final String startDate = startDateET.getText().toString().trim();
        final String endDate = endDateET.getText().toString().trim();

        SharedPreferences saveUserData = getSharedPreferences("UserInfo",MODE_PRIVATE );

        mDatabase = FirebaseDatabase.getInstance().getReference("events");
        Event event = new Event();

        String email = saveUserData.getString("email","");

        String eventId = mDatabase.push().getKey();
        event.setId(i);
        event.setEventId(eventId);
        event.setLocation(location);
        event.setCost(Float.valueOf(cost));
        event.setCreatedAt(new Date());
        event.setExpense(null);
        event.setEmail(email);
        event.setDate(startDate);
        event.setEndDate(endDate);

        mDatabase.child(eventId).setValue(event);

        Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
        startActivity(intent);
    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startDateET.setText(sdf.format(myCalendar.getTime()));
        endDateET.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabelEnd() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        endDateET.setText(sdf.format(myCalendarEnd.getTime()));
    }
}
