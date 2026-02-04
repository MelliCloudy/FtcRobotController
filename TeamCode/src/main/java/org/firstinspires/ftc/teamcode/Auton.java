package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.*;
@Autonomous(name = "Auton", group = "test")

public class Auton extends LinearOpMode {
    final int FLFrontDir = -1;
    final int FRFrontDir = 1;
    final int BLFrontDir = 1;
    final int BRFrontDir = 1;
    // ticks per rot = 537.7, meters per rot = 0.32672563597
    final double TPM = 1645.72332501;

    public void runOpMode() throws InterruptedException {
        DcMotor LeftFront = hardwareMap.get(DcMotor.class, "leftFront");
        LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DcMotor RightFront = hardwareMap.get(DcMotor.class, "rightFront");
        RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DcMotor LeftBack = hardwareMap.get(DcMotor.class, "leftBack");
        LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DcMotor RightBack = hardwareMap.get(DcMotor.class, "rightBack");
        RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        /*
        DcMotor RevolverMotor = hardwareMap.get(DcMotor.class, " !!!!!  CHANGE ASAP  !!!!  whatever the revolver motor name will be");
        RevolverMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RevolverMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor ShooterMotor = hardwareMap.get(DcMotor.class, " !!!!!  CHANGE ASAP  !!!!  whatever the shooter motor name will be");
        ShooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ShooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        */

        waitForStart();
        long startTime = System.currentTimeMillis();

        final double encoderRes = 537.7;
        final double circumference = 30.159;
        final double tickPcm = encoderRes / circumference;

        PID horiError = new PID();
        PID vertError = new PID();

        while (opModeIsActive()) {

            horiError.setCoeff(0.5, 0.0, 0.2);
            vertError.setCoeff(0.5, 0.0, 0.2);
            double LFpos = LeftFront.getCurrentPosition()/tickPcm;
            double RFpos = RightFront.getCurrentPosition()/tickPcm;
            double LBpos = LeftBack.getCurrentPosition()/tickPcm;
            double RBpos = RightBack.getCurrentPosition()/tickPcm;

            double x = Math.min(Math.max(horiError.update(((LFpos+LBpos)/2.0)-((RFpos+RBpos)/2.0))*0.6, -1.0), 1.0);
            double y = vertError.update(100-((LFpos+LBpos+RFpos+RBpos)/4.0));

            double denominator = Math.max(Math.abs(y) + Math.abs(x), 1);
            double frontLeftPower = (y + x) / denominator;
            double backLeftPower = (y - x) / denominator;
            double frontRightPower = (y - x) / denominator;
            double backRightPower = (y + x) / denominator;

            //*
            LeftFront.setPower(FLFrontDir * frontLeftPower);
            LeftBack.setPower(BLFrontDir * backLeftPower);
            RightFront.setPower(FRFrontDir * frontRightPower);
            RightBack.setPower(BRFrontDir * backRightPower); //*/
        }
    }
}
/*

left goal:
fwd 125
lft 135
ink {
fwd 85
ink }
bkw 85
lft 45
sht
rgt 135

*/