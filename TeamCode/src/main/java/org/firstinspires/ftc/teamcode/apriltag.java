package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.vision.limelight3a.LLResult;
import org.firstinspires.ftc.vision.limelight3a.Limelight3A;

@Autonomous(name = "Limelight")
public class apriltag extends LinearOpMode {
    Limelight3A limelight;

    @Override
    public void runOpMode() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        limelight.pipelineSwitch(0);

        telemetry.addLine("ready");
        telemetry.update();
        waitForStart();

        limelight.start();

        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {
                telemetry.addLine("target found");
                telemetry.addData("x offset (tx)", result.getTx());
                telemetry.addData("y offset (ty)", result.getTy());
                telemetry.addData("area (ta)", result.getTa());
            } else {
                telemetry.addLine("searching for target");
                if (result == null || !result.isValid()) {
                    telemetry.addLine("target not found");
                }
            }

            telemetry.update();
        }

        limelight.stop();
    }
}
