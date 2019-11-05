package org.firstinspires.ftc.teamcode.opmodes.testing.TestingEverything;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TestingEverything extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        //region Declara variabilele
        float servoPosition = 0.5f;
        //endregion
        
        //region Declara motoarele
        //Hub 1
          //Motoare  normale
          DcMotor Motor0_H1 = hardwareMap.get(DcMotor.class, "Motor0_H1");
          DcMotor Motor1_H1 = hardwareMap.get(DcMotor.class, "Motor1_H1");
          DcMotor Motor2_H1 = hardwareMap.get(DcMotor.class, "Motor2_H1");
          DcMotor Motor3_H1 = hardwareMap.get(DcMotor.class, "Motor3_H1");

          //Servo-uri
          Servo Servo0_H1 = hardwareMap.get(Servo.class, "Servo0_H1");
          Servo Servo1_H1 = hardwareMap.get(Servo.class, "Servo1_H1");
          Servo Servo2_H1 = hardwareMap.get(Servo.class, "Servo2_H1");
          Servo Servo3_H1 = hardwareMap.get(Servo.class, "Servo3_H1");
          Servo Servo4_H1 = hardwareMap.get(Servo.class, "Servo4_H1");
          Servo Servo5_H1 = hardwareMap.get(Servo.class, "Servo5_H1");

        //Hub 2
          //Motoare  normale
          DcMotor Motor0_H2 = hardwareMap.get(DcMotor.class, "Motor0_H2");
          DcMotor Motor1_H2 = hardwareMap.get(DcMotor.class, "Motor1_H2");
          DcMotor Motor2_H2 = hardwareMap.get(DcMotor.class, "Motor2_H2");
          DcMotor Motor3_H2 = hardwareMap.get(DcMotor.class, "Motor3_H2");
        
          //Servo-uri
          Servo Servo0_H2 = hardwareMap.get(Servo.class, "Servo0_H2");
          Servo Servo1_H2 = hardwareMap.get(Servo.class, "Servo1_H2");
          Servo Servo2_H2 = hardwareMap.get(Servo.class, "Servo2_H2");
          Servo Servo3_H2 = hardwareMap.get(Servo.class, "Servo3_H2");
          Servo Servo4_H2 = hardwareMap.get(Servo.class, "Servo4_H2");
          Servo Servo5_H2 = hardwareMap.get(Servo.class, "Servo5_H2");

        //endregion

        while (true)
        {
            //region Seteaza curentul la 0 la motoare
            //Hub 1
              //Motoare normale
              Motor0_H1.setPower(0);
              Motor1_H1.setPower(0);
              Motor2_H1.setPower(0);
              Motor3_H1.setPower(0);
              
            //Hub 2
              //Motoare normale
              Motor0_H2.setPower(0);
              Motor1_H2.setPower(0);
              Motor2_H2.setPower(0);
              Motor3_H2.setPower(0);
            //endregion

            //region Testeaza motoarele
              //Hub 1
              Motor0_H1.setPower(gamepad1.left_stick_x);
              Motor1_H1.setPower(gamepad1.left_stick_x);
              Motor2_H1.setPower(gamepad1.left_stick_x);
              Motor3_H1.setPower(gamepad1.left_stick_x);

              //Hub 2
              Motor0_H1.setPower(gamepad1.left_stick_x);
              Motor1_H1.setPower(gamepad1.left_stick_x);
              Motor2_H1.setPower(gamepad1.left_stick_x);
              Motor3_H1.setPower(gamepad1.left_stick_x);
            //endregion

            //region Testeaza servourile
            //Prelucreaza variabilele
            if (gamepad1.left_bumper)
            {
                servoPosition = servoPosition + 0.01f;

                if(servoPosition>1)
                {
                    servoPosition=1;
                }
            }
            else
            if (gamepad1.right_bumper)
            {
                servoPosition = servoPosition - 0.01f;

                if(servoPosition<0)
                {
                    servoPosition=0;
                }
            }
            else
                servoPosition+=0;

            //Hub 1
            Servo0_H1.setPosition(servoPosition);
            Servo1_H1.setPosition(servoPosition);
            Servo2_H1.setPosition(servoPosition);
            Servo3_H1.setPosition(servoPosition);
            Servo4_H1.setPosition(servoPosition);
            Servo5_H1.setPosition(servoPosition);

            //Hub 2
            Servo0_H2.setPosition(servoPosition);
            Servo1_H2.setPosition(servoPosition);
            Servo2_H2.setPosition(servoPosition);
            Servo3_H2.setPosition(servoPosition);
            Servo4_H2.setPosition(servoPosition);
            Servo5_H2.setPosition(servoPosition);
            //endregion
        }

    }


}