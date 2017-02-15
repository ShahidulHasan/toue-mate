package com.gmail.shahidul.er.tourmate.EventExpense.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gmail.shahidul.er.tourmate.Event.Activity.EventAddActivity;
import com.gmail.shahidul.er.tourmate.Event.Activity.EventListActivity;
import com.gmail.shahidul.er.tourmate.Event.Model.Event;
import com.gmail.shahidul.er.tourmate.EventExpense.Model.EventExpense;
import com.gmail.shahidul.er.tourmate.EventMoment.Activity.EventMomentActivity;
import com.gmail.shahidul.er.tourmate.Home.Activity.HomeActivity;
import com.gmail.shahidul.er.tourmate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventExpenseAddActivity extends AppCompatActivity {

    EditText expenseCauseTV;
    EditText expenseDateTV;
    EditText expenseCostTV;
    Button   expenseSaveBtn;
    int i = 0;
    private DatabaseReference mDatabase;
    Calendar myCalendar = Calendar.getInstance();
    float totalCost;
    float eventBudgetCost;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_expense_add);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        expenseCauseTV = (EditText) findViewById(R.id.expenseCause);
        expenseDateTV = (EditText) findViewById(R.id.expenseDate);
        expenseCostTV = (EditText) findViewById(R.id.expenseCost);
        expenseSaveBtn = (Button) findViewById(R.id.eventExpenseBtn);

        Intent intent = getIntent();

        eventBudgetCost = intent.getFloatExtra("eventBudgetCost",0);


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

        expenseDateTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EventExpenseAddActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        SharedPreferences saveUserData = getSharedPreferences("UserInfo",MODE_PRIVATE );

        mDatabase = FirebaseDatabase.getInstance().getReference("eventExpenses");

         email = saveUserData.getString("email","");

        String eventIdEachMoment = saveUserData.getString("eventIdEachMoment"," ");


        mDatabase.child("eventExpenses").orderByChild("eventId").equalTo(eventIdEachMoment).addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                totalCost = 0;

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    totalCost +=  data.getValue(EventExpense.class).getExpenseCost();
                    Log.d("expense", "1: "+data.getValue(EventExpense.class).getExpenseCost());
                    Log.d("expense", "2: "+totalCost);
                }
                //    checkBudget(totalCost);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        expenseDateTV.setText(sdf.format(myCalendar.getTime()));
    }

    public void eventExpenseSaveAction(View view) {

        if (eventBudgetCost > totalCost){

            if (i == 0){ i = 1;} else { i++; }

            final String expenseCause = expenseCauseTV.getText().toString().trim();
            final String expenseCost = expenseCostTV.getText().toString().trim();
            final String expenseDate = expenseDateTV.getText().toString().trim();

            SharedPreferences saveUserData = getSharedPreferences("UserInfo",MODE_PRIVATE );

            mDatabase = FirebaseDatabase.getInstance().getReference("eventExpenses");

            EventExpense eventExpense = new EventExpense();

            String email = saveUserData.getString("email","");
            String eventIdEachMoment = saveUserData.getString("eventIdEachMoment"," ");

            String eventExpenseId = FirebaseDatabase.getInstance().getReference("eventExpenses").push().getKey();

                eventExpense.setExpenseId(eventExpenseId);
                eventExpense.setUserEmail(email);
                eventExpense.setExpenseCause(expenseCause);
                eventExpense.setExpenseCost(Float.valueOf(expenseCost));
                eventExpense.setExpenseDate(expenseDate);
                eventExpense.setEventId(eventIdEachMoment);
                eventExpense.setCreatedAt(new Date());

                mDatabase.child(eventExpenseId).setValue(eventExpense);

                Intent intent = new Intent(getApplicationContext(), EventExpenseActivity.class);
                startActivity(intent);
        } else {
            Toast.makeText(EventExpenseAddActivity.this, "This Expense Cross Your Budget", Toast.LENGTH_SHORT).show();

        }
    }


}
