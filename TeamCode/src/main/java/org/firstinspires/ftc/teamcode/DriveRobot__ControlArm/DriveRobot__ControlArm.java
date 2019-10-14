package org.firstinspires.ftc.teamcode.DriveRobot__ControlArm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class DriveRobot__ControlArm extends LinearOpMode
{

    @Override
    public void runOpMode() throws InterruptedException
    {

        //Declara motoarele
        //Pt. conducerea robotului
        DcMotor Motor0_Drive = hardwareMap.get(DcMotor.class, "Motor0_Drive");
        DcMotor Motor1_Drive = hardwareMap.get(DcMotor.class, "Motor1_Drive");

        //Pt. brat
        DcMotor Motor2_ArmBase = hardwareMap.get(DcMotor.class, "Motor2_ArmBase");
        DcMotor Motor3_ArmElbow = hardwareMap.get(DcMotor.class, "Motor3_ArmElbow");

        while (true) {

            // /////////// MISCAREA ROBOTULUI ///////////

            //Seteaza curentul la 0 cand nu face nimic
            Motor0_Drive.setPower(0);
            Motor1_Drive.setPower(0);

            //Pt miscarea fata-spate
            Motor0_Drive.setPower(-gamepad1.left_stick_y);
            Motor1_Drive.setPower(gamepad1.left_stick_y);

            if (gamepad1.left_stick_x != 0)
            {
                //Pt miscarea pe diagonala pe stanga
                if (gamepad1.left_stick_x < 0) {
                    Motor0_Drive.setPower(gamepad1.left_stick_x);
                    Motor1_Drive.setPower(gamepad1.left_stick_x);
                }

                //Pt miscarea pe diagonala pe dreapta
                if (gamepad1.left_stick_x > 0)
                {
                    Motor0_Drive.setPower(gamepad1.left_stick_x);
                    Motor1_Drive.setPower(gamepad1.left_stick_x);
                }
            }

            // //////////////////////////////////////////



            // /////////// MISCAREA BRATULUI ////////////


            //Seteaza curentul la 0 cand nu face nimic
            Motor2_ArmBase.setPower(0);
            Motor3_ArmElbow.setPower(0);

            Motor2_ArmBase.setPower((gamepad1.right_stick_x)/3);
            //Motor3_ArmElbow.setPower(-(gamepad1.right_stick_y)/1.5);

            if (gamepad1.right_stick_y > 0)
            {
                Motor3_ArmElbow.setPower(-(gamepad1.right_stick_y)/3);
            }

            if (gamepad1.right_stick_y < 0)
            {
                Motor3_ArmElbow.setPower(-gamepad1.right_stick_y);
            }
            // //////////////////////////////////////////



        }
    }
}