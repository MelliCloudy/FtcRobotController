package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

//Type of Program (Auto or TeleOp)
@Autonomous(name = "auton", group = "auton")


// FR: 0
// BR: 1
// TL: 2
// BL: 3

// temp stuff so i can push Iakfjleridjfopvervji;wekf

public class auton extends LinearOpMode {
    final int FLFrontDir = -1;
    final int FRFrontDir = 1;
    final int BLFrontDir = 1;
    final int BRFrontDir = 1;
    final double sprintMoveMult = 1.2;
    final double sprintTurnMult = 1.2;
    final double brakeMoveMult = 0.7;
    final double brakeTurnMult = 0.7;

    double flywheelPower = 1;

    public void runOpMode() throws InterruptedException {
        GoBildaPinpointDriver pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

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

        /* DcMotor IntakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); */

        Input input = new Input(gamepad1, gamepad2);
        Movement movement = new Movement(LeftFront, LeftBack, RightFront, RightBack, input, pinpoint);
        //Intake intake = new Intake(IntakeMotor, input);

        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.REVERSED);
        pinpoint.setPosition(new Pose2D(DistanceUnit.CM, 0, 0, AngleUnit.RADIANS, 0));
        waitForStart();
        pinpoint.resetPosAndIMU();
        pinpoint.setOffsets(-0.5, 9.5, DistanceUnit.CM);
        pinpoint.recalibrateIMU();

        while (opModeIsActive()) {
            Pose2D error = pinpoint.getPosition();
                    //movement.moveTo(new Pose2D(DistanceUnit.CM, 0.0, 10, AngleUnit.DEGREES, 0.0));
            //m,m,mjPose2D error = pinpoint.getPosition();
            telemetry.addData("X error", error.getX(DistanceUnit.CM));
            telemetry.addData("Y error", error.getY(DistanceUnit.CM));
            telemetry.addData("R error", error.getHeading(AngleUnit.DEGREES));
            telemetry.addData("X raw", pinpoint.getEncoderX());
            telemetry.addData("Y raw", pinpoint.getEncoderY());
            telemetry.update();
            pinpoint.update();
            //intake.run();v

        }
    }
}

