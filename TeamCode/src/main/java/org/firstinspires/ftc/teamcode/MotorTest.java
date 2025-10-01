package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

//Type of Program (Auto or TeleOp)
@TeleOp (name = "TeleOpTest", group = "test")

public class MotorTest extends LinearOpMode {
    final int FLFrontDir = 1;
    final int FRFrontDir = -1;
    final int BLFrontDir = 1;
    final int BRFrontDir = -1;
    final double sprintMoveMult = 1.2;
    final double sprintTurnMult = 1.2;
    final double brakeMoveMult = 0.7;
    final double brakeTurnMult = 0.7;
    public void runOpMode() throws InterruptedException{
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
        waitForStart();

        while (opModeIsActive()) {
            double x = -gamepad1.right_stick_x, y = -gamepad1.right_stick_y;
            double rot = gamepad1.left_stick_x;
            if (gamepad1.left_bumper) { // the game developer in me requires this to be done
                rot *= sprintTurnMult;
            } else {
                rot *= 1 - (gamepad1.left_trigger * brakeTurnMult);
            }
            if (gamepad1.right_bumper) {
                x *= sprintMoveMult;
                y *= sprintMoveMult;

            } else {
                x *= (1 - (gamepad1.right_trigger * brakeMoveMult));
                y *= (1 - (gamepad1.right_trigger * brakeMoveMult));
            }
            telemetry.addData("x", x);
            telemetry.addData("y", y);
            telemetry.addData("rot", rot);
            movement(x, y, rot, LeftFront, LeftBack, RightFront, RightBack);
            //frontForward(y, LeftFront, RightFront);
            //frontHorizontal(x, LeftFront, RightFront);
            //backForward(y, LeftBack, RightBack);
            //backHorizontal(x, LeftBack, RightBack);
            //previousGamepad1.copy(currentGamepad1);
            //previousGamepad2.copy(currentGamepad2);
            //currentGamepad1.copy(gamepad1);
            //currentGamepad2.copy(gamepad2);

        }

        /*
        DcMotor motor = hardwareMap.get(DcMotor.class, "test");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();

        while (opModeIsActive() && gamepad1.a){
            motor.setPower(0.5);
        }
        motor.setPower(0);
        */
    }
    /*
    public void frontForward(double power, DcMotor left, DcMotor right) throws InterruptedException {
        left.setPower(-1);
        right.setPower(1);
    }
    public void frontHorizontal(double power, DcMotor left, DcMotor right) throws InterruptedException {
        left.setPower(power);
        right.setPower(power);
    }
    public void frontReset(DcMotor left, DcMotor right) throws InterruptedException {
        left.setPower(0);
        right.setPower(0);
    }
    public void backForward(double power, DcMotor left, DcMotor right) throws InterruptedException {
        left.setPower(-1);
        right.setPower(1);
    }
    public void backHorizontal(double power, DcMotor left, DcMotor right) throws InterruptedException {
        left.setPower(-power);
        right.setPower(-power);
    }
    public void backReset(DcMotor left, DcMotor right) throws InterruptedException {
        left.setPower(0);
        right.setPower(0);
    }
    */

    void movement(double x, double y, double rot, DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight) {
        final double a = 0.355;
        final double r = 0.0475;
        final double bandaidSolution = 1.2;
        frontLeft.setPower(FLFrontDir * (x - y - (rot * a)) / bandaidSolution);
        frontRight.setPower(FRFrontDir * (x + y + (rot * a)) / bandaidSolution);
        backLeft.setPower(BLFrontDir * (x + y - (rot * a)) / bandaidSolution);
        backRight.setPower(BRFrontDir * (x - y + (rot * a)) / bandaidSolution);
    }
}