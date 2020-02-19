/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.opmodes.year2020.testing.Autonomy;

import android.drm.DrmStore;
import android.hardware.Sensor;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Functions.robotMovement;

import java.nio.channels.FileLock;

import static org.firstinspires.ftc.teamcode.Functions.Constants.plateMaxPos;
import static org.firstinspires.ftc.teamcode.Functions.Constants.plateMinPos;
import static org.firstinspires.ftc.teamcode.Functions.robotGrabbyThings.slashKill;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.move;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.strafe;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.turn;

@Autonomous(name="RightWall", group ="Autonomy")
public class BroBots_GoRight_Wall extends LinearOpMode {

    public float platePos=plateMinPos;

    public void stopRobot(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR)
    {
        move(FL, FR, BL, BR, 0, 0);
    }

    public void move(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed, int miliseconds) {
        robotMovement.move(FL, FR, BL, BR, speed);
        sleep(miliseconds);
    }

    public void strafe(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed, int miliseconds/*int ticks*/) {
        robotMovement.strafe(FL, FR, BL, BR, speed);
        sleep(miliseconds);
    }

    public void turn(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed, int miliseconds) {
        robotMovement.turn(FL, FR, BL, BR, speed);
        sleep(miliseconds);
    }

    private void slashKill(Servo left, Servo right, boolean isClosing)
    {
        if(isClosing)
            platePos = plateMaxPos;
        else
            platePos = plateMinPos;

        left.setPosition(1-platePos);
        right.setPosition(platePos);

        sleep(3000);
    }

    @Override
    public void runOpMode() {
        //int tickSpeed = 1120;
        float speed = 1;

        DcMotor H1Motor0_FL = hardwareMap.get(DcMotor.class, "H1Motor0_FL");
        DcMotor H1Motor1_FR = hardwareMap.get(DcMotor.class, "H1Motor1_FR");
        DcMotor H1Motor2_BL = hardwareMap.get(DcMotor.class, "H1Motor2_BL");
        DcMotor H1Motor3_BR = hardwareMap.get(DcMotor.class, "H1Motor3_BR");

        DcMotor H2Motor0_Arm = hardwareMap.get(DcMotor.class, "H2Motor0_Arm");
        Servo H2Servo0_PlateLeft = hardwareMap.get(Servo.class, "H2Servo0_PlateLeft");
        Servo H2Servo1_PlateRight = hardwareMap.get(Servo.class, "H2Servo1_PlateRight");

        ModernRoboticsI2cRangeSensor ultraSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "ultraSensor");

        waitForStart();

        if(opModeIsActive()) {
            H2Motor0_Arm.setPower(0);

            //Do stuff
            speed /= 3;

            //Move to plate
            move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speed, 100);
            strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speed, 250);
            robotMovement.move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speed);
            while (opModeIsActive() && ultraSensor.getDistance(DistanceUnit.CM) > 6.5)
            {
                telemetry.addData("Distance: ", ultraSensor.getDistance(DistanceUnit.CM));
                telemetry.update();
            }

            //Catch plate
            slashKill(H2Servo0_PlateLeft, H2Servo1_PlateRight, true);
            move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, -speed, 500);
            slashKill(H2Servo0_PlateLeft, H2Servo1_PlateRight, false);

            //Go around plate
            strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speed, 550);
            move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, -speed, 1500);
            strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, -speed, 550);
            turn(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speed, 700);

            //Get to plate again
            robotMovement.move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speed);
            while (ultraSensor.getDistance(DistanceUnit.CM) > 6) {
                telemetry.addData("Distance: ", ultraSensor.getDistance(DistanceUnit.CM));
                telemetry.update();
            }

            //Catch plate again
            slashKill(H2Servo0_PlateLeft, H2Servo1_PlateRight, true);
            move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speed, 1500);
            slashKill(H2Servo0_PlateLeft, H2Servo1_PlateRight, false);

            //Parking
            strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, -speed, 550);

            stopRobot(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR);
        }
    }
}
