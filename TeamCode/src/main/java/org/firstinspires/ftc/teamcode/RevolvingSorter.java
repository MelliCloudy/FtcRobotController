/*
package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;


// temp stuff so i can push I

public class RevolvingSorter {

    DcMotor RevolverMotor;
    Intake intake;
    // Shooter shooter;

    public RevolvingSorter(/*Intake newIntake,  Shooter newShooter, DcMotor Revolver) {
        // intake = newIntake;
        // shooter = newShooter;
        RevolverMotor = Revolver;
        RevolverMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RevolverMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // RevolverMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // RevolverMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    final int shootAngles[] = {0, 120, 240};
    final int intakeAngles[] = {180, 300, 60};

    String Mode = "INTAKE"; // INTAKE and SHOOT
    // boolean SlotsFilled[] = new boolean[]{false, false, false};

    DcMotor RevolverMotor;
    Intake intake;
    // Shooter shooter;

    public RevolvingSorter(Intake newIntake,  Shooter newShooter, DcMotor Revolver) {
        intake = newIntake;
        // shooter = newShooter;
        RevolverMotor = Revolver;
        RevolverMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RevolverMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void shootSlot(int slot) { // slot = 0, 1, 2
        RevolverMotor.setPower(1);
        RevolverMotor.setTargetPosition(shootAngles[slot]);
        while (RevolverMotor.isBusy()) {
            //idk what goes here for now
        }
        // shooter.shoot();
    }

    public void intakeSlot(int slot) { // slot = 0, 1, 2;
        RevolverMotor.setPower(1);
        RevolverMotor.setTargetPosition(intakeAngles[slot]);
        while (RevolverMotor.isBusy()) {
            //idk what goes here for now
        }
        intake.startIntake();
    }
    public void rotate(double direction) {
        RevolverMotor.setPower(direction * 0.1);
    }
}

*/
