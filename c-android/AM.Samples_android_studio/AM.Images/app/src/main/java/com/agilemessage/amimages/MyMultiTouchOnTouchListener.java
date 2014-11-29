package com.agilemessage.amimages;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MyMultiTouchOnTouchListener implements OnTouchListener {
    private static final String TAG = "Touch";

    Context context;

    // These matrices will be used to move and zoom image
    Matrix matrix = new Matrix(); // constructor creates identity matrix
    Matrix savedMatrix = new Matrix(); // constructor creates identity matrix

    // We can be in one of these 3 states
    public enum Mode {
        NONE, DRAG, ZOOM, THREE_FINGER
    }

    Mode mode = Mode.NONE;

    // static final int NONE = 0;
    // static final int DRAG = 1;
    // static final int ZOOM = 2;
    // int mode = NONE;

    // Remember some things for zooming
    PointF startPT = new PointF();
    PointF midPT = new PointF();
    float oldDist = 1f;
    float zoomLimitUp = 100.0f;
    float zoomLimitDown = 0.1f;
    double scaleAccumulated = 1.0;

    MyMultiTouchOnTouchListener(Context context) {
        this.context = context;
    }

    void setZoomLimit(float zoomUp, float zoomDown) {
        zoomLimitUp = zoomUp;
        zoomLimitDown = zoomDown;
    }

    /**
     * Show an event in the LogCat view, for debugging
     */
    private void dumpEvent(MotionEvent event) {
        String names[] = {"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
                "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"};
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);
        if (actionCode == MotionEvent.ACTION_POINTER_DOWN
                || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(
                    action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }
        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }
        sb.append("]");
        Log.d(TAG, sb.toString());
    }

    /**
     * Determine the space between the first two fingers
     */
    private float spacingBetweenFirstTwoPoints(MotionEvent event) {
        float delta_x = event.getX(0) - event.getX(1);
        float delta_y = event.getY(0) - event.getY(1);
        // Euclidian distance between first 2 points
        return FloatMath.sqrt(delta_x * delta_x + delta_y * delta_y);
    }

    /**
     * Calculate the mid point of the first two fingers
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

//		Log.d(TAG, "================== onTouch");

        ImageView view = (ImageView) v;

        // Dump touch event to log
        dumpEvent(event);

        // Handle touch events here...
        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            // one finger
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix); // savedMatrix = matrix;
                startPT.set(event.getX(), event.getY());
                Log.d(TAG, "mode=DRAG");
                mode = Mode.DRAG;
                break;

            // two (actually more than one) finger
            case MotionEvent.ACTION_POINTER_DOWN:
                int numFingers = event.getPointerCount();

//            Toast.makeText(context, "numFingers = " + numFingers,
//                    Toast.LENGTH_SHORT).show();
                Log.d(TAG, "++++++++ numFingers = " + numFingers);

                oldDist = spacingBetweenFirstTwoPoints(event);
                Log.d(TAG, "oldDist=" + oldDist);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix); // savedMatrix = matrix;
                    midPoint(midPT, event);
                }
                if (numFingers == 2) {
                    mode = Mode.ZOOM;
                    Log.d(TAG, "mode=ZOOM");
                }
                if (numFingers == 3) {
                    mode = Mode.THREE_FINGER;
                    Log.d(TAG, "mode=THREE_FINGER");
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = Mode.NONE;
                Log.d(TAG, "mode=NONE");
                break;

            case MotionEvent.ACTION_MOVE:
                // one finger
                if (mode == Mode.DRAG) {
                    // ...
                    matrix.set(savedMatrix); // matrix = savedMatrix;
                    matrix.postTranslate(event.getX() - startPT.x, event.getY()
                            - startPT.y);
                }

                // two fingers
                if (mode == Mode.ZOOM) {
                    float newDist = spacingBetweenFirstTwoPoints(event);
                    Log.d(TAG, "newDist=" + newDist);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix); // matrix = savedMatrix;
                        double scale = newDist / oldDist;
                        scaleAccumulated *= scale;


                        matrix.postScale((float) scale, (float) scale, midPT.x, midPT.y);

//					if (scaleAccumulated < zoomLimitUp && scaleAccumulated > zoomLimitDown)
//						matrix.postScale((float)scale, (float)scale, midPT.x, midPT.y);
//					else {
//						Toast.makeText(context, "zoom blocked", Toast.LENGTH_SHORT)
//						.show();
//
//						if (scaleAccumulated > zoomLimitUp) {
//							scaleAccumulated = zoomLimitUp;
//						}
//						if (scaleAccumulated < zoomLimitDown)
//							scaleAccumulated = zoomLimitDown;
////						matrix.postScale((float)scaleOriginal, (float)scaleOriginal, midPT.x, midPT.y);
//						matrix.postScale(1.0f, 1.0f, midPT.x, midPT.y);
//					}									
                    }
                }

                    // 3 fingers
                    if (mode == Mode.THREE_FINGER) {
                        float newDist = spacingBetweenFirstTwoPoints(event);
                        Log.d(TAG, "newDist=" + newDist);
                        if (newDist > 10f) {
                            matrix.set(savedMatrix); // matrix = savedMatrix;
                            double scale = newDist / oldDist;
                            scaleAccumulated *= 0.1 * scale;
                            matrix.postScale((float) scale, (float) scale, midPT.x, midPT.y);
                        }

                    }
                    break;
        }


                view.setImageMatrix(matrix);

                return true; // indicate event was handled
    }

}
