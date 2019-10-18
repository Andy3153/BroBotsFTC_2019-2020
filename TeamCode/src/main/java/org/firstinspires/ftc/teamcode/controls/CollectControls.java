package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.utils.Defines;

public class CollectControls
{
    private DcMotor baseMotor, armMotor;
    private Servo servoCollector, servoGate, servoExtender;
    private boolean gateClosed, gateMoving;
    public boolean baseMotorMove, armMotorMove, servoCollectorMove, servoExtenderMove;

    public CollectControls(HardwareMap hardwareMap)
    {
        baseMotor = hardwareMap.get(DcMotor.class, Defines.MOTOR_COLLECTOR_BASE);
        armMotor = hardwareMap.get(DcMotor.class, Defines.MOTOR_COLLECTOR_ARM);
        servoExtender = hardwareMap.get(Servo.class, Defines.SERVO_COLLECTOR_EXTENDER);
        servoCollector = hardwareMap.get(Servo.class, Defines.SERVO_COLLECTOR);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        baseMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        gateClosed = true;
        gateMoving = false;
        baseMotorMove = armMotorMove = servoCollectorMove = servoExtenderMove = false;
    }

    private void moveGate(double position)
    {
        servoGate.setPosition(position);
        try
        {
            Thread.sleep(500);
        }
        catch(InterruptedException e)
        {
        }
        servoGate.setPosition(0.5);
        gateMoving = false;
    }

    public void gate()
    {
        if(gateMoving)
            return;
        gateMoving = true;
        if(gateClosed)
        {
            final Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    moveGate(0.0);
                    try
                    {
                        Thread.sleep(500);
                    }
                    catch(InterruptedException e)
                    {

                    }
                    moveGate(0.5);
                    gateClosed = false;
                    gateMoving = false;
                }
            });
            t.run();
        }
        else
        {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    moveGate(1.0);
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e)
                    {

                    }
                    moveGate(0.5);
                    gateClosed = true;
                    gateMoving = false;
                }
            });
            t.run();
        }
    }

    public void collect()
    {
        servoCollector.setPosition(1.0);
    }

    public void base(double speed)
    {
        baseMotor.setPower(speed);
    }

    public void arm(double speed)
    {
        armMotor.setPower(speed);
    }

    public void extend(double dir)
    {
        servoExtender.setPosition(dir);
    }

    public void resetServos()
    {
        servoCollector.setPosition(0.5);
        //servoExtender.setPosition(0.5);
    }

    public void resetEngines()
    {
        //baseMotor.setPower(0);
        //armMotor.setPower(0);
    }
}
