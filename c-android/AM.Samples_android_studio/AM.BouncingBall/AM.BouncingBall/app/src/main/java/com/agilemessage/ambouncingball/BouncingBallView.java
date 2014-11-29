package com.agilemessage.ambouncingball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class BouncingBallView extends View {

    private int xMin = 0; // This view's bounds
    private int xMax;
    private int yMin = 0;
    private int yMax;
    private float ballRadius = 30; // Ball's radius
    private float ballX; // Ball's center (x,y)
    private float ballY;
    private float ballSpeedX = 5; // Ball's speed (x,y)
    private float ballSpeedY = 10;
    private RectF ballBounds; // Needed for Canvas.drawOval
    private Paint paint; // The paint (e.g. style, color) used for drawing
    private int speepMs = 20;

    // Constructor
    public BouncingBallView(Context context) {
        super(context);
        ballBounds = new RectF();
        paint = new Paint();
    }

    // Called back to draw the view. Also called by invalidate().
    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the ball
        ballBounds.set(ballX - ballRadius, ballY - ballRadius, ballX
                + ballRadius, ballY + ballRadius);
        paint.setColor(Color.GREEN);
        canvas.drawOval(ballBounds, paint);
        // canvas.drawLine(startX, startY, stopX, stopY, paint)

//		// triangle points
//		int x [] = {0, 100, 200, 0};
//		int y [] = {0, 100, 0, 0};
//		
//		Paint trianglePaint = new Paint();
//		trianglePaint.setColor(Color.GREEN);
//		trianglePaint.setStyle(Style.FILL);
//
//		Path trianglePath = new Path();		
//		trianglePath.reset(); // only needed when reusing this path for a new build
//		trianglePath.moveTo(x[0], y[0]); // used for first point
//		trianglePath.lineTo(x[1], y[1]);
//		trianglePath.lineTo(x[2], y[2]);
//		trianglePath.lineTo(x[3], y[3]);
//		trianglePath.lineTo(x[0], y[0]); // there is a setLastPoint action but i
//										// found it not to work as expected
//		canvas.drawPath(trianglePath, trianglePaint);

        // Update the position of the ball, including collision detection and
        // reaction.
        update();

        // Delay
        try {
            Thread.sleep(speepMs);
        } catch (InterruptedException e) {
        }

        invalidate(); // Force a re-draw, call update method
    }

    // Detect collision and update the position of the ball.
    private void update() {
        // Get new (x,y) position
        ballX += ballSpeedX;
        ballY += ballSpeedY;
        // Detect collision and react
        if (ballX + ballRadius > xMax) {
            ballSpeedX = -ballSpeedX;
            ballX = xMax - ballRadius;
        } else if (ballX - ballRadius < xMin) {
            ballSpeedX = -ballSpeedX;
            ballX = xMin + ballRadius;
        }
        if (ballY + ballRadius > yMax) {
            ballSpeedY = -ballSpeedY;
            ballY = yMax - ballRadius;
        } else if (ballY - ballRadius < yMin) {
            ballSpeedY = -ballSpeedY;
            ballY = yMin + ballRadius;
        }
    }

    // Called back when the view is first created or its size changes.
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        xMax = w - 1;
        yMax = h - 1;
    }
}
