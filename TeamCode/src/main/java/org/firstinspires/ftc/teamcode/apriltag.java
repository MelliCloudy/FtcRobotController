/*
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@Autonomous(name = "apriltagToPoint", group = "auton")
public class apriltag extends LinearOpMode {
    Limelight3A limelight;
    GoBildaPinpointDriver pinpt;
    pinpointlarp pinpoint;

    @Override
    public void runOpMode() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        pinpt = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        DcMotor leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        DcMotor rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        DcMotor leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        DcMotor rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pinpt.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpt.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);

        pinpoint = new pinpointlarp(pinpt, telemetry);
        Input input = new Input(gamepad1, gamepad2);
        Movement movement = new Movement(leftFront, leftBack, rightFront, rightBack, input, pinpoint);

        limelight.pipelineSwitch(0);
        limelight.start();

        telemetry.addData("status", "initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            pinpt.update();
            pinpoint.update();
            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {
                Pose3D botPose3D = result.getBotpose();

                if (botPose3D != null) {
                    double tagX = botPose3D.getPosition().x;
                    double tagY = botPose3D.getPosition().y;
                    double tagHead = botPose3D.getOrientation().getYaw(AngleUnit.DEGREES);

                    double targetX = tagX - 0.5;
                    double targetY = tagY;
                    double targetHead = tagHead;

                    Pose2D targetPose = new Pose2D(DistanceUnit.METER, targetX, targetY, AngleUnit.DEGREES, targetHead);

                    movement.moveTo(targetPose);

                    telemetry.addData("tag x", tagX);
                    telemetry.addData("tag y", tagY);
                    telemetry.addData("tag heading", tagHead);
                }
            } else {
                telemetry.addData("status", "searching for apriltag");
            }

            telemetry.update();
        }

        limelight.stop();
    }
}
 */