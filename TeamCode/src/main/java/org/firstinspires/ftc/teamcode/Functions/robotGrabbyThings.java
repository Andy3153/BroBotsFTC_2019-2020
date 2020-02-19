package org.firstinspires.ftc.teamcode.Functions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.Functions.Constants.plateMaxPos;
import static org.firstinspires.ftc.teamcode.Functions.Constants.plateMinPos;

public class robotGrabbyThings {

    public static float slashKill(Servo left, Servo right, float sticky, float platePos)
    {
        if (sticky > 0)
        {
            platePos=slashKill(left, right, true, platePos);
        }
        else if (sticky < 0)
        {
            platePos=slashKill(left, right, false, platePos);
        }

        return platePos;
    }

    public static float slashKill(Servo left, Servo right, boolean isClosing, float platePos)
    {
        if(isClosing)
            platePos = platePos > plateMaxPos-0.05f ? plateMaxPos : platePos + 0.05f;
        else
            platePos = platePos < plateMinPos-0.05f ? plateMinPos : platePos - 0.05f;

        left.setPosition(1-platePos);
        right.setPosition(platePos);

        return platePos;
    }

    public static void moveArm(DcMotor arm, boolean up, boolean down)
    {
        arm.setPower(up?0.55:down?-0.55:0);
    }

}
