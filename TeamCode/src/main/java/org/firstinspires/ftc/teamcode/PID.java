package org.firstinspires.ftc.teamcode;
import java.util.*;
public class PID {
    private double prevError, integralSum;
    private double cP, cI, cD, max;
    public PID(double newcP, double newcI, double newcD, double newMax) {
        prevError = 0;
        integralSum = 0;
        cP = newcP;
        cI = newcI;
        cD = newcD;
        max = newMax;
    }
    public void reset() {
        prevError = 0;
    }
    public double update(double error) {
        double P = cP * error;
        double I = cI * (error + integralSum);
        double D = cD * (error - prevError);
        prevError = error;
        integralSum += error;
        double ret = P + I + D;
        return Math.max(Math.min(ret, max), -1*max);
    }
}
 // dksl;jafkdla;fjkd