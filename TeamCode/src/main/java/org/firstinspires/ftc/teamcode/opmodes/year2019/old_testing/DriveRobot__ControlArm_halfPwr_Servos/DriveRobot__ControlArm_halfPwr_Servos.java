package org.firstinspires.ftc.teamcode.opmodes.year2019.old_testing.DriveRobot__ControlArm_halfPwr_Servos;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//@TeleOp
class DriveRobot__ControlArm_halfPwr_Servos extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        //region Declara servo-urile
        CRServo Servo0_Arm = hardwareMap.get(CRServo.class, "Servo0_Arm");
        CRServo Servo1_Claw = hardwareMap.get(CRServo.class, "Servo1_Claw");

        double clawServoSpeed = 0.0;
        boolean rightBumperPressed=false, leftBumperPressed=false;
        //endregion

        float halfPwrMD_x, halfPwrMD_y, halfPwrMA_x, halfPwrMA_y, negativeArmSpeed=0, positiveArmSpeed=0;

        //region Declara motoarele
          //Pt. conducerea robotului
            DcMotor Motor0_Drive = hardwareMap.get(DcMotor.class, "Motor0_Drive");
            DcMotor Motor1_Drive = hardwareMap.get(DcMotor.class, "Motor1_Drive");

          //Pt. brat
            DcMotor Motor2_ArmBase = hardwareMap.get(DcMotor.class, "Motor2_ArmBase");
        //endregion

        while (true) {
            //region MISCAREA ROBOTULUI

            //region Seteaza curentul la 0 cand nu face nimic
            Motor0_Drive.setPower(0);
            Motor1_Drive.setPower(0);
            //endregion

            //region Creeaza niste variabile pt. jumatate din putere
            halfPwrMD_x = gamepad1.left_stick_x / 1.5f;
            halfPwrMD_y = gamepad1.left_stick_y / 1.5f;

            halfPwrMA_x = gamepad1.right_stick_x / 1.5f;
            halfPwrMA_y = gamepad1.right_stick_y / 1.5f;
            //endregion

            //region Daca butoanele respective sunt tinute apasate, robotul sa se miste mai incet, cu o precizie mai mare
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

            //region Miscarea fata-spate
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

            //endregion

            //region MISCAREA BRATULUI

            //region Initializeaza servo-urile
            if(!leftBumperPressed && !rightBumperPressed)
                Servo1_Claw.setPower(0);

            //endregion

            //region Gheara
            /*if(gamepad2.right_bumper) {
                if(!rightBumperPressed)
                {
                    rightBumperPressed=true;
                    clawServoSpeed=1;
                }
                else {
                    clawServoSpeed=0;
                    rightBumperPressed = false;
                }
            }

            if(gamepad2.left_bumper) {
                if(!leftBumperPressed)
                {
                    leftBumperPressed=true;
                    clawServoSpeed=-1;
                }
                else {
                    clawServoSpeed=0;
                    leftBumperPressed = false;
                }
            }*/
            /*else if(gamepad2.left_bumper)
                if(servoSpeed>-0.8f)
                    servoSpeed-=0.2;
                else
                    servoSpeed=0;*/
            float speed = gamepad2.right_stick_y;
            if(speed>0)
            {
                Servo0_Arm.setDirection(DcMotorSimple.Direction.REVERSE);
                Servo0_Arm.setPower(speed);
            }else if(speed<0)
            {
                Servo0_Arm.setDirection(DcMotorSimple.Direction.FORWARD);
                Servo0_Arm.setPower(-speed);
            }
            else
                Servo0_Arm.setPower(0);

            //Servo1_Claw.setPower(clawServoSpeed);
            //endregion

            //region Arm
            /*float speed = -gamepad2.right_stick_y;
            if(speed<0) {
                Servo0_Arm.setDirection(DcMotorSimple.Direction.REVERSE);
                Servo0_Arm.setPower(speed * 0.4f);
            }
            else
            {
                Servo0_Arm.setDirection(DcMotorSimple.Direction.FORWARD);
                Servo0_Arm.setPower(speed * 0.7f);
            }*/
                /*if(speed>x) {
                negativeArmSpeed = 0;
                positiveArmSpeed=speed/1.5f;
            }
            else if(speed<x)
            {
                positiveArmSpeed=0;
                negativeArmSpeed=speed/1.5f;
            }
            else
                negativeArmSpeed=positiveArmSpeed=0;*/
            //Servo0_Arm.setPower(negativeArmSpeed<0 ? negativeArmSpeed : positiveArmSpeed);

            //endregion


            //Seteaza curentul la 0 cand nu face nimic
            Motor2_ArmBase.setPower(0);
            //Motor3_ArmElbow.setPower(0);
            Motor2_ArmBase.setPower((gamepad2.right_stick_x) / 3);
            //Motor3_ArmElbow.setPower(-(gamepad1.right_stick_y)/1.5);

            /*if (gamepad1.right_stick_y > 0.3)
            {
                Motor3_ArmElbow.setPower(-(gamepad1.right_stick_y) / 3);
            }

            if (gamepad1.right_stick_y < 0)
            {
                Motor3_ArmElbow.setPower(-gamepad1.right_stick_y / 1.5);
            }*/
            //endregion
        }
    }

}









