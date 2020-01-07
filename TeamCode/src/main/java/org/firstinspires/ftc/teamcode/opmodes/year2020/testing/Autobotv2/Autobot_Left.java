package org.firstinspires.ftc.teamcode.opmodes.year2020.testing.Autobotv2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

import java.util.ArrayList;
import java.util.List;

@Autonomous(name="AutobotLeft", group ="Concept")
public class Autobot_Left extends LinearOpMode
{

    DcMotor H1Motor1_Drive, H1Motor0_Drive, H2Motor0_ArmString;
    Servo H2Servo0_ArmBase, H2Servo1_ClawL, H2Servo2_ClawR;

    public void stopRobot()
    {
        goForward(0, 0);
    }

    public void goLeft(float power, int miliseconds)
    {
        H1Motor0_Drive.setPower(power);
        H1Motor1_Drive.setPower(power);
        sleep(miliseconds);
    }

    public void goRight(float power, int miliseconds)
    {
        H1Motor0_Drive.setPower(-power);
        H1Motor1_Drive.setPower(-power);
        sleep(miliseconds);
    }

    public void goForward(float power, int miliseconds)
    {
        H1Motor0_Drive.setPower(power);
        H1Motor1_Drive.setPower(-power);
        sleep(miliseconds);
    }

    public void goBackwards(float power, int miliseconds)
    {
        H1Motor0_Drive.setPower(-power);
        H1Motor1_Drive.setPower(power);
        sleep(miliseconds);
    }

    public void catchCube()
    {
        H2Servo1_ClawL.setPosition(0);
        H2Servo2_ClawR.setPosition(1);
        H2Servo0_ArmBase.setPosition(0.432f);
        sleep(1650);
        H2Servo1_ClawL.setPosition(0.47f);
        H2Servo2_ClawR.setPosition(0.53f);
        sleep(300);
        H2Servo0_ArmBase.setPosition(0.5f);
    }

    @Override public void runOpMode()
    {
        H1Motor0_Drive  = hardwareMap.get(DcMotor.class, "H1Motor0_Drive ");
        H1Motor1_Drive= hardwareMap.get(DcMotor.class, "H1Motor1_Drive ");

        //region Declara variabilele actualizate numai odata
        float
                //Pt. pozitia ghearei
                clawInitPos=0,     clawMaxPos=0.5899999f, clawMinPos=0,
                clawPos = clawInitPos,

                //Pt. pozitia bratului
                armInitPos=0.4299995f, armMaxPos=0.547998f,   armMinPos=0.43f,
                armPos = armInitPos,

                //Pt. miscarea robotului
                driveSpeed_x = 0,
                driveSpeed_y = 0
        ;
        //endregion

        //region Declara motoarele
        //Motoare normale
        //Pt. conducerea robotului

        //Pt. ata
        H2Motor0_ArmString = hardwareMap.get(DcMotor.class, "H2Motor0_ArmString");

        //Servo-uri
        //Pt. baza
        H2Servo0_ArmBase= hardwareMap.get(Servo.class, "H2Servo0_ArmBase");

        //Pt. gheara
        H2Servo1_ClawL = hardwareMap.get(Servo.class, "H2Servo1_ClawL");
        H2Servo2_ClawR = hardwareMap.get(Servo.class, "H2Servo2_ClawR");
        //endregion

        //region Seteaza pozitia ghearei
        sleep(1000);
        clawPos = 0;
        //endregion
        H2Servo0_ArmBase.setPosition(armMinPos);

        /*while (opModeIsActive())
        {*/

            //region Miscarea robotului
            //Miscarea fata-spate
            H1Motor0_Drive.setPower(-driveSpeed_y);
            H1Motor1_Drive.setPower(driveSpeed_y);

            //Miscarea stanga-dreapta
            if (driveSpeed_x != 0)
            {
                H1Motor0_Drive.setPower(-driveSpeed_x);
                H1Motor1_Drive.setPower(-driveSpeed_x);
            }
            //endregion
            clawPos = clawInitPos;
            armPos = armMinPos;

        //region Miscarea bratului
            //region Pt. brat
            H2Servo0_ArmBase.setPosition(armPos);
            //endregion

            //region Pt. gheara
            H2Servo1_ClawL.setPosition(clawPos);
            H2Servo2_ClawR.setPosition(1 - clawPos);
            //endregion
            //endregion

            //region Automat cica
            //ACTUAL COMMANDS
            /*goLeft(1, 500);(
            goForward(1, 1000);
            goRight(1, 500);
            goBackwards(1, 1000);*/

            goLeft(1, 455);
            goForward(1, 1500);
            stopRobot();
            /*goForward(1, 1350);
            goRight(1, 520);
            goForward(1, 100);
            stopRobot();
            catchCube();*/

            /*goRight(1, 500);
            goForward(1, 400);
            goLeft(1, 500);
            goForward(1, 1000);
            goLeft(1, 500);
            goForward(0.25f, 2);
            goForward(0, 0);
            catchCube();
            goLeft(1, 500);
            goForward(1, 850);
            goLeft(1, 500);
            goForward(1, 2000);
            goForward(0, 0);
            */
            /*goLeft(1, 540);
            goForward(1, 750);
            goLeft(1, 270);
            goForward(1, 150);
            goLeft(1, 250);
            goForward(0.5f, 100);
            */
            //endregion

            //region Telemetrie
            //Viteza robotului
            telemetry.addData("Viteza robotului", 10*(1 - driveSpeed_x + driveSpeed_y));

            //Pozitia bratului
            telemetry.addData("Pozitia bratului", 1 + armPos);

            //Pozitia ghearei
            telemetry.addData("Pozitia ghearei", clawPos);

            //Update
            telemetry.update();
            //endregion
//            //region Seteaza curentul la 0 la motoare
//            //Pt. roti
//            H1Motor0_Drive.setPower(0);
//            H1Motor1_Drive.setPower(0);
//
//            //Pt. brat
//            H2Motor0_ArmString.setPower(0);
//            //endregion

//            //Activat servouri
//            H2Servo0_ArmBase.setPosition(0.4299995f);
//
//            H2Servo1_ClawL.setPosition(0);
//            H2Servo2_ClawR.setPosition(1 - 0);
//
//            //Activat motoare
//            H1Motor0_Drive.setPower(1);
//            H1Motor1_Drive.setPower(-1);
//            sleep(1000);
//
//            H1Motor0_Drive.setPower(-1);
//            H1Motor1_Drive.setPower(-1);
//            sleep(100);
//
//            H1Motor0_Drive.setPower(1);
//            H1Motor1_Drive.setPower(-1);
//            sleep(1000);
//
//            H1Motor0_Drive.setPower(1);
//            H1Motor1_Drive.setPower(1);
//            sleep(100);
//
//            H1Motor0_Drive.setPower(1);
//            H1Motor1_Drive.setPower(-1);

        }
    //}

}
