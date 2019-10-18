package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.utils.Defines;
import org.firstinspires.ftc.teamcode.utils.Utils;

public class Controls {
    public DcMotor motorUpLeft, motorUpRight, motorDownLeft, motorDownRight;

    public PickupControls pickupControls;
    private SensorControls sensorControls;
    public CollectControls collectorControls;
    private static boolean pickupControlsEnabled = true;
    private static boolean controlsEnabled = true;
    private static boolean sensorControlsEnabled = true;
    private static boolean collectorControlsEnabled = true;

    public CollectControls getCollectorControls() {
        return collectorControls;
    }

    public void goDown(double speedEngine) {
        if (pickupControlsEnabled) {
            pickupControls.goDown(speedEngine);
        }
    }

    public void goUp(double speedEngine) {
        if (pickupControlsEnabled) {
            pickupControls.goUp(speedEngine);
        }
    }

    public Controls(HardwareMap hardwareMap) {
        if (controlsEnabled) {
            motorDownRight = hardwareMap.get(DcMotor.class, Defines.MOTOR_DOWN_RIGHT);
            motorDownLeft = hardwareMap.get(DcMotor.class, Defines.MOTOR_DOWN_LEFT);
            motorUpRight = hardwareMap.get(DcMotor.class, Defines.MOTOR_UP_RIGHT);
            motorUpLeft = hardwareMap.get(DcMotor.class, Defines.MOTOR_UP_LEFT);
            motorUpLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            motorDownLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (pickupControlsEnabled) {
            pickupControls = new PickupControls(hardwareMap);
        }

        if (sensorControlsEnabled) {
            //sensorControls = new SensorControls(hardwareMap);
        }

        if (collectorControlsEnabled) {
            collectorControls = new CollectControls(hardwareMap);
        }
    }

    /**
     * Moves the robot orthogonally
     * @param x x axis movement
     * @param y y axis movement
     */
    public void move(double x, double y)
    {
        motorUpRight.setPower(Utils.Clamp(x - y, -1.0, 1.0));
        motorDownRight.setPower(Utils.Clamp(x + y, -1.0, 1.0));
        motorUpLeft.setPower(Utils.Clamp(x + y, -1.0, 1.0));
        motorDownLeft.setPower(Utils.Clamp(x - y, -1.0, 1.0));
    }

    public void resetEngine()
    {
        pickupControls.reset();
        if(pickupControlsEnabled)
        {

        }

        if(controlsEnabled)
        {
            motorUpLeft.setPower(0);
            motorUpRight.setPower(0);
            motorDownLeft.setPower(0);
            motorDownRight.setPower(0);
        }

        if(collectorControlsEnabled)
        {
            collectorControls.resetEngines();
        }
    }

    public void rotateCounterClockwise(double speed)
    {
        motorUpLeft.setPower(-speed);
        motorUpRight.setPower(speed);
        motorDownLeft.setPower(-speed);
        motorDownRight.setPower(speed);
    }

    public void rotateClockwise(double speed)
    {
        motorUpLeft.setPower(speed);
        motorUpRight.setPower(-speed);
        motorDownLeft.setPower(speed);
        motorDownRight.setPower(-speed);
    }

    public void goBackwardLeft(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorUpLeft.setPower(-speedEngine);
            motorUpRight.setPower(-speedEngine);
        }
    }

    public void goBackwardRight(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorDownLeft.setPower(-speedEngine);
            motorUpRight.setPower(-speedEngine);
        }
    }

    public void goFrontwardLeft(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorDownLeft.setPower(speedEngine);
            motorUpRight.setPower(speedEngine);
        }
    }

    public void goFrontwordRight(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorUpLeft.setPower(speedEngine);
            motorDownRight.setPower(speedEngine);
        }
    }

    public void goBackward(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorUpLeft.setPower(-speedEngine);
            motorUpRight.setPower(-speedEngine);
            motorDownLeft.setPower(-speedEngine);
            motorDownRight.setPower(-speedEngine);
        }
    }

    public void goFrontward(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorUpLeft.setPower(speedEngine);
            motorUpRight.setPower(speedEngine);
            motorDownLeft.setPower(speedEngine);
            motorDownRight.setPower(speedEngine);
        }
    }

    public void goRight(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorDownRight.setPower(speedEngine);
            motorUpRight.setPower(-speedEngine);
            motorUpLeft.setPower(speedEngine);
            motorDownLeft.setPower(-speedEngine);
        }
    }

    public void goLeft(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorDownLeft.setPower(speedEngine);
            motorUpLeft.setPower(-speedEngine);
            motorUpRight.setPower(speedEngine);
            motorDownRight.setPower(-speedEngine);
        }
    }
}

