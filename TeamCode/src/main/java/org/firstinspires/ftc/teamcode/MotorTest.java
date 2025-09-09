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
        DcMotor motor = hardwareMap.get(DcMotor.class, "test");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();

        while (opModeIsActive()) {
            //previousGamepad1.copy(currentGamepad1);
            //previousGamepad2.copy(currentGamepad2);
            //currentGamepad1.copy(gamepad1);
            //currentGamepad2.copy(gamepad2);
            if (gamepad1.a) motor.setPower(gamepad1.left_stick_y);
            else motor.setPower(0);
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
}
