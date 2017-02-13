package com.gmail.shahidul.er.tourmate.EventExpense.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gmail.shahidul.er.tourmate.Event.Dao.EventDAO;
import com.gmail.shahidul.er.tourmate.Event.Model.Event;
import com.gmail.shahidul.er.tourmate.EventExpense.Model.EventExpense;
import com.gmail.shahidul.er.tourmate.R;

import java.util.ArrayList;

/**
 * Created by rahat on 2/13/17.
 */

public class ExpenseListAdapter extends ArrayAdapter<EventExpense> {


    ArrayList<EventExpense> eventExpenseArrayList;
    Context context;

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

        return convertView;
    }
}
