package com.gmail.shahidul.er.tourmate.EventMoment.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.shahidul.er.tourmate.EventMoment.Dao.EventMomentDao;
import com.gmail.shahidul.er.tourmate.EventMoment.Model.EventMoment;
import com.gmail.shahidul.er.tourmate.R;

import java.util.ArrayList;

public class EventMomentListAdapter extends ArrayAdapter<EventMoment> {

    EventMomentDao eventMomentDao;

    ArrayList<EventMoment> eventMomentArrayList;
    Bitmap bitmap;
    Context context;

    public EventMomentListAdapter(Context context, ArrayList<EventMoment> eventMoments) {

        super(context, R.layout.activity_event_moment_row, eventMoments);
        eventMomentArrayList = eventMoments;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final EventMoment eventMoment = eventMomentArrayList.get(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_event_moment_row, viewGroup, false);

        TextView eventMomentTitle = (TextView) convertView.findViewById(R.id.eventMomentTitleTV);
        TextView eventMomentDesc = (TextView) convertView.findViewById(R.id.eventMomentDescriptionTV);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.eventMomentPhotoIV);

        eventMomentTitle.setText(eventMoment.getTitle());
        eventMomentDesc.setText(eventMoment.getDescription());

        int targetW = 600;
        int targetH = 150;

        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(eventMoment.getMomentPhotoPath(),options);
        int photoW=options.outWidth;
        int photoH=options.outHeight;

        int scaleFactor=Math.min(photoW/targetW,photoH/targetH);

        options.inJustDecodeBounds = false;
        options.inSampleSize = scaleFactor;

        bitmap = BitmapFactory.decodeFile(eventMoment.getMomentPhotoPath(),options);
        imageView.setImageBitmap(bitmap);

        return convertView;
    }
}
