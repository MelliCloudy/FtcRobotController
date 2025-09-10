package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

//Type of Program (Auto or TeleOp)
@TeleOp (name = "TeleOpTest", group = "test")

public class MotorTest extends LinearOpMode {
    public void runOpMode() throws InterruptedException{
        DcMotor LeftFront = hardwareMap.get(DcMotor.class, "leftFront");
        LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();
        DcMotor RightFront = hardwareMap.get(DcMotor.class, "rightFront");
        RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();
        DcMotor LeftBack = hardwareMap.get(DcMotor.class, "leftBack");
        LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();
        DcMotor RightBack = hardwareMap.get(DcMotor.class, "rightBack");
        RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();

        while (opModeIsActive()) {
            double x = gamepad1.left_stick_x, y = -gamepad1.left_stick_y;
            frontForward(y, LeftFront, LeftBack);
            frontHorizontal(x, LeftFront, LeftBack);
            x = gamepad1.right_stick_x;
            y = -gamepad1.right_stick_y;
            frontForward(y, LeftFront, LeftBack);
            frontHorizontal(x, LeftFront, LeftBack);
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
    public void frontForward(double power, DcMotor left, DcMotor right) throws InterruptedException {
        left.setPower(power);
        right.setPower(-power);
    }
    public void frontHorizontal(double power, DcMotor left, DcMotor right) throws InterruptedException {
        left.setPower(-power);
        right.setPower(-power);
    }
    public void frontReset(DcMotor left, DcMotor right) throws InterruptedException {
        left.setPower(0);
        right.setPower(0);
    }
    public void backForward(double power, DcMotor left, DcMotor right) throws InterruptedException {
        left.setPower(power);
        right.setPower(-power);
    }
    public void backHorizontal(double power, DcMotor left, DcMotor right) throws InterruptedException {
        left.setPower(power);
        right.setPower(power);
    }
    public void backReset(DcMotor left, DcMotor right) throws InterruptedException {
        left.setPower(0);
        right.setPower(0);
    }
}

