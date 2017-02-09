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

    public EventMoment() {

    }
    public EventMoment(int momentId, int eventId, String title, String description, String momentPhotoPath) {
        this.momentId = momentId;
        this.eventId = eventId;
        Title = title;
        Description = description;
        this.momentPhotoPath = momentPhotoPath;
    }
    public EventMoment(int eventId, String title, String description, String momentPhotoPath) {

        this.eventId = eventId;
        Title = title;
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
}
