package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Defines;

public class PickupControls
{
    public DcMotor motor;
    public boolean motorMove;

    public PickupControls(HardwareMap hardwareMap)
    {
        motor = hardwareMap.get(DcMotor.class, Defines.MOTOR_PICKUP);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorMove = false;
    }

    public void goUp(double speed)
    {
        motor.setPower(speed);
    }

    public void goDown(double speed)
    {
        motor.setPower(-speed);
    }

    public void reset()
    {

    }
}

