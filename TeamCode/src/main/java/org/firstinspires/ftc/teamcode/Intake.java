package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {
    private final double intakePower = 0.8;
    DcMotor intakeMotor;
    Input input;

    CRServo intakeservo;

    public Intake(CRServo intakeservo, Input input) {
        this.intakeservo = intakeservo;
        this.input = input;
    }
    public void run() {
        if (input.intake()) {
            intakeservo.setPower(1.0);
        } else {
            intakeservo.setPower(0.0);
        }
    }
}
