package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class pinpointlarp {
    GoBildaPinpointDriver realpinpoint;
    final double yTPMM = 19.894; // positive means when pod increases, robor moving LEFT
    final double xTPMM = 75.507; // positive means when pod increases, robor moving FORWARD
    // custom is 75.507, 4 bar is 19.894
    final double xOFF = -195; // positive offset means to the LEFT of the robor
    final double yOFF = 0; // positive offset means in FRONT of the robor
    private Pose2D position;
    private double prevhead;
    private double xprev;
    private double yprev;
    Telemetry telemetry;
    public pinpointlarp(GoBildaPinpointDriver realpinpoint, Telemetry telemetry) {
        this.realpinpoint = realpinpoint;
        this.telemetry = telemetry;
        this.position = new Pose2D(DistanceUnit.CM, 0, 0, AngleUnit.DEGREES, 0);
        realpinpoint.resetPosAndIMU();
        try { Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        prevhead = realpinpoint.getHeading(AngleUnit.DEGREES);
        xprev = realpinpoint.getEncoderX() / xTPMM;
        yprev = realpinpoint.getEncoderY() / yTPMM;
        telemetry.addLine("reset");
    }


    private Vector2 arcodom(double pod, double offset, double theta) {
        if (theta != 0) {
            double rad = (360 * pod) / (theta * 2 * Math.PI) - offset;
            double theta2 = (180 - theta) / 2;
            double xupd = rad * Math.sin(theta*Math.PI/180);
            double yupd = xupd / Math.tan(theta2*Math.PI/180);
            return new Vector2(xupd, yupd);
        }
        else return new Vector2(pod, 0);
    }

    void update() {
        double head = realpinpoint.getHeading(AngleUnit.DEGREES);
        double xpod = realpinpoint.getEncoderX() / xTPMM;
        double ypod = realpinpoint.getEncoderY() / yTPMM;
        double rupd = 360;
        if (Math.abs(head - prevhead) < Math.abs(rupd)) {
            rupd = head - prevhead;
        }
        else if (Math.abs(head - (prevhead-360)) < Math.abs(rupd)) {
            rupd = head - prevhead;
        }
        else if (Math.abs(head - (prevhead+360)) < Math.abs(rupd)) {
            rupd = head - prevhead;
        }
        double xupd = xpod - xprev;
        double yupd = ypod - yprev;

        Vector2 xodom = arcodom(xupd, xOFF, -rupd);
        Vector2 yodom = arcodom(yupd, yOFF, -rupd);
        Vector2 translation = new Vector2();
        translation.x = xodom.x - yodom.y;
        translation.y = xodom.y + yodom.x;
        final double rotateangle = prevhead*Math.PI/180;
        position = new Pose2D(DistanceUnit.MM,
                position.getX(DistanceUnit.MM) + translation.x*Math.cos(rotateangle) - translation.y*Math.sin(rotateangle),
                position.getY(DistanceUnit.MM) + translation.x*Math.sin(rotateangle) + translation.y*Math.cos(rotateangle),
                AngleUnit.DEGREES, head);
        prevhead = head;
        xprev = xpod;
        yprev = ypod;
    }

    Pose2D getPosition() {
        return position;
    }
    void setPosition(Pose2D position) {
        this.position = position;
    }
    void resetPositionandIMU() {
        this.position = new Pose2D(DistanceUnit.CM, 0, 0, AngleUnit.DEGREES, 0);
        realpinpoint.recalibrateIMU();
        try { Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        prevhead = realpinpoint.getHeading(AngleUnit.DEGREES);
        xprev = realpinpoint.getEncoderX() / xTPMM;
        yprev = realpinpoint.getEncoderY() / yTPMM;
        telemetry.addLine("reset");
    }
    double getHeading(AngleUnit unit) {
        return realpinpoint.getHeading(unit);
    }
}
