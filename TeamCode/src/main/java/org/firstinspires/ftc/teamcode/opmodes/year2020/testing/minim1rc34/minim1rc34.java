package org.firstinspires.ftc.teamcode.opmodes.year2020.testing.minim1rc34;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="minim1rc34", group="Testing")
public class minim1rc34 extends LinearOpMode
{
    //region Declara motoarele
      //Motoare normale
        //Pt.conducerea robotului
          DcMotor H1Motor0_Drive  = hardwareMap.get(DcMotor.class, "H1Motor0_Drive");
          DcMotor H1Motor1_Drive  = hardwareMap.get(DcMotor.class, "H1Motor1_Drive");
          DcMotor H1Motor2_Drive  = hardwareMap.get(DcMotor.class, "H1Motor2_Drive");
          DcMotor H1Motor3_Drive  = hardwareMap.get(DcMotor.class, "H1Motor3_Drive");
    //endregion

    //region Declara clasele
      //Miscare fata-spate
//      public void goForward_Backward(float power)
//      {
//          H1Motor0_Drive.setPower(power);
//          H1Motor1_Drive.setPower(- power);
//          H1Motor2_Drive.setPower(power);
//          H1Motor3_Drive.setPower(- power);
//      }
    //endregion


    @Override
    public void runOpMode() throws InterruptedException
    {
        H1Motor0_Drive.setPower(0);
        H1Motor1_Drive.setPower(0);
        H1Motor2_Drive.setPower(0);
        H1Motor3_Drive.setPower(0);

        while(true)
        {
//            goForward_Backward(gamepad1.left_stick_y);
            H1Motor0_Drive.setPower(gamepad1.left_stick_y);
            H1Motor1_Drive.setPower(- gamepad1.left_stick_y);
            H1Motor2_Drive.setPower(gamepad1.left_stick_y);
            H1Motor3_Drive.setPower(- gamepad1.left_stick_y);
        }
    }

}