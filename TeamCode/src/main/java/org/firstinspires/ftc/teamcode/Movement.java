package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.opencv.core.Mat;

public class Movement {
    final int FLFrontDir = -1;
    final int FRFrontDir = 1;
    final int BLFrontDir = 1;
    final int BRFrontDir = 1;
    final double slowMult = 0.3;


    DcMotor LeftFront, LeftBack, RightFront, RightBack;
    Input input;
    IMU imu;
    public Movement(DcMotor LF, DcMotor LB, DcMotor RF, DcMotor RB, Input input, IMU imu) {
        LeftFront = LF;
        LeftBack = LB;
        RightFront = RF;
        RightBack = RB;
        this.input = input;
        this.imu = imu;
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
        if (input.reset()) {
            imu.resetYaw();
        }
        double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double rotatedx = x*Math.cos(2*Math.PI-heading) - y*Math.sin(2*Math.PI-heading);
        double rotatedy = x*Math.sin(2*Math.PI-heading) + y*Math.cos(2*Math.PI-heading);
        move(rotatedx, rotatedy, rot);
        return heading;
    }
}
