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
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

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
        GoBildaPinpointDriver OGpinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        Limelight3A limelight = hardwareMap.get(Limelight3A.class, "limelight");

        DcMotor LeftFront = hardwareMap.get(DcMotor.class, "leftFront");
        LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        DcMotor RightFront = hardwareMap.get(DcMotor.class, "rightFront");
        RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DcMotor LeftBack = hardwareMap.get(DcMotor.class, "leftBack");
        LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        DcMotor RightBack = hardwareMap.get(DcMotor.class, "rightBack");
        RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        DcMotor IntakeMotor = hardwareMap.get(DcMotor.class, "intakeMotorPort");
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        CRServo axonServo = hardwareMap.get(CRServo.class, "axonServo");

        Input input = new Input(gamepad1, gamepad2);
        Intake intake = new Intake(IntakeMotor, input);

        OGpinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD); // fuck u this single line caused me so much pain
        OGpinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);

        pinpointlarp pinpoint = new pinpointlarp(OGpinpoint, telemetry);
        Movement movement = new Movement(LeftFront, LeftBack, RightFront, RightBack, input, pinpoint);

        limelight.pipelineSwitch(0);
        limelight.start();

        //pinpoint.resetPositionandIMU();

        waitForStart();

        while (opModeIsActive()) {
            OGpinpoint.update();
            pinpoint.update();

            Pose2D currentPos = pinpoint.getPosition();
            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid() && result.getBotpose() != null) {
                Pose3D botPose3D = result.getBotpose();
                double targetX = botPose3D.getPosition().x - 0.5;
                double targetY = botPose3D.getPosition().y;
                double targetHead = botPose3D.getOrientation().getYaw(AngleUnit.DEGREES);

                Pose2D targetPose = new Pose2D(DistanceUnit.METER, targetX, targetY, AngleUnit.DEGREES, targetHead);

                movement.moveTo(targetPose);
                double xError = targetX - currentPos.getX(DistanceUnit.METER);
                double yError = targetY - currentPos.getY(DistanceUnit.METER);
                double distanceError = Math.sqrt(xError * xError + yError * yError);

                if (distanceError <= 0.05) {
                    IntakeMotor.setPower(-1.0);
                    axonServo.setPower(1.0);
                    telemetry.addData("status", "arrived at tag - running intake");
                } else {
                    IntakeMotor.setPower(0.0);
                    axonServo.setPower(0.0);
                    telemetry.addData("status", "driving to tag");
                }
                telemetry.addData("distance error", distanceError);
            } else {
                IntakeMotor.setPower(0.0);
                axonServo.setPower(0.0);
                telemetry.addData("status", "searching for apriltag");
            }

           // Pose2D error = pinpoint.getPosition();
            //pinpoint.resetPositionandIMU();
          //  movement.moveTo(new Pose2D(DistanceUnit.CM, 0.0, 10, AngleUnit.DEGREES, 0.0));
            //m,m,mjPose2D error = pinpoint.getPosition();
            telemetry.addData("X error", currentPos.getX(DistanceUnit.CM));
            telemetry.addData("Y error", currentPos.getY(DistanceUnit.CM));
            telemetry.addData("R error", currentPos.getHeading(AngleUnit.DEGREES));
         //   telemetry.addData("X raw", OGpinpoint.getEncoderX());
           // telemetry.addData("Y raw", OGpinpoint.getEncoderY());
            telemetry.update();
            //intake.run();

        }
    }
}


/*

\^^^----------______________
|##\   _  _  __  _ ___ \####^\__
|##/ ((_ /< //_ )) //  /####  __/
|#/_=_=_=_-_ _ _______/#####/
/----^^^^^^^^^
a
tootpass

 */

