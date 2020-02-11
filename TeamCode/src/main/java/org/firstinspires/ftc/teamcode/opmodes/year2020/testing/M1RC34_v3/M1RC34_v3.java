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

    public void moveArm(DcMotor arm)
    {
        if(gamepad2.dpad_up)
            arm.setPower(1);
        else if(gamepad2.dpad_down)
            arm.setPower(-1);
        else
            arm.setPower(0);
//        arm.setPower(gamepad2.dpad_up?1:gamepad2.dpad_down?-1:0);
    }

    public void rotateClaw(Servo rotateServo)
    {
        /*if(gamepad2.left_trigger > 0) {
            if (rotateServo.getPosition() > 0)
                rotateServo.setPosition(rotateServo.getPosition() - 0.1f);
        }
        if(gamepad2.right_trigger>0)
        {
            if(rotateServo.getPosition()<1)
                rotateServo.setPosition(rotateServo.getPosition()+0.1f);
        }*/
        if(gamepad2.y && rotateServo.getPosition()>0)
            rotateServo.setPosition(rotateServo.getPosition()-0.1f);
        else if (gamepad2.a && rotateServo.getPosition()<1)
            rotateServo.setPosition(rotateServo.getPosition()+0.1f);
    }

    public void useClaw(Servo clawServo)
    {
        if(gamepad2.x) {
            if (clawServo.getPosition() > 0)
                clawServo.setPosition(clawServo.getPosition() - 0.1f);
        }
        if(gamepad2.b)
        {
            if(clawServo.getPosition() < 1)
                clawServo.setPosition(clawServo.getPosition() + 0.1f);
        }
    }

    @Override
    public void runOpMode() throws InterruptedException
    {
        //region Defineste motoarele
          //Motoare normale
            //Pt. conducerea robotului
            DcMotor H1Motor0_FL  = hardwareMap.get(DcMotor.class, "H1Motor0_FL");
            DcMotor H1Motor1_FR  = hardwareMap.get(DcMotor.class, "H1Motor1_FR");
            DcMotor H1Motor2_BL  = hardwareMap.get(DcMotor.class, "H1Motor2_BL");
            DcMotor H1Motor3_BR  = hardwareMap.get(DcMotor.class, "H1Motor3_BR");

            //Pt. brat
            DcMotor H2Motor0_Arm = hardwareMap.get(DcMotor.class, "H2Motor0_Arm");

          //Servo-uri
          Servo H2Servo0_ClawRotate = hardwareMap.get(Servo.class, "H2Servo0_ClawRotate");
          Servo H2Servo1_Claw = hardwareMap.get(Servo.class, "H2Servo1_Claw");
        //endregion

        //region Seteaza pozitia initiala la servouri
        H2Servo0_ClawRotate.setPosition(1);
        H2Servo1_Claw.setPosition(0.7f);
        //endregion

        waitForStart();
        while (opModeIsActive() && !gamepad1.x)
        {
            //region Seteaza curentul la 0 la motoare
            //Pt. roti
            H1Motor0_FL.setPower(0);
            H1Motor1_FR.setPower(0);
            H1Motor2_BL.setPower(0);
            H1Motor3_BR.setPower(0);

            move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR);
            strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR);
            turn(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR);
            moveArm(H2Motor0_Arm);
            rotateClaw(H2Servo0_ClawRotate);
            useClaw(H2Servo1_Claw);

            //region Telemetrie
            //Viteza robotului

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

            telemetry.addData("Pozitia rotatiei ghearei", H2Servo0_ClawRotate.getPosition());
            telemetry.addData("Pozitia ghearei", H2Servo1_Claw.getPosition());


            //Update
            telemetry.update();

            //endregion

        }
    }
}