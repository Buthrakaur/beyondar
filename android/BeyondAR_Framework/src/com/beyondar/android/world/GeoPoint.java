package com.beyondar.android.world;

public class GeoPoint {
    public final double latitude, longitude, altitude;

    public GeoPoint(double latitude, double longitude) {
        this(latitude, longitude, 0);
    }

    public GeoPoint(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }
}
