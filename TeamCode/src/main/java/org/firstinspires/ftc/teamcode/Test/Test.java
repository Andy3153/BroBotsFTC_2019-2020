package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.nio.channels.DatagramChannel;
@TeleOp
public class Test extends LinearOpMode {
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





// Ex. 1
        /*
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

         */
// Ex. 2

    /*
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
     */


// Ex. 3

    /*
        //Declara motoarele
        DcMotor forMotor1 = hardwareMap.get(DcMotor.class, "motor1");
        DcMotor forMotor2 = hardwareMap.get(DcMotor.class, "motor2");
        DcMotor forMotor3 = hardwareMap.get(DcMotor.class, "motor3");

        while (true) {
            // Seteaza viteza 0 la inceput
            forMotor1.setPower(0);
            forMotor2.setPower(0);
            forMotor3.setPower(0);

            // Baga viteza la motorul 1
            forMotor1.setPower(gamepad1.left_stick_y);

            // Baga viteza la motorul 2
            if (forMotor1.getPower()!=0) {
                forMotor2.setPower(gamepad1.right_stick_y);
            }

            // Baga viteza la motorul 3
            if (forMotor2.getPower() != 0) {
                if (gamepad1.dpad_up) {
                    forMotor3.setPower(1);
                }

                if (gamepad1.dpad_down) {
                    forMotor3.setPower(-1);
                }
            }
     */



// Ex. 4

     /*

      */



// Mentiuni onorabile
//asta ii bun cica pt 1 motor
            /*
            if (gamepad1.) {
                forMotor0.setPower(1);
            }
            forMotor0.setPower(0);
            if (gamepad1.left_stick_y) {
                forMotor0.setPower(-1);
            }
            */



// Pt. ex. 4

     /*
                 float v;
            v = 0;

            if (gamepad1.dpad_up) {
                forMotor1.setPower(v += 0.1);
            }

            if (gamepad1.dpad_down) {
                forMotor1.setPower(v -= 0.1);
            }
      */