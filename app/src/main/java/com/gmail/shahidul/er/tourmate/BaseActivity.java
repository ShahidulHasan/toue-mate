package com.gmail.shahidul.er.tourmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.gmail.shahidul.er.tourmate.Home.Activity.HomeActivity;
import com.gmail.shahidul.er.tourmate.Location.Activity.MapsActivity;
import com.gmail.shahidul.er.tourmate.User.Activity.LoginActivity;

/**
 * Created by rahat on 2/9/17.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.home_menu:
                // go to home page
                Intent homePageIntent = new Intent(BaseActivity.this,HomeActivity.class);
                startActivity(homePageIntent);
                break;

            case R.id.near_by_menu:
                Intent nearByIntent = new Intent(BaseActivity.this,MapsActivity.class);
                startActivity(nearByIntent);
                break;
            case R.id.weather_menu:
                //Weather
                Intent intent = new Intent(BaseActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.logout_menu:

                SharedPreferences saveUserData = getSharedPreferences("UserInfo",MODE_PRIVATE );

                SharedPreferences.Editor editor = saveUserData.edit();

                String username = saveUserData.getString("email","");
                editor.remove("username");
                editor.apply();
                editor.commit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
