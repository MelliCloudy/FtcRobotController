package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;

// temp stuff so i can push I

public class Shooter {
    boolean shooterOn = false;
    DcMotor shooterMotor;

    public Shooter(DcMotor motor) {
        shooterMotor = motor;
    }

    public void startFlywheel() {
        shooterMotor.setPower(0.1);
        shooterOn = true;
    }

    public void stopFlywheel() {
        shooterMotor.setPower(0.0);
        shooterOn = false;
    }

    public void toggleFlywheel() {
        if (shooterOn) {
            stopFlywheel();
        } else {
            startFlywheel();
        }
    }
}