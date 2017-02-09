package com.gmail.shahidul.er.tourmate.Event.Dao;

import android.database.Cursor;

import com.gmail.shahidul.er.tourmate.Event.Model.Event;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventDAO {

    @Exclude
    public Map<String, Object> save() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", "email");

        return result;
    }

//    public ArrayList<Event> getAll() {
//        db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery(SELECT_PRODUCT_TABLE, null);
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(cursor.getColumnIndex(PRODUCT_ID));
//                String name = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME));
//                String type = cursor.getString(cursor.getColumnIndex(PRODUCT_TYPE));
//                int quantity = cursor.getInt(cursor.getColumnIndex(PRODUCT_QUANTITY));
//
//                product = new Product(id, name, type, quantity);
//                products.add(product);
//            } while (cursor.moveToNext());
//        }
//
//        return events;
//    }
}
