package org.firstinspires.ftc.teamcode.MoveRobot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MoveRobot extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        //Declara motoarele
        DcMotor forMotor1 = hardwareMap.get(DcMotor.class, "motor1");
        DcMotor forMotor2 = hardwareMap.get(DcMotor.class, "motor2");

        while (true) {
            // Baga viteza la motorul 1
            forMotor1.setPower(0);
            forMotor1.setPower(gamepad2.left_stick_y);

            // Baga viteza la motorul 2
            forMotor2.setPower(0);
            forMotor2.setPower(gamepad2.right_stick_y);


        }




    }
}