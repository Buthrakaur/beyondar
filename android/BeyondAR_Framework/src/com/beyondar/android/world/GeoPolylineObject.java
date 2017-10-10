package com.beyondar.android.world;

import com.beyondar.android.opengl.renderable.Renderable;
import com.beyondar.android.util.math.geom.Point3;

import java.util.Collections;
import java.util.List;

public class GeoPolylineObject extends GeoObject {

    public GeoPolylineObject(long id) {
        super(id);
    }

    public GeoPolylineObject() {
    }

    @Override
    public double calculateDistanceMeters(GeoPoint geo) {
        double distance = Double.MAX_VALUE;
        for (GeoPoint p : polyline) {
            double pd = p.calculateDistanceMeters(geo);
            if (pd < distance) distance = pd;
        }
        return distance;
    }

    private List<GeoPoint> polyline;

    public void setPolyline(List<GeoPoint> points){
        if (points.size() > 0 && getLatitude() == 0 && getLongitude() == 0){
            GeoPoint firstPoint = points.get(0);
            setGeoPosition(firstPoint.latitude, firstPoint.longitude, firstPoint.altitude);
        }
        polyline = Collections.unmodifiableList(points);
    }

    private List<Point3> pointPositions;

    public void setPositions(List<Point3> points){
        pointPositions = points;
    }

    @Override
    protected Renderable createRenderable() {
        return super.createRenderable();
    }
}
