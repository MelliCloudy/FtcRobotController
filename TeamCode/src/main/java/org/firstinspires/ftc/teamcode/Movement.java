package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.opencv.core.Mat;
import  com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

public class Movement {
    final int FLFrontDir = -1;
    final int FRFrontDir = 1;
    final int BLFrontDir = 1;
    final int BRFrontDir = 1;
    final double slowMult = 0.2;
    double basedir = 0;
    GoBildaPinpointDriver pinpoint;
    DcMotor LeftFront, LeftBack, RightFront, RightBack;
    Input input;

    public Movement(DcMotor LF, DcMotor LB, DcMotor RF, DcMotor RB, Input input, GoBildaPinpointDriver pinpoint) {
        LeftFront = LF;
        LeftBack = LB;
        RightFront = RF;
        RightBack = RB;
        this.input = input;
        this.pinpoint = pinpoint;
    }
    private void move(double x, double y, double rot) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rot), 1);
        double frontLeftPower = (y + x + rot) / denominator;
        double backLeftPower = (y - x + rot) / denominator;
        double frontRightPower = (y - x - rot) / denominator;
        double backRightPower = (y + x - rot) / denominator;

        LeftFront.setPower(FLFrontDir * frontLeftPower);
        LeftBack.setPower(BLFrontDir * backLeftPower);
        RightFront.setPower(FRFrontDir * frontRightPower);
        RightBack.setPower(BRFrontDir * backRightPower);
    }
    public double run() {
        double x, y, rot;
        x = input.moveX();
        y = input.moveY();
        rot = input.rotation();
        if (input.slow()) {
            x *= slowMult;
            y *= slowMult;
            rot *= slowMult;
        }
        double heading = pinpoint.getHeading(AngleUnit.RADIANS);
        if (input.reset()) {
            basedir = heading;
        }
        heading -= basedir;
        double rotatedx = x*Math.cos(2*Math.PI-heading) - y*Math.sin(2*Math.PI-heading);
        double rotatedy = x*Math.sin(2*Math.PI-heading) + y*Math.cos(2*Math.PI-heading);
        move(rotatedx, rotatedy, rot);
        return heading;
    }

    PID xpid = new PID(0.5, 0, 0.5, 1);
    PID ypid = new PID(0.5, 0, 0.5, 1);;
    PID rpid = new PID(0.5, 0, 0.5, 1);;
    public Pose2D moveTo(Pose2D desiredPos) {
        Pose2D currentPos = pinpoint.getPosition();
        double xerror = desiredPos.getX(DistanceUnit.CM) - currentPos.getX(DistanceUnit.CM);
        double yerror = desiredPos.getY(DistanceUnit.CM) - currentPos.getY(DistanceUnit.CM);
        double rerror = desiredPos.getHeading(AngleUnit.DEGREES) - currentPos.getHeading(AngleUnit.DEGREES);
        double moveX = xpid.update(xerror);
        double moveY = ypid.update(yerror);
        double moveR = rpid.update(rerror);
        double heading = pinpoint.getHeading(AngleUnit.RADIANS);
        double rotatedx = moveX*Math.cos(2*Math.PI-heading) - moveY*Math.sin(2*Math.PI-heading);
        double rotatedy = moveX*Math.sin(2*Math.PI-heading) + moveY*Math.cos(2*Math.PI-heading);
        move(rotatedx, rotatedy, moveR);
        return pinpoint.getPosition();
    }
}
