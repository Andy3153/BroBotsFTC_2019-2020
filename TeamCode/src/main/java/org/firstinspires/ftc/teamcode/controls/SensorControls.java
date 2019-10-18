package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.utils.Color;
import org.firstinspires.ftc.teamcode.utils.Defines;

import java.io.File;

public class SensorControls
{
    private DistanceSensor distanceSensor;
    private ColorSensor colorSensor;
    private BNO055IMU imuSensor;

    public SensorControls(HardwareMap hardwareMap)
    {
        distanceSensor = hardwareMap.get(DistanceSensor.class, Defines.SENSOR_DISTANCE);
        colorSensor = hardwareMap.get(ColorSensor.class, Defines.SENSOR_COLOR);
        imuSensor = hardwareMap.get(BNO055IMU.class, Defines.SENSOR_IMU);
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        imuSensor.initialize(parameters);
        calibrateIMU(Defines.IMU_CALIBRATION_HOME);
        while(!getGyroCalibration());
    }

    public float getAngle()
    {
        return 0;
        //return imuSensor.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
    }

    private void calibrateIMU()
    {
        return;
        /*while(!imuSensor.isGyroCalibrated())
        {

        }*/
    }

    private void calibrateIMU(String filename)
    {
        //File file = AppUtil.getInstance().getSettingsFile(filename);
        //BNO055IMU.CalibrationData data = BNO055IMU.CalibrationData.deserialize(ReadWriteFile.readFile(file));
        //imuSensor.writeCalibrationData(data);
    }

    public BNO055IMU.CalibrationData getIMUCalibrationData()
    {
        return imuSensor.readCalibrationData();
    }

    public boolean getGyroCalibration()
    {
        return imuSensor.isGyroCalibrated();
    }

    public void disableLED()
    {
        colorSensor.enableLed(false);
    }

    public void enableLED()
    {
        colorSensor.enableLed(true);
    }

    public double getDistance()
    {
        return distanceSensor.getDistance(DistanceUnit.CM);
    }

    public int getAlpha()
    {
        return colorSensor.alpha();
    }

    public int getBlue()
    {
        return colorSensor.blue();
    }

    public int getGreen()
    {
        return colorSensor.green();
    }

    public int getRed()
    {
        return colorSensor.red();
    }

    public Color getColor()
    {
        int red = colorSensor.red();
        int green = colorSensor.green();
        int blue = colorSensor.blue();
        int alpha = colorSensor.alpha();
        float max = Math.max(Math.max(Math.max(red, green), blue), alpha);
        return new Color(red / max, green / max, blue / max, alpha);
    }
}

