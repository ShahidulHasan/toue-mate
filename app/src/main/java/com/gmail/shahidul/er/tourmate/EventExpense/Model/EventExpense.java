package com.gmail.shahidul.er.tourmate.EventExpense.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rahat on 2/13/17.
 */

public class EventExpense implements Serializable {

    private String expenseId;
    private String eventId;
    private String userEmail;
    private String expenseCause;
    private String expenseDate;
    private  Float expenseCost;
    private Date createdAt;

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getExpenseCause() {
        return expenseCause;
    }

    public void setExpenseCause(String expenseCause) {
        this.expenseCause = expenseCause;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public Float getExpenseCost() {
        return expenseCost;
    }

    public void setExpenseCost(Float expenseCost) {
        this.expenseCost = expenseCost;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
