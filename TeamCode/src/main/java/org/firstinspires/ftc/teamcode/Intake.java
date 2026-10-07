package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {
    private final double intakePower = 0.8;
    DcMotor intakeMotor;
    Input input;
    public Intake(DcMotor intakeMotor, Input input) {
        this.intakeMotor = intakeMotor;
        this.input = input;
    }
    public void run() {
        if (input.intake()) {
            intakeMotor.setPower(intakePower);
        }
    }
}
