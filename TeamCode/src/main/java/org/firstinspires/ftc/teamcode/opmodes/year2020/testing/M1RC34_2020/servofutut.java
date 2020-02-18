package org.firstinspires.ftc.teamcode.opmodes.year2020.testing.M1RC34_2020;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Tservofutut", group="servofutut")
public class servofutut extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {

        //region Declara variabilele actualizate numai odata
        float
                //Pt. pozitia ghearei
                clawInitPos=0, clawMaxPos=0.5899999f, clawMinPos=0,
                clawPos = clawInitPos,

                //Pt. pozitia bratului
                armInitPos=0.4299995f, armMaxPos=0.547998f, armMinPos=0.4299995f,
                armPos = armInitPos,

                //Pt. pozitia tavii
                plateInitPos = 0, plateMaxPos=0.6f, plateMinPos=0,
                platePos = plateInitPos
                        ;

        //Pt. tava
        Servo H2Servo3_PlateL = hardwareMap.get(Servo.class, "H2Servo3_PlateL");
        Servo H2Servo4_PlateR = hardwareMap.get(Servo.class, "H2Servo4_PlateR");
        //endregion

        while (true)
        {
            //region Pt. tava
            if (gamepad2.right_stick_y > 0)
            {
                platePos = platePos > plateMaxPos ? plateMaxPos : platePos + 0.01f;
            }
            else
            if (gamepad2.right_stick_y < 0)
            {
                platePos = platePos < plateMinPos ? plateMinPos : platePos - 0.01f;
            }

            H2Servo4_PlateR.setPosition(platePos);
            H2Servo3_PlateL.setPosition(1-platePos);
            //endregion
            //endregion

            //region Butoane de oprire
            //Oprirea miscarii bratului
            if(gamepad2.b)
            {
                //Pt. brat
                armPos = armInitPos;

                //Pt. gheara
                clawPos = clawInitPos;

                //Pt. tava
                platePos = plateInitPos;
            }



            //region Telemetrie
            //Pozitia bratului
            telemetry.addData("Pozitia bratului", 1+armPos);

            //Pozitia ghearei
            telemetry.addData("Pozitia ghearei", clawPos);

            //Pozitia tavii
            telemetry.addData("Pozitia tavii", platePos);

            //Update
            telemetry.update();
            //endregion
        }
    }
}
