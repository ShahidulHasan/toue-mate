package com.gmail.shahidul.er.tourmate.Event.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gmail.shahidul.er.tourmate.Event.Dao.EventDAO;
import com.gmail.shahidul.er.tourmate.Event.Model.Event;
import com.gmail.shahidul.er.tourmate.R;

import java.util.ArrayList;

public class EventListAdapter extends ArrayAdapter<Event> {

    EventDAO eventDAO;
    ArrayList<Event> eventArrayList;
    Context context;

    public EventListAdapter(Context context, ArrayList<Event> events) {
        super(context, R.layout.activity_event_row, events);
        eventArrayList = events;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final Event event = eventArrayList.get(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_event_row, viewGroup, false);

        TextView location = (TextView) convertView.findViewById(R.id.location);
        TextView cost = (TextView) convertView.findViewById(R.id.cost);
        TextView eventDate = (TextView) convertView.findViewById(R.id.eventDateTV);
        location.setText(event.getLocation());
        cost.setText(""+event.getCost().toString());
        eventDate.setText(""+event.getDate());


        return convertView;
    }
}
