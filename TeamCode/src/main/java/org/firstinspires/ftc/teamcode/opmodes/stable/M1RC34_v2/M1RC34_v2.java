//                __  __ _ ____   ____ _____ _  _
//               |  \/  / |  _ \ / ___|___ /| || |
//               | |\/| | | |_) | |     |_ \| || |_
//               | |  | | |  _ <| |___ ___) |__   _|
//               |_|  |_|_|_| \_\\____|____/   |_|
//                        de echipa Brobots (RO-142)
//                         versiunea v2.0, 1 gamepad
//                                           testing
//
//Repo: https://www.github.com/Andy3153/FTCLaho_2019
//
//Configuri in Driver Station:
//
//Expansion Hub Portal 1
//  Expansion Hub 1
//    Motors
//      0 - Unspecified Motor - H1Motor0_Drive
//      1 - Unspecified Motor - H1Motor1_Drive
//
//  Expansion Hub 2
//    Motors
//      0 - Unspecified Motor - H2Motor0_ArmString
//    Servos
//      0 - Servo - H2Servo0_ArmBase
//      1 - Servo - H2Servo1_ClawL
//      2 - Servo - H2Servo2_ClawR
//
//

package org.firstinspires.ftc.teamcode.opmodes.stable.M1RC34_v2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Stable", group="Concept")
public class M1RC34_v2 extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        float clawInitPos=0.5f, armInitPos=0.39499992f;

        //region Declara variabilele actualizate numai odata
        float
                //Pt. pozitia ghearei
                clawPosition = clawInitPos,

                //Pt. pozitia bratului
                armPosition = armInitPos

//              //Pt. jumatate din putere
//              halfPwrMD_x = driveSpeed_x / 1.5f,
//              halfPwrMD_y = driveSpeed_y / 1.5f,
//              halfPwrMA_x = gamepad1.right_stick_x / 1.5f,
//              halfPwrMA_y = gamepad1.right_stick_y / 1.5f,
                        ;
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
            //region Declara variabilele actualizate constant
            float
                    //Pt. miscarea robotului
                    driveSpeed_x = gamepad1.left_stick_x,
                    driveSpeed_y = gamepad1.left_stick_y
                            ;
            //endregion

            //region Seteaza curentul la 0 la motoare
            //Pt. roti
            H1Motor0_Drive.setPower(0);
            H1Motor1_Drive.setPower(0);

            //Pt. brat
            H2Motor0_ArmString.setPower(0);
            //endregion

//            //region Butoanele pt. miscat mai incet
//              if (gamepad1.left_trigger > 0)
//              {
//                  driveSpeed_x = halfPwrMD_x;
//                  driveSpeed_y = halfPwrMD_y;
//              }
//
//              if (gamepad1.right_trigger > 0)
//              {
//                  gamepad1.right_stick_x = halfPwrMA_x;
//                  gamepad1.right_stick_y = halfPwrMA_y;
//              }
//            //endregion

            //region Miscarea robotului
            //Miscarea fata-spate
            H1Motor0_Drive.setPower(-driveSpeed_y);
            H1Motor1_Drive.setPower(driveSpeed_y);

            //Miscarea stanga-dreapta
            if (driveSpeed_x != 0)
            {
                H1Motor0_Drive.setPower(driveSpeed_x);
                H1Motor1_Drive.setPower(driveSpeed_x);
            }

            //endregion

            //region Miscarea bratului
            //region Pt. ata
            if(gamepad1.dpad_right)
            {
                H2Motor0_ArmString.setPower(1);
            }
            else
            if(gamepad1.dpad_left)
            {
                H2Motor0_ArmString.setPower(-1);
            }
            else H2Motor0_ArmString.setPower(0);
            //endregion

            //region Pt. brat
            if (gamepad1.dpad_up)
            {
                armPosition = armPosition + 0.001f;

                if(armPosition > 1)
                {
                    armPosition = 1;
                }
            }
            else
            if (gamepad1.dpad_down)
            {
                armPosition = armPosition - 0.001f;

                if(armPosition < 0)
                {
                    armPosition = 0;
                }
            }
            else armPosition += 0;

            H2Servo0_ArmBase.setPosition(armPosition);
            //endregion

            //region Pt. gheara
            if (gamepad1.left_bumper)
            {
                clawPosition = clawPosition + 0.01f;

                if(clawPosition > 1)
                {
                    clawPosition = 1;
                }
            }
            else
            if (gamepad1.right_bumper)
            {
                clawPosition = clawPosition - 0.01f;

                if(clawPosition < 0)
                {
                    clawPosition = 0;
                }
            }
            else clawPosition += 0;

            H2Servo1_ClawL.setPosition(clawPosition);
            H2Servo2_ClawR.setPosition(1 - clawPosition);
            //endregion
            //endregion

            //region Butoane de oprire
            //Oprirea miscarii robotului
            if(gamepad1.y)
            {
                H1Motor0_Drive.setPower(0);
                H1Motor1_Drive.setPower(0);
            }

            //Oprirea miscarii bratului
            if(gamepad1.b)
            {
                //Pt. ata
                H2Motor0_ArmString.setPower(0);

                //Pt. brat
                armPosition = armInitPos;

                //Pt. gheara
                clawPosition = clawInitPos;
            }

            //Start / stop pentru tot codul
            if(gamepad1.back)
            {
                stop();
            }

            if(gamepad1.start)
            {
                start();
            }
            //endregion

            //region Telemetrie
            //Viteza robotului
            telemetry.addData("Viteza robotului", 10*(1 - driveSpeed_x + driveSpeed_y));

            //Pozitia bratului
            telemetry.addData("Pozitia bratului", 1+armPosition);

            //Pozitia ghearei
            telemetry.addData("Pozitia ghearei", clawPosition);

            //Update
            telemetry.update();
            //endregion
        }
    }
}