package com.github.gcacace.signaturepad.utils;

public class TimedPoint {
    public float x;
    public float y;
    public long timestamp;

    public TimedPoint set(float x, float y) {
        this.x = x;
        this.y = y;
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public float velocityFrom(TimedPoint start) {
        long diff = this.timestamp - start.timestamp;
        if (diff <= 0) {
            diff = 1;
        }
        float velocity = vectorRatio(start) ;
        if (Float.isInfinite(velocity) || Float.isNaN(velocity)) {
            velocity = 0;
        }
        return velocity;
    }

    public float distanceTo(TimedPoint point) {
//        return (float) Math.sqrt(Math.pow(point.x - this.x, 2) + Math.pow(point.y - this.y, 2));
        return (float) Math.sqrt(Math.pow(point.x - this.x, 2));

    }

    public float vectorRatio(TimedPoint point) {
        float maxSpeed = 2f;
        float minSpeed = 0.5f;
        float vx = Math.abs(point.x - this.x);
        float vy = Math.abs(point.y - this.y);
        if (vy == 0) {//drawing a line down
            return minSpeed;
        } else {
            return (float) (maxSpeed * vx / Math.sqrt(Math.pow(vy, 2) + Math.pow(vx, 2)));
        }
    }
}
