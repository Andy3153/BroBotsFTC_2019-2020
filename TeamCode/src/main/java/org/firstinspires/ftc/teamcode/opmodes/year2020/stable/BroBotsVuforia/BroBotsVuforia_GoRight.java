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

package org.firstinspires.ftc.teamcode.opmodes.year2020.stable.BroBotsVuforia;

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

@Autonomous(name="AutonomyRIGHT", group ="Concept")
public class BroBotsVuforia_GoRight extends LinearOpMode
{

    DcMotor H1Motor1_Drive, H1Motor0_Drive, H2Motor0_ArmString;
    Servo H2Servo0_ArmBase, H2Servo1_ClawL, H2Servo2_ClawR;

    public void stopRobot()
    {
        goForward(0, 0);
    }

    public void goLeft(float power, int miliseconds)
    {
        H1Motor0_Drive.setPower(power);
        H1Motor1_Drive.setPower(power);
        sleep(miliseconds);
    }

    public void goRight(float power, int miliseconds)
    {
        H1Motor0_Drive.setPower(-power);
        H1Motor1_Drive.setPower(-power);
        sleep(miliseconds);
    }

    public void goForward(float power, int miliseconds)
    {
        H1Motor0_Drive.setPower(power);
        H1Motor1_Drive.setPower(-power);
        sleep(miliseconds);
    }

    public void goBackwards(float power, int miliseconds)
    {
        H1Motor0_Drive.setPower(-power);
        H1Motor1_Drive.setPower(power);
        sleep(miliseconds);
    }

    public void catchCube()
    {
        H2Servo1_ClawL.setPosition(0);
        H2Servo2_ClawR.setPosition(1);
        H2Servo0_ArmBase.setPosition(0.432f);
        sleep(1650);
        H2Servo1_ClawL.setPosition(0.47f);
        H2Servo2_ClawR.setPosition(0.53f);
        sleep(300);
        H2Servo0_ArmBase.setPosition(0.5f);
    }

    //region nuj
    public String VUFORIA_KEY="AUBpzCP/////AAABmVA9gBBLJ0mknFkjJAvfbZMgigw65DTNLvb+ioQTKRe9fL52yAllQQ1e4HJhxjpgoslb9Wpxa9dQrSQ+EWkybOYm0TUyl8uk+MyG2GAtxyPOUFOylH5byYHKzAVp8gODnBVDsU0Xfbs7D0wIFmP/QTyz2mZgdWPTA3y0QdBBKcWeksmWMsjNVvtO5HtYrm82FolMY6GFi/7m7uoAicBD/OwCEvknmwm1Qw6FD5HhAnqjkmkSK3TM1XqtJgVuaDNE+ay2NpMBJw63CBKYf/p4Jdljdp1BwFOf7w4IizWsZ+Ix5WNe5r2SmH25VR70eoUrmpS4tO612b30yztLiS/CmMC3+Axx7vYaqJ6kLPWWQ0C/";
    public static final String TAG = "Vuforia";
    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;
    //endregion
    @Override public void runOpMode()
    {
        H1Motor0_Drive  = hardwareMap.get(DcMotor.class, "H1Motor0_Drive ");
        H1Motor1_Drive= hardwareMap.get(DcMotor.class, "H1Motor1_Drive ");

        //region nuj
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = VUFORIA_KEY;

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        /**
         * Load the data sets that for the trackable objects we wish to track. These particular data
         * sets are stored in the 'assets' part of our application (you'll see them in the Android
         * Studio 'Project' view over there on the left of the screen). You can make your own datasets
         * with the Vuforia Target Manager: https://developer.vuforia.com/target-manager. PDFs for the
         * example "StonesAndChips", datasets can be found in in this project in the
         * documentation directory.
         */
        VuforiaTrackables stonesAndChips = this.vuforia.loadTrackablesFromAsset("StonesAndChips");
        VuforiaTrackable redTarget = stonesAndChips.get(0);
        redTarget.setName("RedTarget");  // Stones

        VuforiaTrackable blueTarget  = stonesAndChips.get(1);
        blueTarget.setName("BlueTarget");  // Chips

        /** For convenience, gather together all the trackable objects in one easily-iterable collection */
        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(stonesAndChips);

        float mmPerInch        = 25.4f;
        float mmBotWidth       = 18 * mmPerInch;            // ... or whatever is right for your robot
        float mmFTCFieldWidth  = (12*12 - 2) * mmPerInch;   // the FTC field is ~11'10" center-to-center of the glass panels

        /**
         * In order for localization to work, we need to tell the system where each target we
         * wish to use for navigation resides on the field, and we need to specify where on the robot
         * the phone resides. These specifications are in the form of <em>transformation matrices.</em>
         * Transformation matrices are a central, important concept in the math here involved in localization.
         * See <a href="https://en.wikipedia.org/wiki/Transformation_matrix">Transformation Matrix</a>
         * for detailed information. Commonly, you'll encounter transformation matrices as instances
         * of the {@link OpenGLMatrix} class.
         *
         * For the most part, you don't need to understand the details of the math of how transformation
         * matrices work inside (as fascinating as that is, truly). Just remember these key points:
         * <ol>
         *
         *     <li>You can put two transformations together to produce a third that combines the effect of
         *     both of them. If, for example, you have a rotation transform R and a translation transform T,
         *     then the combined transformation matrix RT which does the rotation first and then the translation
         *     is given by {@code RT = T.multiplied(R)}. That is, the transforms are multiplied in the
         *     <em>reverse</em> of the chronological order in which they applied.</li>
         *
         *     <li>A common way to create useful transforms is to use methods in the {@link OpenGLMatrix}
         *     class and the Orientation class. See, for example, {@link OpenGLMatrix#translation(float,
         *     float, float)}, {@link OpenGLMatrix#rotation(AngleUnit, float, float, float, float)}, and
         *     {@link Orientation#getRotationMatrix(AxesReference, AxesOrder, AngleUnit, float, float, float)}.
         *     Related methods in {@link OpenGLMatrix}, such as {@link OpenGLMatrix#rotated(AngleUnit,
         *     float, float, float, float)}, are syntactic shorthands for creating a new transform and
         *     then immediately multiplying the receiver by it, which can be convenient at times.</li>
         *
         *     <li>If you want to break open the black box of a transformation matrix to understand
         *     what it's doing inside, use {@link MatrixF#getTranslation()} to fetch how much the
         *     transform will move you in x, y, and z, and use {@link Orientation#getOrientation(MatrixF,
         *     AxesReference, AxesOrder, AngleUnit)} to determine the rotational motion that the transform
         *     will impart. See {@link #format(OpenGLMatrix)} below for an example.</li>
         *
         * </ol>
         *
         * This example places the "stones" image on the perimeter wall to the Left
         *  of the Red Driver station wall.  Similar to the Red Beacon Location on the Res-Q
         *
         * This example places the "chips" image on the perimeter wall to the Right
         *  of the Blue Driver station.  Similar to the Blue Beacon Location on the Res-Q
         *
         * See the doc folder of this project for a description of the field Axis conventions.
         *
         * Initially the target is conceptually lying at the origin of the field's coordinate system
         * (the center of the field), facing up.
         *
         * In this configuration, the target's coordinate system aligns with that of the field.
         *
         * In a real situation we'd also account for the vertical (Z) offset of the target,
         * but for simplicity, we ignore that here; for a real robot, you'll want to fix that.
         *
         * To place the Stones Target on the Red Audience wall:
         * - First we rotate it 90 around the field's X axis to flip it upright
         * - Then we rotate it  90 around the field's Z access to face it away from the audience.
         * - Finally, we translate it back along the X axis towards the red audience wall.
         */
        OpenGLMatrix redTargetLocationOnField = OpenGLMatrix
                /* Then we translate the target off to the RED WALL. Our translation here
                is a negative translation in X.*/
                .translation(-mmFTCFieldWidth/2, 0, 0)
                .multiplied(Orientation.getRotationMatrix(
                        /* First, in the fixed (field) coordinate system, we rotate 90deg in X, then 90 in Z */
                        AxesReference.EXTRINSIC, AxesOrder.XZX,
                        AngleUnit.DEGREES, 90, 90, 0));
        redTarget.setLocation(redTargetLocationOnField);
        RobotLog.ii(TAG, "Red Target=%s", format(redTargetLocationOnField));

       /*
        * To place the Stones Target on the Blue Audience wall:
        * - First we rotate it 90 around the field's X axis to flip it upright
        * - Finally, we translate it along the Y axis towards the blue audience wall.
        */
        OpenGLMatrix blueTargetLocationOnField = OpenGLMatrix
                /* Then we translate the target off to the Blue Audience wall.
                Our translation here is a positive translation in Y.*/
                .translation(0, mmFTCFieldWidth/2, 0)
                .multiplied(Orientation.getRotationMatrix(
                        /* First, in the fixed (field) coordinate system, we rotate 90deg in X */
                        AxesReference.EXTRINSIC, AxesOrder.XZX,
                        AngleUnit.DEGREES, 90, 0, 0));
        blueTarget.setLocation(blueTargetLocationOnField);
        RobotLog.ii(TAG, "Blue Target=%s", format(blueTargetLocationOnField));

        /**
         * Create a transformation matrix describing where the phone is on the robot. Here, we
         * put the phone on the right hand side of the robot with the screen facing in (see our
         * choice of BACK camera above) and in landscape mode. Starting from alignment between the
         * robot's and phone's axes, this is a rotation of -90deg along the Y axis.
         *
         * When determining whether a rotation is positive or negative, consider yourself as looking
         * down the (positive) axis of rotation from the positive towards the origin. Positive rotations
         * are then CCW, and negative rotations CW. An example: consider looking down the positive Z
         * axis towards the origin. A positive rotation about Z (ie: a rotation parallel to the the X-Y
         * plane) is then CCW, as one would normally expect from the usual classic 2D geometry.
         */
        OpenGLMatrix phoneLocationOnRobot = OpenGLMatrix
                .translation(mmBotWidth/2,0,0)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.YZY,
                        AngleUnit.DEGREES, 0, 0, 0));
        RobotLog.ii(TAG, "phone=%s", format(phoneLocationOnRobot));

        ((VuforiaTrackableDefaultListener)redTarget.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);
        ((VuforiaTrackableDefaultListener)blueTarget.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);

        /**
         * A brief tutorial: here's how all the math is going to work:
         *
         * C = phoneLocationOnRobot  maps   phone coords -> robot coords
         * P = tracker.getPose()     maps   image target coords -> phone coords
         * L = redTargetLocationOnField maps   image target coords -> field coords
         *
         * So
         *
         * C.inverted()              maps   robot coords -> phone coords
         * P.inverted()              maps   phone coords -> imageTarget coords
         *
         * Putting that all together,
         *
         * L x P.inverted() x C.inverted() maps robot coords to field coords.
         *
         * @see VuforiaTrackableDefaultListener#getRobotLocation()
         */

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        waitForStart();

        /** Start tracking the data sets we care about. */
        stonesAndChips.activate();
        //endregion

        //region Declara variabilele actualizate numai odata
        float
                //Pt. pozitia ghearei
                clawInitPos=0.2f,     clawMaxPos=0.5899999f, clawMinPos=0,
                clawPos = clawInitPos,

                //Pt. pozitia bratului
                armInitPos=0.4299995f, armMaxPos=0.547998f,   armMinPos=0.43f,
                armPos = armInitPos,

                //Pt. miscarea robotului
                driveSpeed_x = 0,
                driveSpeed_y = 0
        ;
        //endregion

        //region Declara motoarele
        //Motoare normale
        //Pt. conducerea robotului

        //Pt. ata
        H2Motor0_ArmString = hardwareMap.get(DcMotor.class, "H2Motor0_ArmString");

        //Servo-uri
        //Pt. baza
        H2Servo0_ArmBase= hardwareMap.get(Servo.class, "H2Servo0_ArmBase");

        //Pt. gheara
        H2Servo1_ClawL = hardwareMap.get(Servo.class, "H2Servo1_ClawL");
        H2Servo2_ClawR = hardwareMap.get(Servo.class, "H2Servo2_ClawR");
        //endregion

        //region Seteaza pozitia ghearei
        sleep(1000);
        clawPos = 0;
        //endregion
        H2Servo0_ArmBase.setPosition(armMinPos);

        /*while (opModeIsActive())
        {*/
            //region nuj
            for (VuforiaTrackable trackable : allTrackables) {
                telemetry.addData(trackable.getName(), ((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible() ? "Visible" : "Not Visible");    //

                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
            }
             // Provide feedback as to where the robot was last located (if we know).

            if (lastLocation != null) {
                //  RobotLog.vv(TAG, "robot=%s", format(lastLocation));
                telemetry.addData("Pos", format(lastLocation));
            } else {
                telemetry.addData("Pos", "Unknown");
            }
            telemetry.update();
            //endregion

            //region Miscarea robotului
            //Miscarea fata-spate
            H1Motor0_Drive.setPower(-driveSpeed_y);
            H1Motor1_Drive.setPower(driveSpeed_y);

            //Miscarea stanga-dreapta
            if (driveSpeed_x != 0)
            {
                H1Motor0_Drive.setPower(-driveSpeed_x);
                H1Motor1_Drive.setPower(-driveSpeed_x);
            }
            //endregion
            clawPos = clawInitPos;
            armPos = armMinPos;

        //region Miscarea bratului
            //region Pt. brat
            H2Servo0_ArmBase.setPosition(armPos);
            //endregion

            //region Pt. gheara
            H2Servo1_ClawL.setPosition(clawPos);
            H2Servo2_ClawR.setPosition(1 - clawPos);
            //endregion
            //endregion

            //region Automat cica
            //ACTUAL COMMANDS
            /*goLeft(1, 500);(
            goForward(1, 1000);
            goRight(1, 500);
            goBackwards(1, 1000);*/



        goForward(1, 800);
        goRight(1, 700);
        goForward(1, 800);
        stopRobot();






//        goForward(1, 200);
//        goRight(1, 455);
//        goForward(1, 1500);
//        stopRobot();

//            goForward(1, 200);
//            goRight(1, 500);
//            goForward(1, 1300);
//            stopRobot();
            /*goForward(1, 1350);
            goRight(1, 520);
            goForward(1, 100);
            stopRobot();
            catchCube();*/

            /*goRight(1, 500);
            goForward(1, 400);
            goLeft(1, 500);
            goForward(1, 1000);
            goLeft(1, 500);
            goForward(0.25f, 2);
            goForward(0, 0);
            catchCube();
            goLeft(1, 500);
            goForward(1, 850);
            goLeft(1, 500);
            goForward(1, 2000);
            goForward(0, 0);
            */
            /*goLeft(1, 540);
            goForward(1, 750);
            goLeft(1, 270);
            goForward(1, 150);
            goLeft(1, 250);
            goForward(0.5f, 100);
            */
            //endregion

            //region Telemetrie
            //Viteza robotului
            telemetry.addData("Viteza robotului", 10*(1 - driveSpeed_x + driveSpeed_y));

            //Pozitia bratului
            telemetry.addData("Pozitia bratului", 1 + armPos);

            //Pozitia ghearei
            telemetry.addData("Pozitia ghearei", clawPos);

            //Update
            telemetry.update();
            //endregion
//            //region Seteaza curentul la 0 la motoare
//            //Pt. roti
//            H1Motor0_Drive.setPower(0);
//            H1Motor1_Drive.setPower(0);
//
//            //Pt. brat
//            H2Motor0_ArmString.setPower(0);
//            //endregion

//            //Activat servouri
//            H2Servo0_ArmBase.setPosition(0.4299995f);
//
//            H2Servo1_ClawL.setPosition(0);
//            H2Servo2_ClawR.setPosition(1 - 0);
//
//            //Activat motoare
//            H1Motor0_Drive.setPower(1);
//            H1Motor1_Drive.setPower(-1);
//            sleep(1000);
//
//            H1Motor0_Drive.setPower(-1);
//            H1Motor1_Drive.setPower(-1);
//            sleep(100);
//
//            H1Motor0_Drive.setPower(1);
//            H1Motor1_Drive.setPower(-1);
//            sleep(1000);
//
//            H1Motor0_Drive.setPower(1);
//            H1Motor1_Drive.setPower(1);
//            sleep(100);
//
//            H1Motor0_Drive.setPower(1);
//            H1Motor1_Drive.setPower(-1);

        }
    //}
    //region nuj
    /**
     * A simple utility that extracts positioning information from a transformation matrix
     * and formats it in a form palatable to a human being.
     */
    String format(OpenGLMatrix transformationMatrix) {
        return transformationMatrix.formatAsTransform();
    }
    //endregion
}