package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

//Type of Program (Auto or TeleOp)
@TeleOp (name = "TeleOpTest", group = "test")

public class Teleop extends LinearOpMode {
    final int FLFrontDir = 1;
    final int FRFrontDir = -1;
    final int BLFrontDir = 1;
    final int BRFrontDir = -1;
    final double sprintMoveMult = 1.2;
    final double sprintTurnMult = 1.2;
    final double brakeMoveMult = 0.7;
    final double brakeTurnMult = 0.7;

    final double revolverPrecisionMult = 0.1;


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
        DcMotor IntakeMotor = hardwareMap.get(DcMotor.class, " !!!!!  CHANGE ASAP  !!!!  whatever the intake motor name will be");
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor RevolverMotor = hardwareMap.get(DcMotor.class, " !!!!!  CHANGE ASAP  !!!!  whatever the intake motor name will be");
        RevolverMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RevolverMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Movement movement = new Movement();
        Intake intake = new Intake(IntakeMotor);
        RevolvingSorter revolvingSorter = new RevolvingSorter(RevolverMotor);

        while (opModeIsActive()) {

            //input and whatnot
            double x = gamepad1.right_stick_x, y = -gamepad1.right_stick_y;
            double rot = gamepad1.left_stick_y;
            double revolve = gamepad2.left_stick_x;
            boolean revolverPrecisionMode = gamepad2.a;
            boolean intakePressed = gamepad1.b;

            // the game developer in me requires this to be done
            if (gamepad1.left_bumper) {
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

            if (revolverPrecisionMode) {
                revolve *= revolverPrecisionMult;
            }


            // calling the random ahh shat i coded
            if (intakePressed) intake.toggleIntake();
            movement.move(x, y, rot, LeftFront, LeftBack, RightFront, RightBack);
            revolvingSorter.rotate(revolve);



            telemetry.addData("x", x);
            telemetry.addData("y", y);
            telemetry.addData("rot", rot);
            telemetry.update();

        }

    }
}



/*
   /\ /\
 =('w'  )=
  (||  _^)__

    ___
   /   \`\
  /     \ \
 / .u.   \ \
/         \ \
(  (  )   ) /
 \_______/-
 */