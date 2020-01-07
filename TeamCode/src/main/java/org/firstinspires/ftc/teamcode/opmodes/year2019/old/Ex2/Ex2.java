package org.firstinspires.ftc.teamcode.opmodes.year2019.old.Ex2;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//@TeleOp
public class Ex2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        //Declara motoarele
        DcMotor forMotor1 = hardwareMap.get(DcMotor.class, "motor1");
        DcMotor forMotor2 = hardwareMap.get(DcMotor.class, "motor2");
        DcMotor forMotor3 = hardwareMap.get(DcMotor.class, "motor3");

        while (true) {
            // Baga viteza la motorul 1
            forMotor1.setPower(0);
            forMotor1.setPower(gamepad1.left_stick_y);

            // Baga viteza la motorul 2
            forMotor2.setPower(0);
            forMotor2.setPower(gamepad1.right_stick_y);

            // Baga viteza la motorul 3
            forMotor3.setPower(0);
            forMotor3.setPower(gamepad1.left_stick_y + gamepad1.right_stick_y);
        }

    }
}