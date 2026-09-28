package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class movemen {
    final int FLFrontDir = -1;
    final int FRFrontDir = 1;
    final int BLFrontDir = 1;
    final int BRFrontDir = 1;
    final double sprintMoveMult = 1.2;
    final double sprintTurnMult = 1.2;
    final double brakeMoveMult = 0.7;
    final double brakeTurnMult = 0.7;
    DcMotor LeftFront, LeftBack, RightFront, RightBack;
    //Input input;
    public movemen(DcMotor LF, DcMotor LB, DcMotor RF, DcMotor RB) {
        LeftFront = LF;
        LeftBack = LB;
        RightFront = RF;
        RightBack = RB;
        //input = in;
    }
    private void move(double x, double y, double rot) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rot), 1);
        double frontLeftPower = (y + x + rot) / denominator;
        double backLeftPower = (y - x + rot) / denominator;
        double frontRightPower = (y - x - rot) / denominator;
        double backRightPower = (y + x - rot) / denominator;

        LeftFront.setPower(FLFrontDir * frontLeftPower);
        LeftBack.setPower(BLFrontDir * backLeftPower);
        RightFront.setPower(FRFrontDir * frontRightPower);
        RightBack.setPower(BRFrontDir * backRightPower);
    }
    public void run() {

    }
}
