package org.firstinspires.ftc.teamcode;
import java.util.*;
public class PID {
    private double prevError, integralSum;
    private double cP, cI, cD;
    public PID() {
        prevError = 0;
        integralSum = 0;
        cP = 0;
        cI = 0;
        cD = 0;
    }
    public void reset() {
        prevError = 0;
    }
    public void setCoeff(double newcP, double newcI, double newcD) {
        cP = newcP;
        cI = newcI;
        cD = newcD;
    }
    public double update(double error) {
        double P = cP * error;
        double I = cI * (error + integralSum);
        double D = cD * (error - prevError);
        prevError = error;
        integralSum += error;
        return P + I + D;
    }
}
