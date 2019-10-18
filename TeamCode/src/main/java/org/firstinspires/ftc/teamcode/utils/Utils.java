package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import java.util.List;

public class Utils
{
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static VuforiaLocalizer vuforia;
    private static TFObjectDetector tfod;

    public static void deinnit()
    {
        //deactivate tfod
        if(tfod != null)
        {
            tfod.deactivate();
        }
    }

    public static void init(HardwareMap map)
    {
        //init vuforia
        VuforiaLocalizer.Parameters p = new VuforiaLocalizer.Parameters();
        p.vuforiaLicenseKey = Defines.vuforiaKey;
        p.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.getInstance().createVuforia(p);
        //init tfod
        tfod = null;
        if(ClassFactory.getInstance().canCreateTFObjectDetector())
        {
            int tfodMonitorViewId = map.appContext.getResources().getIdentifier("tfodMonitorViewId", "id",
                    map.appContext.getPackageName());
            TFObjectDetector.Parameters tfodp = new TFObjectDetector.Parameters(tfodMonitorViewId);
            tfod = ClassFactory.getInstance().createTFObjectDetector(tfodp, vuforia);
            tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
        }
        //activate tfod
        if(tfod != null)
            tfod.activate();
    }

    public static int getGoldLocation()
    {
        //recognize
        float gold1 = -1, white1 = -1, silver1 = -1;
        List<Recognition> r = tfod.getRecognitions();
        if(r != null)
        {
            //find the gold that should be sampled
            float gold = -1;
            float silver = -1;
            float white = -1;
            for(Recognition rec : r)
            {
                if(rec.getLabel().equals(LABEL_GOLD_MINERAL))
                {
                    if(gold < rec.getWidth())
                    {
                        gold = rec.getWidth();
                        gold1 = rec.getLeft();
                    }
                }
                else if(rec.getLabel().equals(LABEL_SILVER_MINERAL))
                {
                    if(silver < rec.getWidth())
                    {
                        if(silver > white)
                        {
                            white = silver;
                            white1 = silver1;
                        }
                        silver = rec.getWidth();
                        silver1 = rec.getLeft();
                    }
                    else if(white < rec.getWidth())
                    {
                        white = rec.getWidth();
                        white1 = rec.getLeft();
                    }
                }
            }
        }
        //tell relative position of cube and return it
        if(gold1 < silver1 && gold1 < white1)
            return Defines.MINERAL_LOCATION_LEFT;
            //return Defines.MINERAL_LOCATION_RIGHT;
        else if(gold1 > silver1 && gold1 < white1)
            return Defines.MINERAL_LOCATION_CENTER;
        else if(gold1 > white1 && gold1 < silver1)
            return Defines.MINERAL_LOCATION_CENTER;
        else if(gold1 > silver1 && gold1 > white1)
            return Defines.MINERAL_LOCATION_RIGHT;
            //return Defines.MINERAL_LOCATION_LEFT;

        return -1;
    }

    public static double Clamp(double value, double min, double max)
    {
        if(value < min)
            return min;
        else if(value > max)
            return max;
        return value;
    }
}

