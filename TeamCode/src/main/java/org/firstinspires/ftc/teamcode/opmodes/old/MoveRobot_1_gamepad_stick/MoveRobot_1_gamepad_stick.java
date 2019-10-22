package org.firstinspires.ftc.teamcode.opmodes.old.MoveRobot_1_gamepad_stick;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MoveRobot_1_gamepad_stick extends LinearOpMode
{

    @Override
    public void runOpMode() throws InterruptedException
    {
        //Declara motoarele
        DcMotor forMotor1 = hardwareMap.get(DcMotor.class, "motor1");
        DcMotor forMotor2 = hardwareMap.get(DcMotor.class, "motor2");

        while (true)
        {
            //Seteaza curentul la 0 cand nu face nimic
            forMotor1.setPower(0);
            forMotor2.setPower(0);

            //Pt miscarea fata-spate
            forMotor1.setPower(-gamepad1.left_stick_y);
            forMotor2.setPower(gamepad1.left_stick_y);

            if (gamepad1.left_stick_x != 0)
            {
                //Pt miscarea pe diagonala pe stanga
                if (gamepad1.left_stick_x < 0 )
                {
                    forMotor1.setPower(gamepad1.left_stick_x);
                    forMotor2.setPower(gamepad1.left_stick_x);
                }

                //Pt miscarea pe diagonala pe dreapta
                if (gamepad1.left_stick_x > 0 )
                {
                    forMotor1.setPower(gamepad1.left_stick_x);
                    forMotor2.setPower(gamepad1.left_stick_x);
                }
            }




        }




    }
}