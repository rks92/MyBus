package com.shardr.mybuslocation.parsemodels;

import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by shardr on 12/9/2015.
 */
public class Student {
    public static class BusRoutesStrings{
        public static final String OBJECT_ID = "objectId";
        public static final String NAME = "Name";
        public static final String CREATED_AT = "createdAt";
        public static final String UPDATED_AT = "updatedAt";
        public static final String PHONE_NUMBER = "PhoneNumber";
        public static final String STOP_IDENTIFIER = "StopIdentifier";
    }

    private String objectId;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private String phoneNumber;
    private String stopIdentifier;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStopIdentifier() {
        return stopIdentifier;
    }

    public void setStopIdentifier(String stopIdentifier) {
        this.stopIdentifier = stopIdentifier;
    }
}
