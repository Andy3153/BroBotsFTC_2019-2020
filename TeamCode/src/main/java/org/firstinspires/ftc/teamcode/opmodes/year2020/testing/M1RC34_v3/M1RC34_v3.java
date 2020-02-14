package org.firstinspires.ftc.teamcode.opmodes.year2020.testing.M1RC34_v3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="eltest", group="Testing")
public class M1RC34_v3 extends LinearOpMode
{
    public void turn(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed)
    {
        FL.setPower(speed);
        FR.setPower(speed);
        BL.setPower(speed);
        BR.setPower(speed);
    }

    public void strafe(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed)
    {
        FL.setPower(speed);
        FR.setPower(speed);
        BL.setPower(-speed);
        BR.setPower(-speed);
    }

    public void move(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed)
    {
        FL.setPower(-speed);
        BL.setPower(-speed);
        FR.setPower(speed);
        BR.setPower(speed);
    }

    public void moveArm(DcMotor arm)
    {
        if(gamepad2.dpad_up)
            arm.setPower(0.55);
        else if(gamepad2.dpad_down)
            arm.setPower(-0.55);
        else
            arm.setPower(0);
//        arm.setPower(gamepad2.dpad_up?1:gamepad2.dpad_down?-1:0);
    }

    public void rotateClaw(Servo rotateServo)
    {
        if(gamepad2.dpad_left&& rotateServo.getPosition()>0)
            rotateServo.setPosition(rotateServo.getPosition()-0.025f);
        else if (gamepad2.dpad_right&& rotateServo.getPosition()<1)
            rotateServo.setPosition(rotateServo.getPosition()+0.025f);
    }

    public void useClaw(Servo clawServo)
    {
        if(gamepad2.right_bumper) {
            if (clawServo.getPosition() > 0)
                clawServo.setPosition(clawServo.getPosition() - 0.025f);
        }
        if(gamepad2.left_bumper)
        {
            if(clawServo.getPosition() < 1)
                clawServo.setPosition(clawServo.getPosition() + 0.025f);
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
            float speedX=gamepad1.left_stick_x, speedY=gamepad1.left_stick_y, speedTurn=gamepad1.right_stick_x;
            speedX = gamepad1.y?speedX/1.75f:speedX;
            speedY = gamepad1.y?speedY/1.75f:speedY;

            float speedX2=gamepad2.left_stick_x, speedY2=gamepad2.left_stick_y, speedTurn2=gamepad2.right_stick_x;
            speedX2 = gamepad2.y?speedX2/1.75f:speedX2;
            speedY2 = gamepad2.y?speedY2/1.75f:speedY2;

            //region Seteaza curentul la 0 la motoare
            //Pt. roti
            H1Motor0_FL.setPower(0);
            H1Motor1_FR.setPower(0);
            H1Motor2_BL.setPower(0);
            H1Motor3_BR.setPower(0);

            if(gamepad2.left_stick_x==0 || gamepad2.left_stick_y==0 || gamepad2.right_stick_x==0)
                if(!gamepad1.a) {
                    move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speedY);
                    strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speedX);
                    turn(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speedTurn);
                }
                else {
                    move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, 0);
                    strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, 0);
                    turn(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, 0);
                }
            else {
                if(!gamepad2.a) {
                    move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speedY2);
                    strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speedX2);
                    turn(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speedTurn2);
                }
                else
                {
                    move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, 0);
                    strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, 0);
                    turn(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, 0);
                }
            }

            moveArm(H2Motor0_Arm);
            rotateClaw(H2Servo0_ClawRotate);
            useClaw(H2Servo1_Claw);

            //region Telemetrie

            telemetry.addData("Pozitia rotatiei ghearei", H2Servo0_ClawRotate.getPosition()*10);
            telemetry.addData("Pozitia ghearei", H2Servo1_Claw.getPosition()*10);
            telemetry.addData("Motor Fl", (H1Motor0_FL.getPower()+H1Motor2_BL.getPower()+H1Motor1_FR.getPower()+H1Motor3_BR.getPower())/4);

            //Update
            telemetry.update();

            //endregion
        }
    }
}