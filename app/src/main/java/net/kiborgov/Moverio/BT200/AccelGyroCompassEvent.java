package net.kiborgov.Moverio.BT200;

/**
 * Created by Yana Artishcheva on 08.08.2017.
 */

import java.util.EventObject;

public class AccelGyroCompassEvent extends EventObject {
    public boolean isAccMeasured = false;
    public double accXData = 0;
    public double accYData = 0;
    public double accZData = 0;

    public boolean isGyroMeasured = false;
    public double gyroXData = 0;
    public double gyroYData = 0;
    public double gyroZData = 0;

    public boolean isUserAxisMeasured = false;
    public double userForward = 0;
    public double userUp = 0;
    public double userRight = 0;
    public int forwardIs = FORWARD;
    public int upIs = UP;
    public int rightIs = RIGHT;

    public static final int UNDEFINED = 0;
    public static final int FORWARD = 1;
    public static final int BACKWARD = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;
    public static final int LEFT = 5;
    public static final int RIGHT = 6;

    public AccelGyroCompassEvent (Object source
            , boolean isAccMeasured, double accXData, double accYData, double accZData
            , boolean isGyroMeasured, double gyroXData, double gyroYData, double gyroZData
            , boolean isUserAxisMeasured, double userRight, double userUp, double userForward
            , int rightIs, int upIs, int forwardIs
    ) {
        super(source);
        this.isAccMeasured = isAccMeasured;
        this.accXData = accXData;
        this.accYData = accYData;
        this.accZData = accZData;
        this.isGyroMeasured = isGyroMeasured;
        this.gyroXData = gyroXData;
        this.gyroYData = gyroYData;
        this.gyroZData =  gyroZData;
        this.isUserAxisMeasured = isUserAxisMeasured;
        this.userRight = userRight;
        this.userUp = userUp;
        this.userForward = userForward;
        this.rightIs = rightIs;
        this.upIs = upIs;
        this.forwardIs = forwardIs;
    }
}
