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

package org.firstinspires.ftc.teamcode.opmodes.year2020.old_stable.M1RC34_2020;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Stable", group="Stable")
@Disabled
public class M1RC34_v2_2020 extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {

        //region Declara variabilele actualizate numai odata
        float
                //Pt. pozitia ghearei
                clawInitPos=0.14f, clawMaxPos=0.5899999f, clawMinPos=0,
                clawPos = clawInitPos,

                //Pt. pozitia bratului
                armInitPos=0.4299995f, armMaxPos=0.547998f, armMinPos=0.4299995f,
                armPos = armInitPos,

                //Pt. pozitia tavii
                plateInitPos = 0, plateMaxPos=0.6f, plateMinPos=0,
                platePos = plateInitPos
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

        //Pt. tava
        Servo H2Servo3_PlateL = hardwareMap.get(Servo.class, "H2Servo3_PlateL");
        Servo H2Servo4_PlateR = hardwareMap.get(Servo.class, "H2Servo4_PlateR");
        //endregion

        //region Seteaza servourile la pozitia initiala
        H2Servo3_PlateL.setPosition(1 - plateInitPos);
        H2Servo4_PlateR.setPosition(plateInitPos);
        H2Servo1_ClawL.setPosition(clawInitPos);
        H2Servo2_ClawR.setPosition(1 - clawInitPos);
        H2Servo0_ArmBase.setPosition(armInitPos);
        //endregion

        telemetry.addData("PRESS START", clawPos);

        waitForStart();  //Waits for the match to start
        while (opModeIsActive() && !gamepad2.x)
        {
            //region Declara variabilele actualizate constant
            float
                    //Pt. miscarea robotului
                    driveSpeed_x,
                    driveSpeed_y
                            ;
            //endregion

            //region Controalele pentru robot
            //Axa X
            if(gamepad1.left_stick_x != 0)
            {
                driveSpeed_x = gamepad1.left_stick_x;
            }
            else if(gamepad2.left_stick_x != 0)
            {
                driveSpeed_x = gamepad2.left_stick_x;
            }
            else driveSpeed_x = 0;

            //Axa Y
            if(gamepad1.left_stick_y != 0)
            {
                driveSpeed_y = gamepad1.left_stick_y;
            }
            else if(gamepad2.left_stick_y != 0) {
                driveSpeed_y = gamepad2.left_stick_y;
            }
            else driveSpeed_y = 0;
            //endregion

            //region Seteaza curentul la 0 la motoare
            //Pt. roti
            H1Motor0_Drive.setPower(0);
            H1Motor1_Drive.setPower(0);

            //Pt. brat
            H2Motor0_ArmString.setPower(0);
            //endregion

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

            //region Miscarea bratului
            //region Pt. ata
            if(gamepad2.dpad_right)
            {
                H2Motor0_ArmString.setPower(1);
            }
            else
            if(gamepad2.dpad_left)
            {
                H2Motor0_ArmString.setPower(-1);
            }
            else H2Motor0_ArmString.setPower(0);
            //endregion

            //region Pt. brat
            if (gamepad2.dpad_up)
            {
                armPos = armPos>armMaxPos?armMaxPos:armPos+0.001f;
            }
            else if (gamepad2.dpad_down)
            {
                armPos = armPos<armMinPos?armMinPos:armPos-0.001f;
            }

            H2Servo0_ArmBase.setPosition(armPos);
            //endregion

            //region Pt. gheara
            if (gamepad2.left_bumper)
            {
                clawPos=clawPos>clawMaxPos?clawMaxPos:clawPos+0.01f;
            }
            else
            if (gamepad2.right_bumper)
            {
                clawPos = clawPos<clawMinPos?clawMinPos:clawPos-0.01f;
            }

            H2Servo1_ClawL.setPosition(clawPos);
            H2Servo2_ClawR.setPosition(1 - clawPos);
            //endregion

            //region Pt. tava
            if (gamepad2.right_stick_y > 0)
            {
                platePos = platePos > plateMaxPos ? plateMaxPos : platePos + 0.01f;
            }
            else
            if (gamepad2.right_stick_y < 0)
            {
                platePos = platePos < plateMinPos ? plateMinPos : platePos - 0.01f;
            }

            H2Servo3_PlateL.setPosition(1-platePos);
            H2Servo4_PlateR.setPosition(platePos);
            //endregion
            //endregion

            //region Resetarea pozitiilor initiale ale servourilor
            if(gamepad2.b)
            {
                //Pt. ata
                H2Motor0_ArmString.setPower(0);

                //Pt. brat
                armPos = armInitPos;

                //Pt. gheara
                clawPos = clawInitPos;

                //Pt. tava
                platePos = plateInitPos;
            }
            //endregion

            //region Telemetrie
            //Viteza robotului
            telemetry.addData("Viteza robotului", 10*(1 - driveSpeed_x + driveSpeed_y));

            //Pozitia bratului
            telemetry.addData("Pozitia bratului", 1+armPos);

            //Pozitia ghearei
            telemetry.addData("Pozitia ghearei", clawPos);

            //Pozitia tavii
            telemetry.addData("Pozitia tavii", platePos);

            //Update
            telemetry.update();
            //endregion
        }
    }
}