package org.firstinspires.ftc.teamcode.opmodes.year2020.testing.M1RC34_v3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="eltest", group="Testing")
public class M1RC34_v3 extends LinearOpMode
{
    public void turn(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR)
    {
        FL.setPower(gamepad2.right_stick_x);
        FR.setPower(gamepad2.right_stick_x);
        BL.setPower(gamepad2.right_stick_x);
        BR.setPower(gamepad2.right_stick_x);
    }

    public void strafe(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR)
    {
        FL.setPower(gamepad2.left_stick_x);
        FR.setPower(gamepad2.left_stick_x);
        BL.setPower(-gamepad2.left_stick_x);
        BR.setPower(-gamepad2.left_stick_x);
    }

    public void move(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR)
    {
        FL.setPower(-gamepad2.left_stick_y);
        BL.setPower(-gamepad2.left_stick_y);
        FR.setPower(gamepad2.left_stick_y);
        BR.setPower(gamepad2.left_stick_y);
    }

    @Override
    public void runOpMode() throws InterruptedException
    {
        //Variabile

        //Motoare normale
        //Pt. conducerea robotului
        DcMotor H1Motor0_FL  = hardwareMap.get(DcMotor.class, "H1Motor0_FL");
        DcMotor H1Motor1_FR  = hardwareMap.get(DcMotor.class, "H1Motor1_FR");
        DcMotor H1Motor2_BL  = hardwareMap.get(DcMotor.class, "H1Motor2_BL");
        DcMotor H1Motor3_BR  = hardwareMap.get(DcMotor.class, "H1Motor3_BR");


        //Servo-uri

        waitForStart();

        while (opModeIsActive() && !gamepad1.x)
        {
            //region Declara variabilele actualizate constant
            float
                    //Pt. miscarea robotului
                    driveSpeed_x,
                    driveSpeed_y
                            ;
            //endregion

            //region Controalele pentru robot
            if(gamepad1.left_stick_x != 0)
            {
                driveSpeed_x = gamepad1.left_stick_x;
            }
            else if(gamepad2.left_stick_x != 0)
            {
                driveSpeed_x = gamepad2.left_stick_x;
            }
            else driveSpeed_x = 0;

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
            H1Motor0_FL.setPower(0);
            H1Motor1_FR.setPower(0);
            H1Motor2_BL.setPower(0);
            H1Motor3_BR.setPower(0);
            //endregion

            move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR);
            strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR);
            turn(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR);

            //region Telemetrie
            //Viteza robotului
            /*
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
             */
        }
    }
}