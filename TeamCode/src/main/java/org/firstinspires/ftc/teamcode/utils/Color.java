package org.firstinspires.ftc.teamcode.utils;

public class Color
{
    public float red, green, blue, alpha, argb;

    public Color(float red, float green, float blue, float alpha)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public boolean isYellow()
    {
        //red > green && red > blue && (red + green) /  2 > blue
        return (red + green) / 2 > blue * 2 && red > green;
    }
}

