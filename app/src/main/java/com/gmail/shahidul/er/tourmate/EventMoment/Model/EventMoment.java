package com.gmail.shahidul.er.tourmate.EventMoment.Model;

import java.io.Serializable;

/**
 * Created by rahat on 2/8/2017.
 */

public class EventMoment implements Serializable {

    private int momentId;
    private int  eventId;
    private  String Title;
    private String Description;
    private String momentPhotoPath;
    private String userEmail;

    public EventMoment() {

    }
    public EventMoment(int momentId, int eventId, String title, String description, String momentPhotoPath,String userEmail) {
        this.momentId = momentId;
        this.userEmail = userEmail;
        this.eventId = eventId;
        this.Title = title;
        Description = description;
        this.momentPhotoPath = momentPhotoPath;
    }
    public EventMoment(int eventId, String title, String description, String momentPhotoPath,String userEmail) {

        this.eventId = eventId;
        this.userEmail = userEmail;
        this.Title = title;
        Description = description;
        this.momentPhotoPath = momentPhotoPath;
    }

    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMomentPhotoPath() {
        return momentPhotoPath;
    }

    public void setMomentPhotoPath(String momentPhotoPath) {
        this.momentPhotoPath = momentPhotoPath;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
