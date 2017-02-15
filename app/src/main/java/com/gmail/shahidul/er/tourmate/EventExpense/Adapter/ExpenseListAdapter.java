package com.gmail.shahidul.er.tourmate.EventExpense.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gmail.shahidul.er.tourmate.Event.Activity.EventListActivity;
import com.gmail.shahidul.er.tourmate.Event.Dao.EventDAO;
import com.gmail.shahidul.er.tourmate.Event.Model.Event;
import com.gmail.shahidul.er.tourmate.EventExpense.Activity.EventExpenseActivity;
import com.gmail.shahidul.er.tourmate.EventExpense.Model.EventExpense;
import com.gmail.shahidul.er.tourmate.EventMoment.Activity.MomentViewActivity;
import com.gmail.shahidul.er.tourmate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by rahat on 2/13/17.
 */

public class ExpenseListAdapter extends ArrayAdapter<EventExpense> {


    ArrayList<EventExpense> eventExpenseArrayList;
    Context context;
    private DatabaseReference mDatabase;

    public ExpenseListAdapter(Context context, ArrayList<EventExpense> eventExpenses) {
        super(context, R.layout.activity_event_expense_row, eventExpenses);
        eventExpenseArrayList = eventExpenses;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final EventExpense eventExpense = eventExpenseArrayList.get(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_event_expense_row, viewGroup, false);

        TextView cause = (TextView) convertView.findViewById(R.id.expenseCauseTV);
        TextView expenseDate = (TextView) convertView.findViewById(R.id.expenseDateTV);
        TextView expenseCost = (TextView) convertView.findViewById(R.id.expenseCostTV);

        cause.setText(eventExpense.getExpenseCause());
        expenseCost.setText(Float.toString(eventExpense.getExpenseCost()));
        expenseDate.setText(eventExpense.getExpenseDate());

        Button eventExpenseDeleteBtn = (Button) convertView.findViewById(R.id.eventExpenseDeleteBtn);

        eventExpenseDeleteBtn.setOnClickListener(new View.OnClickListener() {


            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

            Query applesQuery = mDatabase.child("eventExpenses").orderByChild("expenseId").equalTo(eventExpense.getExpenseId());
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder
                        .setMessage("Are you want to delete this Expense")
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
                                Intent intent = new Intent(context, EventListActivity.class);
//                                intent.putExtra("status","fromAdapter");
                                context.startActivity(intent);
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
        });

        return convertView;
    }
}
