package com.example.iskander2.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public SensorManager sensorManager;
    public Sensor accelerometr;

    private TextView xyView;
    private TextView xzView;
    private TextView zyView;

    private float xy_angle;
    private float xz_angle;
    private float zy_angle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometr = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        xyView = (TextView) findViewById(R.id.xyValue);  //
        xzView = (TextView) findViewById(R.id.xzValue);  // Наши текстовые поля для вывода показаний
        zyView = (TextView) findViewById(R.id.zyValue);  //

    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorEventListener _SensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                xy_angle = sensorEvent.values[0]; //Плоскость XY
                xz_angle = sensorEvent.values[1]; //Плоскость XZ
                zy_angle = sensorEvent.values[2]; //Плоскость ZY

                xyView.setText(String.valueOf(xy_angle));
                xzView.setText(String.valueOf(xz_angle));
                zyView.setText(String.valueOf(zy_angle));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(_SensorEventListener, accelerometr, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
