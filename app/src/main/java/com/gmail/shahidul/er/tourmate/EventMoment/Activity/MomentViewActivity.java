package com.gmail.shahidul.er.tourmate.EventMoment.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gmail.shahidul.er.tourmate.EventMoment.Model.EventMoment;
import com.gmail.shahidul.er.tourmate.R;

public class MomentViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment_view);

       EventMoment eventMoment = (EventMoment) getIntent().getSerializableExtra("eventMoment");

        Toast.makeText(this, eventMoment.getMomentPhotoPath(), Toast.LENGTH_SHORT).show();
    }
}
