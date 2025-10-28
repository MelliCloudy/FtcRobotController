package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {
    boolean intakeOn = false;
    public void startIntake(DcMotor intakeMotor) {
        intakeMotor.setPower(0.1);
        intakeOn = true;
    }
    public void stopIntake(DcMotor intakeMotor) {
        intakeMotor.setPower(0.0);
        intakeOn = false;
    }
    public void toggleIntake(DcMotor intakeMotor) {
        if (intakeOn) {
            stopIntake(intakeMotor);
        } else {
            startIntake(intakeMotor);
        }
    }
}
