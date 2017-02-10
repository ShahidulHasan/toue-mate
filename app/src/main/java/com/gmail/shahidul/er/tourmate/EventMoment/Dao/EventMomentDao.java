package com.gmail.shahidul.er.tourmate.EventMoment.Dao;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rahat on 2/10/2017.
 */


public class EventMomentDao {
    @Exclude
    public Map<String, Object> save() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", "email");

        return result;
    }
}
