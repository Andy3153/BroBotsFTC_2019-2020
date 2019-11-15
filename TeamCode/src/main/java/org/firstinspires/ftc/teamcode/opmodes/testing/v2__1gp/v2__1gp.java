package org.firstinspires.ftc.teamcode.opmodes.testing.v2__1gp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class v2__1gp extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {

        //region Declara variabilele
          float halfPwrMD_x = gamepad1.left_stick_x / 1.5f,
                halfPwrMD_y = gamepad1.left_stick_y / 1.5f,
                halfPwrMA_x = gamepad1.right_stick_x / 1.5f,
                halfPwrMA_y = gamepad1.right_stick_y / 1.5f,
                clawPosition = 0.5f,
                armPosition = 0.5f;
        //endregion

        //region Declara motoarele
        //Motoare normale
          //Pt. conducerea robotului
          DcMotor H1Motor0_Drive  = hardwareMap.get(DcMotor.class, "H1Motor0_Drive ");
          DcMotor H1Motor1_Drive  = hardwareMap.get(DcMotor.class, "H1Motor1_Drive ");

          //Pt. ata
          DcMotor H2Motor0_ArmString = hardwareMap.get(DcMotor.class, "H2Motor0_ArmString");

        //Servo-uri
          //Pt. baza
        Servo H2Servo0_ArmBase= hardwareMap.get(Servo.class, "H2Servo0_ArmBase");

        //Pt. gheara
          Servo H2Servo1_ClawL = hardwareMap.get(Servo.class, "H2Servo1_ClawL");
          Servo H2Servo2_ClawR = hardwareMap.get(Servo.class, "H2Servo2_ClawR");
        //endregion
        
        while (true)
        {
            
            //region Seteaza curentul la 0 la motoare
              //Pt. roti
              H1Motor0_Drive .setPower(0);
              H1Motor1_Drive .setPower(0);

              //Pt. brat
              H2Motor0_ArmString.setPower(0);
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
              H1Motor0_Drive .setPower(-gamepad1.left_stick_y);
              H1Motor1_Drive .setPower(gamepad1.left_stick_y);

              //Miscarea pe diagonala pe stanga
              if (gamepad1.left_stick_x < 0)
              {
                  H1Motor0_Drive .setPower(gamepad1.left_stick_x);
                  H1Motor1_Drive .setPower(gamepad1.left_stick_x);
              }

              //Miscarea pe diagonala pe dreapta
              if (gamepad1.left_stick_x > 0)
              {
                  H1Motor0_Drive .setPower(gamepad1.left_stick_x);
                  H1Motor1_Drive .setPower(gamepad1.left_stick_x);
              }
            //endregion
            
            //region Miscarea bratului
              //Pt. ata
              H2Motor0_ArmString.setPower(gamepad1.right_stick_x);

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

              H2Servo1_ClawL.setPosition(clawPosition);
              H2Servo2_ClawR.setPosition(1 - clawPosition);
            
            
            //endregion
            
        }
    }

}