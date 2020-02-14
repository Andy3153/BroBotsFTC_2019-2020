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

package org.firstinspires.ftc.teamcode.opmodes.year2020.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

@Autonomous(name="RoadRunnerLeftBridge", group ="Autonomy")
public class BroBots_GoLeft extends LinearOpMode {


    public void turn(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed, int miliseconds/*int ticks*/) {
        /*FL.setTargetPosition(-ticks);
        FR.setTargetPosition(-ticks);
        BL.setTargetPosition(-ticks);
        BR.setTargetPosition(-ticks);*/
        FL.setPower(speed);
        FR.setPower(speed);
        BL.setPower(speed);
        BR.setPower(speed);
        sleep(miliseconds);
    }

    public void strafe(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed, int miliseconds/*int ticks*/) {
        /*FL.setTargetPosition(-ticks);
        FR.setTargetPosition(-ticks);
        BL.setTargetPosition(ticks);
        BR.setTargetPosition(ticks);*/
        FL.setPower(speed);
        FR.setPower(speed);
        BL.setPower(-speed);
        BR.setPower(-speed);
        sleep(miliseconds);
    }

    public void move(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed, int miliseconds/*int ticks*/) {
        /*FL.setTargetPosition(ticks);
        BL.setTargetPosition(ticks);
        FR.setTargetPosition(-ticks);
        BR.setTargetPosition(-ticks);*/
        FL.setPower(-speed);
        BL.setPower(-speed);
        FR.setPower(speed);
        BR.setPower(speed);
        sleep(miliseconds);
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
        Servo H2Servo0_ClawRotate = hardwareMap.get(Servo.class, "H2Servo0_ClawRotate");
        Servo H2Servo1_Claw = hardwareMap.get(Servo.class, "H2Servo1_Claw");

        waitForStart();

        H2Motor0_Arm.setPower(0);
        H2Servo0_ClawRotate.setPosition(1);
        H2Servo1_Claw.setPosition(0.7f);

        move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, -speed, 400);
        strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, -speed, 850);

        move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, 0, 0);

  /*      H1Motor0_FL.setPower(0);
        H1Motor1_FR.setPower(0);
        H1Motor2_BL.setPower(0);
        H1Motor3_BR.setPower(0);
*/
/*        try {
            H1Motor0_FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            H1Motor1_FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            H1Motor2_BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            H1Motor3_BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            H1Motor0_FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            H1Motor1_FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            H1Motor2_BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            H1Motor3_BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        } catch (Exception ex) {
            telemetry.addData("Eroare: ", ex.getMessage());
        }

        //waitForStart();

        //if (opModeIsActive()) {
            try {
                move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, tickSpeed / 2);
                sleep(1000);
                strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, -tickSpeed * 2);
                sleep(1000);
            } catch (Exception ex) {
                telemetry.addData("Eroare: ", ex.getMessage());
            }
        //}

        telemetry.update();*/
    }
}