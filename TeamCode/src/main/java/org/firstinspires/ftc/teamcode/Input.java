package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.Gamepad;
import java.util.*;
public class Input {
    private Gamepad gamepad1, gamepad2;
    public Input(Gamepad g1, Gamepad g2) {
        gamepad1 = g1;
        gamepad2 = g2;
    }
    public double moveX() {
        return gamepad1.left_stick_x;
    }
    public double moveY() {
        return -gamepad1.left_stick_y;
    }
    public double rotation() {
        return gamepad1.right_stick_x;
    }
    public boolean slow() {
        return gamepad1.b;
    }
    public boolean reset() {
        return gamepad1.y;
    }
}
