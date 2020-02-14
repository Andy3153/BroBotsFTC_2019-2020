package org.firstinspires.ftc.teamcode.opmodes.year2020.testing.sdf;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="sdf", group="sdf")
@Disabled
public class sdf extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {

//        //region Declara variabilele actualizate numai odata
//        float
//                //Pt. pozitia ghearei
//                clawInitPos=0, clawMaxPos=0.5899999f, clawMinPos=0,
//                clawPos = clawInitPos,
//
//                //Pt. pozitia bratului
//                armInitPos=0.4299995f, armMaxPos=0.547998f, armMinPos=0.4299995f,
//                armPos = armInitPos,
//
//                //Pt. pozitia tavii
//                plateInitPos = 0, plateMaxPos=0.6f, plateMinPos=0,
//                platePos = plateInitPos
//
////              //Pt. jumatate din putere
////              halfPwrMD_x = driveSpeed_x / 1.5f,
////              halfPwrMD_y = driveSpeed_y / 1.5f,
////              halfPwrMA_x = gamepad1.right_stick_x / 1.5f,
////              halfPwrMA_y = gamepad1.right_stick_y / 1.5f,
//                        ;
//        //endregion

        //region Declara motoarele
        //Motoare normale
        //Pt. conducerea robotului
        DcMotor H1Motor0_Drive  = hardwareMap.get(DcMotor.class, "H1Motor0_Drive");
        DcMotor H1Motor1_Drive  = hardwareMap.get(DcMotor.class, "H1Motor1_Drive");
        DcMotor H1Motor2_Drive  = hardwareMap.get(DcMotor.class, "H1Motor2_Drive");
        DcMotor H1Motor3_Drive  = hardwareMap.get(DcMotor.class, "H1Motor3_Drive");

//        //Pt. ata
//        DcMotor H2Motor0_ArmString = hardwareMap.get(DcMotor.class, "H2Motor0_ArmString");
//
//        //Servo-uri
//        //Pt. baza
//        Servo H2Servo0_ArmBase= hardwareMap.get(Servo.class, "H2Servo0_ArmBase");
//
//        //Pt. gheara
//        Servo H2Servo1_ClawL = hardwareMap.get(Servo.class, "H2Servo1_ClawL");
//        Servo H2Servo2_ClawR = hardwareMap.get(Servo.class, "H2Servo2_ClawR");
//
//        //Pt. tava
//        Servo H2Servo3_PlateL = hardwareMap.get(Servo.class, "H2Servo3_PlateL");
//        Servo H2Servo4_PlateR = hardwareMap.get(Servo.class, "H2Servo4_PlateR");
        //endregion

        while (true)
        {
//            //region Declara variabilele actualizate constant
//            float
//                    //Pt. miscarea robotului
//                    driveSpeed_x,
//                    driveSpeed_y
//                            ;
//            //endregion

//            //region Controalele pentru robot
//            if(gamepad1.left_stick_x != 0)
//            {
//                driveSpeed_x = gamepad1.left_stick_x;
//            }
//            else if(gamepad2.left_stick_x != 0)
//            {
//                driveSpeed_x = gamepad2.left_stick_x;
//            }
//            else driveSpeed_x = 0;
//
//
//            if(gamepad1.right_trigger > 0)
//            {
//                driveSpeed_y = -gamepad1.right_trigger;
//            }
//            else if(gamepad1.left_trigger > 0)
//            {
//                driveSpeed_y = gamepad1.left_trigger;
//            }
//            else if (gamepad2.left_stick_y != 0)
//            {
//                driveSpeed_y = gamepad2.left_stick_y;
//            }
//            else driveSpeed_y = 0;
//            //endregion

            //region Seteaza curentul la 0 la motoare
            //Pt. roti
            H1Motor0_Drive.setPower(0);
            H1Motor1_Drive.setPower(0);

//            //Pt. brat
//            H2Motor0_ArmString.setPower(0);
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
            H1Motor0_Drive.setPower(-gamepad2.left_stick_y);
            H1Motor1_Drive.setPower(gamepad2.left_stick_y);
            H1Motor2_Drive.setPower(-gamepad2.left_stick_y);
            H1Motor3_Drive.setPower(gamepad2.left_stick_y);

            //cu right stick
            H1Motor0_Drive.setPower(-gamepad2.right_stick_y);
            H1Motor1_Drive.setPower(gamepad2.right_stick_y);
            H1Motor2_Drive.setPower(-gamepad2.right_stick_y);
            H1Motor3_Drive.setPower(gamepad2.right_stick_y);

            //Miscarea stanga-dreapta
            H1Motor0_Drive.setPower(gamepad2.left_stick_x);
            H1Motor1_Drive.setPower(gamepad2.left_stick_x);
            H1Motor2_Drive.setPower(gamepad2.left_stick_x);
            H1Motor3_Drive.setPower(gamepad2.left_stick_x);

            //Strafing
            H1Motor0_Drive.setPower(gamepad2.right_stick_x);
            H1Motor1_Drive.setPower(gamepad2.right_stick_x);
            H1Motor2_Drive.setPower(-gamepad2.right_stick_x);
            H1Motor3_Drive.setPower(-gamepad2.right_stick_x);

            //cu triggere
//            if(gamepad2.left_trigger > 0)
//            {
                H1Motor0_Drive.setPower(gamepad2.left_trigger);
                H1Motor1_Drive.setPower(gamepad2.left_trigger);
                H1Motor2_Drive.setPower(-gamepad2.left_trigger);
                H1Motor3_Drive.setPower(-gamepad2.left_trigger);
//            }
//            else if (gamepad2.right_trigger > 0)
//            {
                H1Motor0_Drive.setPower(-gamepad2.right_trigger);
                H1Motor1_Drive.setPower(-gamepad2.right_trigger);
                H1Motor2_Drive.setPower(gamepad2.right_trigger);
                H1Motor3_Drive.setPower(gamepad2.right_trigger);

            //endregion

//            //region Miscarea bratului
//            //region Pt. ata
//            if(gamepad2.dpad_right)
//            {
//                H2Motor0_ArmString.setPower(1);
//            }
//            else
//            if(gamepad2.dpad_left)
//            {
//                H2Motor0_ArmString.setPower(-1);
//            }
//            else H2Motor0_ArmString.setPower(0);
//            //endregion
//
//            //region Pt. brat
//            if (gamepad2.dpad_up)
//            {
//                armPos = armPos>armMaxPos?armMaxPos:armPos+0.001f;
//            }
//            else if (gamepad2.dpad_down)
//            {
//                armPos = armPos<armMinPos?armMinPos:armPos-0.001f;
//            }
//
//            H2Servo0_ArmBase.setPosition(armPos);
//            //endregion
//
//            //region Pt. gheara
//            if (gamepad2.left_bumper)
//            {
//                clawPos=clawPos>clawMaxPos?clawMaxPos:clawPos+0.01f;
//            }
//            else
//            if (gamepad2.right_bumper)
//            {
//                clawPos = clawPos<clawMinPos?clawMinPos:clawPos-0.01f;
//            }
//
//            H2Servo1_ClawL.setPosition(clawPos);
//            H2Servo2_ClawR.setPosition(1 - clawPos);
//            //endregion
//
//            //region Pt. tava
//            if (gamepad2.right_stick_y > 0)
//            {
//                platePos = platePos > plateMaxPos ? plateMaxPos : platePos + 0.01f;
//            }
//            else
//            if (gamepad2.right_stick_y < 0)
//            {
//                platePos = platePos < plateMinPos ? plateMinPos : platePos - 0.01f;
//            }
//
////            H2Servo3_PlateL.setPosition(platePos);
////            H2Servo4_PlateR.setPosition(1 - platePos)
//            H2Servo4_PlateR.setPosition(platePos);
//            H2Servo3_PlateL.setPosition(1-platePos);
//            //endregion
//            //endregion

//            //region Butoane de oprire
//            //Oprirea miscarii robotului
//            if(gamepad1.y)
//            {
//                H1Motor0_Drive.setPower(0);
//                H1Motor1_Drive.setPower(0);
//            }
//
//            //Oprirea miscarii bratului
//            if(gamepad2.b)
//            {
//                //Pt. ata
//                H2Motor0_ArmString.setPower(0);
//
//                //Pt. brat
//                armPos = armInitPos;
//
//                //Pt. gheara
//                clawPos = clawInitPos;
//
//                //Pt. tava
//                platePos = plateInitPos;
//            }
//
//            //Start / stop pentru tot codul
//            if(gamepad1.back)
//            {
//                stop();
//            }
//
//            if(gamepad1.start)
//            {
//                start();
//            }
////            //endregion

//            //region Telemetrie
//            //Viteza robotului
//            telemetry.addData("Viteza robotului", 10*(1 - driveSpeed_x + driveSpeed_y));
//
//            //Pozitia bratului
//            telemetry.addData("Pozitia bratului", 1+armPos);
//
//            //Pozitia ghearei
//            telemetry.addData("Pozitia ghearei", clawPos);
//
//            //Pozitia tavii
//            telemetry.addData("Pozitia tavii", platePos);
//
//            //Update
//            telemetry.update();
//            //endregion
        }
    }
}