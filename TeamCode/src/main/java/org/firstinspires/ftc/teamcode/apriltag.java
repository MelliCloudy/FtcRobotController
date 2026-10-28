package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.vision.limelight3a.LLResult;
import org.firstinspires.ftc.vision.limelight3a.Limelight3A;

@Autonomous(name = "apriltagToPoint", group = "auton")
public class AprilTagToPoint extends LinearOpMode {
    Limelight3A limelight;
    GoBildaPinpointDriver pinpoint;

    @Override
    public void runOpMode() throws InterruptedException {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        DcMotor leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        DcMotor rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        DcMotor leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        DcMotor rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Input input = new Input(gamepad1, gamepad2);
        Movement movement = new Movement(leftFront, leftBack, rightFront, rightBack, input, pinpoint);

        limelight.pipelineSwitch(0);
        limelight.start();

        telemetry.addData("Status", "initialized");
        telemetry.update();

        waitForStart();
        pinpoint.resetPosAndIMU();

        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {
                Pose3D botPose3D = result.getBotpose();

                if (botPose3D != null) {
                    double tagX = botPose3D.getPosition().x * 39.3701;
                    double tagY = botPose3D.getPosition().y * 39.3701;
                    double tagHeading = botPose3D.getOrientation().getYaw(AngleUnit.DEGREES);

                    Pose2D targetPose = new Pose2D(DistanceUnit.INCH, tagX, tagY, AngleUnit.DEGREES, tagHeading);

                    movement.moveTo(targetPose);

                    telemetry.addData("Tag X", tagX);
                    telemetry.addData("Tag Y", tagY);
                    telemetry.addData("Tag Heading", tagHeading);
                }
            } else {
                telemetry.addData("Status", "searching for apriltag");
            }

            telemetry.update();
        }

        limelight.stop();
    }
}