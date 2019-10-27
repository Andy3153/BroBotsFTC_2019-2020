package org.firstinspires.ftc.teamcode.opmodes.testing.v1_1__1gamepad;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class v1_1__1gamepad extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {

        //region Declara variabilele
          float halfPwrMD_x = gamepad1.left_stick_x / 1.5f,
                halfPwrMD_y = gamepad1.left_stick_y / 1.5f,
                halfPwrMA_x = gamepad1.right_stick_x / 1.5f,
                halfPwrMA_y = gamepad1.right_stick_y / 1.5f,
                clawPosition = 0.5f;
        //endregion

        //region Declara motoarele
        //Motoare normale
          //Pt. conducerea robotului
          DcMotor Motor0_Drive = hardwareMap.get(DcMotor.class, "Motor0_Drive");
          DcMotor Motor1_Drive = hardwareMap.get(DcMotor.class, "Motor1_Drive");

          //Pt. brat
          DcMotor Motor2_ArmBase = hardwareMap.get(DcMotor.class, "Motor2_ArmBase");
          DcMotor Motor3_ArmElbow = hardwareMap.get(DcMotor.class, "Motor3_ArmElbow");

        //Servo-uri
          //Pt. gheara
          Servo Servo0_ClawL = hardwareMap.get(Servo.class, "Servo0_ClawL");
          Servo Servo1_ClawR = hardwareMap.get(Servo.class, "Servo1_ClawR");
        //endregion



        while (true)
        {


            //region Seteaza curentul la 0 la motoare
              //Pt. roti
              Motor0_Drive.setPower(0);
              Motor1_Drive.setPower(0);

              //Pt. brat
              Motor2_ArmBase.setPower(0);
              Motor3_ArmElbow.setPower(0);
            //endregion
            
            //region Butoanele pt. miscat mai incet
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
            //endregion

            
            
            //region Miscarea robotului
              //Miscarea fata-spate
              Motor0_Drive.setPower(-gamepad1.left_stick_y);
              Motor1_Drive.setPower(gamepad1.left_stick_y);

              //Miscarea pe diagonala pe stanga
              if (gamepad1.left_stick_x < 0)
              {
                  Motor0_Drive.setPower(gamepad1.left_stick_x);
                  Motor1_Drive.setPower(gamepad1.left_stick_x);
              }

              //Miscarea pe diagonala pe dreapta
              if (gamepad1.left_stick_x > 0)
              {
                  Motor0_Drive.setPower(gamepad1.left_stick_x);
                  Motor1_Drive.setPower(gamepad1.left_stick_x);
              }
            //endregion

            
            //region Miscarea bratului
              //Pt. baza
              Motor2_ArmBase.setPower((gamepad1.right_stick_x) / 4);


              //Pt. cot
              if (gamepad1.right_stick_y > 0)
              {
                  Motor3_ArmElbow.setPower((gamepad1.right_stick_y) / 4);
              }

              if (gamepad1.right_stick_y < 0)
              {
                  Motor3_ArmElbow.setPower(gamepad1.right_stick_y * 3);
              }


              //Pt. gheara
              if (gamepad1.left_bumper)
              {
                  clawPosition = clawPosition + 0.01f;

                  if(clawPosition>1)
                  {
                      clawPosition=1;
                  }
              }
              else
              if (gamepad1.right_bumper)
              {
                  clawPosition = clawPosition - 0.01f;

                  if(clawPosition<0)
                  {
                      clawPosition=0;
                  }
              }
              else
                  clawPosition+=0;

              Servo0_ClawL.setPosition(clawPosition);
              Servo1_ClawR.setPosition(1 - clawPosition);
            
            
            //endregion


        }
    }

}