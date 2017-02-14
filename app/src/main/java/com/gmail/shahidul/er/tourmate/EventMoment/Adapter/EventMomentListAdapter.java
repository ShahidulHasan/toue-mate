package com.gmail.shahidul.er.tourmate.EventMoment.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.shahidul.er.tourmate.EventMoment.Activity.EventMomentActivity;
import com.gmail.shahidul.er.tourmate.EventMoment.Activity.MomentViewActivity;
import com.gmail.shahidul.er.tourmate.EventMoment.Dao.EventMomentDao;
import com.gmail.shahidul.er.tourmate.EventMoment.Model.EventMoment;
import com.gmail.shahidul.er.tourmate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventMomentListAdapter extends ArrayAdapter<EventMoment> {

    EventMomentDao eventMomentDao;

    ArrayList<EventMoment> eventMomentArrayList;
    Bitmap bitmap;
    Context context;
    private DatabaseReference mDatabase;

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

        int targetW = 500;
        int targetH = 100;

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

        Button eventMomentDeleteBtn = (Button) convertView.findViewById(R.id.eventMomentDeleteBtn);

        eventMomentDeleteBtn.setOnClickListener(new View.OnClickListener() {


           DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

            Query applesQuery = mDatabase.child("eventMoments").orderByChild("eventId").equalTo(eventMoment.getEventId());
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
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
                                Intent intent = new Intent(context, MomentViewActivity.class);
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


               /* Intent intent = new Intent(context.getApplicationContext(), ProductUpdateActivity.class);
                intent.putExtra("products", product);
                context.startActivity(intent);*/
            }
        });

        return convertView;
    }

}
