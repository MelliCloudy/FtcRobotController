package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;

// temp stuff so i can push

public class Intake {
    boolean intakeOn = false;
    DcMotor intakeMotor;

    public Intake(DcMotor motor) {
        intakeMotor = motor;
    }
    public void startIntake() {
        intakeMotor.setPower(0.1);
        intakeOn = true;
    }
    public void stopIntake() {
        intakeMotor.setPower(0.0);
        intakeOn = false;
    }
    public void toggleIntake() {
        if (intakeOn) {
            stopIntake();
        } else {
            startIntake();
        }
    }
}
