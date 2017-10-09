package com.beyondar.android.world;

import com.beyondar.android.opengl.renderable.Renderable;
import java.util.Collections;
import java.util.List;

public class GeoPolylineObject extends GeoObject {

    public GeoPolylineObject(long id) {
        super(id);
    }

    public GeoPolylineObject() {
    }

    private List<GeoPoint> polyline;

    public void setPolyline(List<GeoPoint> points){
        if (points.size() > 0 && getLatitude() == 0 && getLongitude() == 0){
            GeoPoint firstPoint = points.get(0);
            setGeoPosition(firstPoint.latitude, firstPoint.longitude, firstPoint.altitude);
        }
        polyline = Collections.unmodifiableList(points);
    }

    @Override
    protected Renderable createRenderable() {
        return super.createRenderable();
    }
}
