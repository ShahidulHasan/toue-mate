package com.gmail.shahidul.er.tourmate.Event.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.Date;

@IgnoreExtraProperties
public class Event implements Serializable {

    private int id;
    private String location;
    private String email;
    private Date date;
    private Date createdAt;
    private Float cost;
    private Float expense;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getExpense() {
        return expense;
    }

    public void setExpense(Float expense) {
        this.expense = expense;
    }
}
