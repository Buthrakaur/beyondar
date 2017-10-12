package com.beyondar.android.opengl.renderable;

import android.graphics.Color;

import com.beyondar.android.opengl.texture.Texture;
import com.beyondar.android.util.math.geom.Plane;
import com.beyondar.android.util.math.geom.Point3;
import com.beyondar.android.world.BeyondarObject;
import com.beyondar.android.world.GeoPolylineObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class PolylineRenderable extends SquareRenderable {
    private PolylineRenderable() {
        super();
    }

    private static PolylineRenderable mThis;
    public synchronized static Renderable getInstance() {
        if (mThis == null) {
            mThis = new PolylineRenderable();
        }
        return mThis;
    }

    @Override
    public boolean update(long time, double distance, BeyondarObject beyondarObject) {
        return super.update(time, distance, beyondarObject);
    }

    @Override
    public void draw(GL10 gl, Texture defaultTexture) {
        GeoPolylineObject polyline = (GeoPolylineObject) mBeyondarObject;

        Point3 position = polyline.getPosition();
        gl.glTranslatef(position.x, position.y, position.z);

        Point3 angle = polyline.getAngle();
        gl.glRotatef(angle.x, 1, 0, 0);
        gl.glRotatef(angle.y, 0, 1, 0);
        gl.glRotatef(angle.z, 0, 0, 1);

        // Enabled the vertices buffer for writing and to be used during rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        float vertices[] = new float[3*polyline.positions.size()];
        for (int i = 0; i < polyline.positions.size(); i++) {
            Point3 point = polyline.positions.get(i);
            vertices[3*i] = point.x;
            vertices[3*i + 1] = point.y;
            vertices[3*i + 2] = point.z;
        }

        FloatBuffer vertexBuffer;
        // a float is 4 bytes, therefore we multiply the number of vertices with 4.
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        int color = polyline.color;
        gl.glColor4f(Color.red(color) / 255f, Color.green(color) / 255f,Color.blue(color) / 255f, Color.alpha(color) / 255f);
        gl.glLineWidth(10);

        // Set the face rotation
        gl.glFrontFace(GL10.GL_CW);

        // Specifies the location and data format of an array of vertex
        // coordinates to use when rendering.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, vertices.length / 3);

        gl.glColor4f(1, 1, 1, 1);

        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glRotatef(angle.x, -1, 0, 0);
        gl.glRotatef(angle.y, 0, -1, 0);
        gl.glRotatef(angle.z, 0, 0, -1);

        gl.glTranslatef(-position.x, -position.y, -position.z);
    }

    @Override
    public Plane getPlane() {
        return super.getPlane();
    }
}
