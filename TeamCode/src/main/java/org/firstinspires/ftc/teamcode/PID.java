package org.firstinspires.ftc.teamcode;
import java.util.*;
public class PID {
    private float prevError, integralSum;
    private float cP, cI, cD;
    public PID() {
        prevError = 0;
        integralSum = 0;
        cP = 0;
        cI = 0;
        cD = 0;
    }
    public float reset() {
        return prevError = 0;
    }
    public void setCoeff(float newcP, float newcI, float newcD) {
        cP = newcP;
        cI = newcI;
        cD = newcD;
    }
    public float update(float error) {
        float P = cP * error;
        float I = cI * (error + integralSum);
        float D = cD * (error - prevError);
        prevError = error;
        integralSum += error;
        return P + I + D;
    }
}
