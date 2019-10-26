package org.firstinspires.ftc.teamcode.opmodes.testing.TestingClaw;

import android.util.Log;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TestingClaw extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {

        //region Declara variabilele
        float position=0.5f;
        //endregion

        //region Declara motoarele
        Servo Servo0_ClawL = hardwareMap.get(Servo.class, "Servo0_ClawL");
        Servo Servo1_ClawR = hardwareMap.get(Servo.class, "Servo1_ClawR");
        //endregion

        while (true)
        {
            //region MISCAREA BRATULUI
            if (gamepad1.left_bumper)
            {
                position = position + 0.01f;

                if(position>1)
                {
                    position=1;
                }
            }else
                if (gamepad1.right_bumper)
                {
                    position = position - 0.01f;

                  if(position<0)
                   {
                      position=0;
                   }
             }else
                 position+=0;

            Servo0_ClawL.setPosition(position);
            Servo1_ClawR.setPosition(1 - position);

//            System.out.println(position);
            Log.i("MyActivity", "MyClass.getView() — get item number " + position);

            //endregion

        }
    }

}