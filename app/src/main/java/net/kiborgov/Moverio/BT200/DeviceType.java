package net.kiborgov.Moverio.BT200;

/**
 * Created by Yana Artishcheva on 08.08.2017.
 */

public class DeviceType {
    public static final int UNDEFINED = -1;
    public static final int STANDARD_ANDROID = 0;
    public static final int CARDBOARD = 1;
    public static final int MOVERIO_BT_100 = 2;
    public static final int MOVERIO_BT_200 = 3;

    private static int devType = UNDEFINED;

    public static final int getDeviceType () {
        if (devType != UNDEFINED) {
            return devType;
        }
        if (android.os.Build.MANUFACTURER.equals("EPSON")) {
            if (android.os.Build.MODEL.equals("embt2")) {
                return devType = MOVERIO_BT_200;
            } else if (android.os.Build.MODEL.equals("embt1")) {
                return devType = MOVERIO_BT_100;
            }
        }
        return devType = STANDARD_ANDROID;
    }
}
