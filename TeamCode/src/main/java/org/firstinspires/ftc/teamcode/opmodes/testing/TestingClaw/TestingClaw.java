package org.firstinspires.ftc.teamcode.opmodes.testing.TestingClaw;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TestingClaw extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        //region Declara motoarele
        CRServo Servo0_ClawL = hardwareMap.get(CRServo.class, "Servo0_ClawL");
        CRServo Servo1_ClawR = hardwareMap.get(CRServo.class, "Servo1_ClawR");
        //endregion

        while (true)
        {
            //region MISCAREA BRATULUI
            Servo0_ClawL.setPower(0);

            //endregion
        }
    }

}