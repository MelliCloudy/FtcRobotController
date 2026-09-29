package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import java.util.*;

public class apriltag extends LinearOpMode {

    public void runOpMode() {
        AprilTagProcessor aprilTag = new AprilTagProcessor.Builder().build();

        VisionPortal visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "webcam 1"))
                .addProcessor(aprilTag)
                .build();

        telemetry.addData("Status", "Initialized. Waiting for start...");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();

            for (AprilTagDetection detection : currentDetections) {
                if (detection.metadata != null) {
                    double tagId = detection.id;
                    double x = detection.ftcPose.x;
                    double y = detection.ftcPose.y;
                    double yaw = detection.ftcPose.yaw;

                    telemetry.addData("Target Tag ID", tagId);
                    telemetry.addData("Distance X (Inches)", x);
                    telemetry.addData("Distance Y (Inches)", y);
                    telemetry.addData("Yaw Angle", yaw);
                }
            }
            telemetry.update();
            sleep(20);
        }

        visionPortal.close();
    }
}
