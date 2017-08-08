package net.kiborgov.Moverio.BT200;

/**
 * Created by Yana Artishcheva on 08.08.2017.
 */

import jp.epson.moverio.bt200.SensorControl;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.app.Activity;
import java.util.ArrayList;

public class AccelGyroCompass implements SensorEventListener {
    private SensorControl sensorControl;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyro;
//	private Sensor orientation;

    private boolean isAccMeasured = false;
    private double prevAccXData = 0;
    private double accXData = 0;
    private double prevAccYData = 0;
    private double accYData = 0;
    private double prevAccZData = 0;
    private double accZData = 0;

    private boolean isGyroMeasured = false;
    private double prevGyroXData = 0;
    protected double gyroXData = 0;
    private double prevGyroYData = 0;
    protected double gyroYData = 0;
    private double prevGyroZData = 0;
    protected double gyroZData = 0;

    private boolean isUserAxisMeasured = false;
    private double userForward = 0;
    private double userUp = 0;
    private double userRight = 0;

    private int forwardIs = AccelGyroCompassEvent.UNDEFINED;
    private int upIs = AccelGyroCompassEvent.UNDEFINED;
    private int rightIs = AccelGyroCompassEvent.UNDEFINED;

    private boolean flagOrientationSwitch = false;

    private static ArrayList<IAccelGyroCompassListener> listeners = new ArrayList<IAccelGyroCompassListener>();

    public AccelGyroCompass (Activity activity, int deviceType, boolean flagOrientationSwitch) {
        sensorManager = (SensorManager)(activity.getSystemService(Activity.SENSOR_SERVICE));

//        gyro = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//		sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_NORMAL);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//		sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);

        if (deviceType == DeviceType.MOVERIO_BT_200) {
            if (sensorControl == null) {
                sensorControl = new SensorControl(activity);
                sensorControl.setMode(SensorControl.SENSOR_MODE_HEADSET);
            }
        }
    }

    @Override
    public void onSensorChanged (SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                prevAccXData = accXData;
                prevAccYData = accYData;
                prevAccZData = accZData;
                accXData = event.values[0];
                accYData = event.values[1];
                accZData = event.values[2];
                isAccMeasured = true;
                AccelGyroCompassEvent e = new AccelGyroCompassEvent(this
                        , isAccMeasured, accXData, accYData, accZData
                        , isGyroMeasured, gyroXData, gyroYData, gyroZData
                        , isUserAxisMeasured
                        , userRight, userUp, userForward
                        , rightIs, upIs, forwardIs
                );
                fireOnAccelGyroCompass(e);
                break;
		case Sensor.TYPE_GYROSCOPE:
//		case Sensor.TYPE_ORIENTATION:
			prevGyroXData = gyroXData;
			prevGyroYData = gyroYData;
			prevGyroZData = gyroZData;
			gyroXData = event.values[0];
			gyroYData = event.values[1];
			gyroZData = event.values[2];
			isGyroMeasured = true;
			break;
        }
    }

    @Override
    public void onAccuracyChanged (Sensor p1, int p2) {}

    public void addAccelGyroCompassListener (IAccelGyroCompassListener listener) {
        listeners.add(listener);
    }

    public void removeAccelGyroCompassListener (IAccelGyroCompassListener listener) {
        listeners.remove(listener);
    }

    public void fireOnAccelGyroCompass (AccelGyroCompassEvent event) {
        for (IAccelGyroCompassListener listener:listeners) {
            listener.onAccelGyroCompassEvent(event);
        }
    }
}
