package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auton", group = "test")

public class Auton extends LinearOpMode {
    final int FLFrontDir = -1;
    final int FRFrontDir = 1;
    final int BLFrontDir = 1;
    final int BRFrontDir = 1;


    public void runOpMode() throws InterruptedException {
        DcMotor LeftFront = hardwareMap.get(DcMotor.class, "leftFront");
        LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor RightFront = hardwareMap.get(DcMotor.class, "rightFront");
        RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor LeftBack = hardwareMap.get(DcMotor.class, "leftBack");
        LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor RightBack = hardwareMap.get(DcMotor.class, "rightBack");
        RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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

        while (opModeIsActive()) {
            if (System.currentTimeMillis() - startTime <= 2000) {
                LeftFront.setPower(FLFrontDir * 0.5);
                RightFront.setPower(FRFrontDir * 0.5);
                LeftBack.setPower(BLFrontDir * 0.5);
                RightBack.setPower(BRFrontDir * 0.5);
                telemetry.addData("timer thingy: ", System.currentTimeMillis() - startTime);
                telemetry.update();
            }
            else {
                telemetry.addData("timer thingy: ", "done");
                LeftFront.setPower(0);
                LeftBack.setPower(0);
                RightFront.setPower(0);
                RightBack.setPower(0);
            }
        }
    }
}
