package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

//Type of Program (Auto or TeleOp)
@TeleOp (name = "TeleOp", group = "test")


// temp stuff so i can push I

public class Teleop extends LinearOpMode {
    final int FLFrontDir = -1;
    final int FRFrontDir = 1;
    final int BLFrontDir = 1;
    final int BRFrontDir = 1;
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
        DcMotor IntakeMotor = hardwareMap.get(DcMotor.class, "intake");
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor ShootMotor = hardwareMap.get(DcMotor.class, "shooter");
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        /*
        DcMotor RevolverMotor = hardwareMap.get(DcMotor.class, " !!!!!  CHANGE ASAP  !!!!  whatever the revolver motor name will be");
        RevolverMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RevolverMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor ShooterMotor = hardwareMap.get(DcMotor.class, " !!!!!  CHANGE ASAP  !!!!  whatever the shooter motor name will be");
        ShooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ShooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        */
        waitForStart();

        // Movement movement = new Movement();
        // Intake intake = new Intake(IntakeMotor);
        // RevolvingSorter revolvingSorter = new RevolvingSorter(RevolverMotor);
        // Shooter shooter = new Shooter(ShooterMotor);

        boolean intakeOn = false;
        boolean prevIntakeTogglePressed = false;
        boolean outtakeOn = false;
        boolean prevOuttakeTogglePressed = false;
        boolean shooterOn = false;
        boolean prevShooterTogglePressed = false;

        while (opModeIsActive()) {

            //input and whatnot
            double x = gamepad1.left_stick_x, y = -gamepad1.left_stick_y;
            double rot = gamepad1.right_stick_x;
            telemetry.addData("x", x);
            telemetry.addData("y", y);
            telemetry.addData("rot", rot);
            telemetry.update();
            boolean intakeTogglePressed = gamepad1.a;
            boolean shooterPressed = gamepad1.b;
            boolean outtakeTogglePressed = gamepad1.x;

            // ============================= PRECISION & SPD =============================

            if (gamepad1.right_bumper) {
                rot *= sprintTurnMult;
            } else {
                rot *= 1 - (gamepad1.right_trigger * brakeTurnMult);
            }
            if (gamepad1.left_bumper) {
                x *= sprintMoveMult;
                y *= sprintMoveMult;
            } else {
                x *= (1 - (gamepad1.left_trigger * brakeMoveMult));
                y *= (1 - (gamepad1.left_trigger * brakeMoveMult));
            }
            /*
            if (gamepad1.a) LeftFront.setPower(1);
            else LeftFront.setPower(0);
            if (gamepad1.b) RightFront.setPower(1);
            else RightFront.setPower(0);
            if (gamepad1.x) LeftBack.setPower(1);
            else LeftBack.setPower(0);
            if (gamepad1.y) RightBack.setPower(1);
            else RightBack.setPower(0);
            */
            // ============================== MOTION ======================================


            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rot), 1);
            double frontLeftPower = (y + x + rot) / denominator;
            double backLeftPower = (y - x + rot) / denominator;
            double frontRightPower = (y - x - rot) / denominator;
            double backRightPower = (y + x - rot) / denominator;

            LeftFront.setPower(FLFrontDir * frontLeftPower);
            LeftBack.setPower(BLFrontDir * backLeftPower);
            RightFront.setPower(FRFrontDir * frontRightPower);
            RightBack.setPower(BRFrontDir * backRightPower);

            /*
            if (revolverPrecisionMode) {
                revolve *= revolverPrecisionMult;
            }
            */


            // =============================== INTAKE ========================================


            if (intakeTogglePressed && !prevIntakeTogglePressed) {
                intakeOn = !intakeOn;
                outtakeOn = false;
            }
            prevIntakeTogglePressed = intakeTogglePressed;
            if (intakeOn) {
                IntakeMotor.setPower(0.5);
            }

            // =============================== OUTTAKE ========================================


            if (outtakeTogglePressed && !prevOuttakeTogglePressed) {
                outtakeOn = !outtakeOn;
                intakeOn = false;
            }
            prevOuttakeTogglePressed = outtakeTogglePressed;
            if (outtakeOn) {
                IntakeMotor.setPower(-0.5);
            }


            if (!outtakeOn && !intakeOn) {
                IntakeMotor.setPower(0);
            }
            // ================================ SHOOTER =======================================

            if (shooterPressed && !prevShooterTogglePressed) shooterOn = !shooterOn;
            prevShooterTogglePressed = shooterPressed;
            if (shooterOn) {
                ShootMotor.setPower(-1);
            } else {
                ShootMotor.setPower(0);
            }


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
