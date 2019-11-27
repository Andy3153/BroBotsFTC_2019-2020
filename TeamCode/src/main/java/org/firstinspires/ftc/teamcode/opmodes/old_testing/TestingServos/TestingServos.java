package org.firstinspires.ftc.teamcode.opmodes.old_testing.TestingServos;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

//@TeleOp
class TestingServos extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {

        //region Declara servo-urile
        CRServo Servo0_Arm = hardwareMap.get(CRServo.class, "Servo0_Arm");
        CRServo Servo1_Claw = hardwareMap.get(CRServo.class, "Servo1_Claw");
        //endregion

        //region Seteaza curentul la 0 cand nu face nimic
        Servo0_Arm.setPower(0);
//        Servo1_Claw.setPower(0);
        //endregion

        while (true)
        {

            //region Foloseste servo-urile
            Servo0_Arm.setPower(gamepad2.right_stick_y);
            Servo1_Claw.setPower(gamepad2.right_stick_x); //Asta merge bine, e pentru gheara
            //endregion


        }



    }




}
