package org.firstinspires.ftc.teamcode.opmodes.stable.Final_2gamepad;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Final_2gamepad extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {

        //region Declara variabilele
        float halfPwrMD_x, halfPwrMD_y, halfPwrMA_x, halfPwrMA_y;
        //endregion

        //region Declara motoarele
          //Pt. conducerea robotului
            DcMotor Motor0_Drive = hardwareMap.get(DcMotor.class, "Motor0_Drive");
            DcMotor Motor1_Drive = hardwareMap.get(DcMotor.class, "Motor1_Drive");

          //Pt. brat
            DcMotor Motor2_ArmBase = hardwareMap.get(DcMotor.class, "Motor2_ArmBase");

          //Pt.servo-uri
            CRServo Servo0_Arm = hardwareMap.get(CRServo.class, "Servo0_Arm");
            CRServo Servo1_Claw = hardwareMap.get(CRServo.class, "Servo1_Claw");
        //endregion

        while (true) {

            //region MISCAREA ROBOTULUI

            //Seteaza curentul la 0 cand nu face nimic
            Motor0_Drive.setPower(0);
            Motor1_Drive.setPower(0);

            //Defineste variabilele pt. jumatate din putere
            halfPwrMD_x = gamepad1.left_stick_x / 1.5f;
            halfPwrMD_y = gamepad1.left_stick_y / 1.5f;

            halfPwrMA_x = gamepad1.right_stick_x / 1.5f;
            halfPwrMA_y = gamepad1.right_stick_y / 1.5f;

            //Daca butoanele respective sunt tinute apasate, robotul sa se miste mai incet, cu o precizie mai mare
            if (gamepad1.left_trigger > 0)
            {
                gamepad1.left_stick_x = halfPwrMD_x;
                gamepad1.left_stick_y = halfPwrMD_y;
            }

            if (gamepad1.right_trigger > 0)
            {
                gamepad1.right_stick_x = halfPwrMA_x;
                gamepad1.right_stick_y = halfPwrMA_y;
            }

            //Miscarea fata-spate
            Motor0_Drive.setPower(-gamepad1.left_stick_y);
            Motor1_Drive.setPower(gamepad1.left_stick_y);

            if (gamepad1.left_stick_x != 0)
            {
                //Pt miscarea pe diagonala pe stanga
                if (gamepad1.left_stick_x < 0)
                {
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
            //endregion

            //region MISCAREA BRATULUI

            //Seteaza curentul la 0 cand nu face nimic
            Motor2_ArmBase.setPower(0);

            //Opereaza bratul
            Motor2_ArmBase.setPower((gamepad2.right_stick_x) / 3);

            if (gamepad2.left_bumper)
            {
                Servo1_Claw.setPower(-1);
            }
            else
            {
                if (gamepad2.right_bumper)
                {
                    Servo1_Claw.setPower(1);
                }
                else
                {
                    Servo1_Claw.setPower(0);
                }
            }

            //endregion
        }
    }

}









