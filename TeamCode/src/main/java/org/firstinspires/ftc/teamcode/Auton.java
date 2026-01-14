package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auton", group = "test")

public class Auton extends LinearOpMode {
    final int FLFrontDir = -1;
    final int FRFrontDir = 1;
    final int BLFrontDir = 1;
    final int BRFrontDir = 1;
    // ticks per rot = 537.7, meters per rot = 0.32672563597
    //final double TPM = 1645.72332501;


    public void runOpMode() throws InterruptedException {
        DcMotor LeftFront = hardwareMap.get(DcMotor.class, "leftFront");
        LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor RightFront = hardwareMap.get(DcMotor.class, "rightFront");
        RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor LeftBack = hardwareMap.get(DcMotor.class, "leftBack");
        LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor RightBack = hardwareMap.get(DcMotor.class, "rightBack");
        RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor OdomHori = hardwareMap.get(DcMotor.class, "HorizontalOdom");
        DcMotor OdomVert = hardwareMap.get(DcMotor.class, "VerticalOdom");
        OdomHori.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        OdomHori.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        OdomVert.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        OdomVert.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        /*
        DcMotor RevolverMotor = hardwareMap.get(DcMotor.class, " !!!!!  CHANGE ASAP  !!!!  whatever the revolver motor name will be");
        RevolverMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RevolverMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor ShooterMotor = hardwareMap.get(DcMotor.class, " !!!!!  CHANGE ASAP  !!!!  whatever the shooter motor name will be");
        ShooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ShooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        */

        waitForStart();
        final double TPCM = 0;
        final double WheelDST_Side = 40.5;
        final double WheelDST_Back = 33.5;
        //final double cI = 0;
        final double cP = 0;
        final double cD = 0;
        float ldst, rdst;

        while (opModeIsActive()) {

        }
    }
}
