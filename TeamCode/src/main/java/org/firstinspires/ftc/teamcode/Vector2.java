package org.firstinspires.ftc.teamcode;

public class Vector2 {
    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public static Vector2 fromMagDir(double mag, double dir) {
        return new Vector2(mag * Math.cos(dir), mag * Math.sin(dir));
    }

    public double magnitude() {
        return Math.sqrt(x*x + y*y);
    }
    public static Vector2 add(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }
    public double angle() {
        return Math.atan2(y, x);
    }

    public Vector2 normalize() {
        return Vector2.fromMagDir(1, this.angle());
    }
}
