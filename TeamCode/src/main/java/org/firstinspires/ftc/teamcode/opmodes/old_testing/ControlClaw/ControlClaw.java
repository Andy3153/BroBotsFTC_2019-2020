//package org.firstinspires.ftc.teamcode.opmodes.old_testing.ControlClaw;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//
//
////@TeleOp
//public class ControlClaw extends LinearOpMode {
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        // run until the end of the match (driver presses STOP)
//        double tgtPower = 0;
//
//        while (opModeIsActive()) {
//            tgtPower = -this.gamepad1.left_stick_y;
//            motorTest.setPower(tgtPower);
//            // check to see if we need to move the servo.
//            if(gamepad1.y) {
//                // move to 0 degrees.
//                servoTest.setPosition(0);
//            } else if (gamepad1.x || gamepad1.b) {
//                // move to 90 degrees.
//                servoTest.setPosition(0.5);
//            } else if (gamepad1.a) {
//                // move to 180 degrees.
//                servoTest.setPosition(1);
//            }
//            telemetry.addData("Servo Position", servoTest.getPosition());
//            telemetry.addData("Target Power", tgtPower);
//            telemetry.addData("Motor Power", motorTest.getPower());
//            telemetry.addData("Status", "Running");
//            telemetry.update();
//
//        }
//
//
//    }
//
//
//
//
//
//}
