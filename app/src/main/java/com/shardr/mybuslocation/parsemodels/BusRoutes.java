package com.shardr.mybuslocation.parsemodels;

import com.parse.ParseGeoPoint;
import com.parse.ParseRelation;

import java.util.Date;

/**
 * Created by shardr on 12/9/2015.
 */
public class BusRoutes {
    static class BusRoutesStrings{
        public static final String OBJECT_ID = "objectId";
        public static final String BUS_ROUTE_IDENTIFIER = "BusRouteIdentifier";
        public static final String CREATED_AT = "createdAt";
        public static final String UPDATED_AT = "updatedAt";
        public static final String ROUTE_NAME = "RouteName";
        public static final String STUDENTS = "Students";
        public static final String IN_PROGRESS = "InProgress";
        public static final String LAST_UPDATED_LOCATION = "LastUpdatedLocation";
        public static final String START_TIME = "StartTime";
        public static final String END_TIME = "EndTime";
        public static final String STOPS = "Stops";
    }

    private String objectId;
    private String BusRouteIdentifier;
    private Date createdAt;
    private Date updatedAt;
    private String routeName;
    private ParseRelation[] students;
    private boolean inProgress;
    private ParseGeoPoint lastUpdatedLocation;
    private Date startTime;
    private Date endTime;
    private ParseRelation[] stops;
}
