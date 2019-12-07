package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.controls.Controls;

@Autonomous(name="MyMircea")
public class Auto extends LinearOpMode {

    private Controls controls;

    private void setup() {
        controls = new Controls(hardwareMap);
        waitForStart();
    }

    private void moveRobot()
    {
        //controls
    }

    @Override
    public void runOpMode() throws InterruptedException {
        setup();
    }
}
