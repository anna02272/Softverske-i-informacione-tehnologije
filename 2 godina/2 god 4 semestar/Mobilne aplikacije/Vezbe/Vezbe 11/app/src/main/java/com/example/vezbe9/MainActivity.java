package com.example.vezbe11;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;

    TextView tvAccelerometer;
    TextView tvLinearAccelerometer;
    TextView tvMagneticField;
    TextView tvProximitySensor;
    TextView tvGyroscope;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        tvAccelerometer = (TextView) findViewById(R.id.tvAccelerometar);
        tvLinearAccelerometer = (TextView) findViewById(R.id.tvLinearAccelerometer);
        tvMagneticField = (TextView) findViewById(R.id.tvMagneticField);
        tvProximitySensor = (TextView) findViewById(R.id.tvProximitySensor);
        tvGyroscope = (TextView) findViewById(R.id.tvGyroscope);
    }

    /*
     * Bitno je da zakacimo/otkacimo listener na pravom mestu. Kada zelmo da koristimo
     * odredjeni senzor, najbolje mesto da se listener zakaci, da bi dobijali merenja,
     * jeste metoda onResume.
     * */
    @Override
    protected void onResume() {
        super.onResume();
        
	List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor s : sensors){
            Log.i("REZ_TYPE_ALL", s.getName());
        }
        
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                SensorManager.SENSOR_DELAY_NORMAL);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);

        Sensor proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (proximitySensor == null) {
            Toast.makeText(this, "No proximity sensor found in device.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // registering our sensor with sensor manager
            sensorManager.registerListener(this,
                    proximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /*
     * Najbolje mesto da otkacimo listener jeste onPause. Voditi racuna
     * da se svi listener-i koji rade sa senzorima otkace, kada
     * vise ne zelimo da ih koristimo.
     * */
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    private static final int SHAKE_THRESHOLD = 800;
    private long lastUpdate;
    private float last_x;
    private float last_y;
    private float last_z;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float[] values = sensorEvent.values;
                float x = values[0];
                float y = values[1];
                float z = values[2];

                float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    Log.d("REZ", "shake detected w/ speed: " + speed);
                    tvAccelerometer.setText("Accelerometer: shaking \n [" + x + ", " + y + ", " + z + "]");
                }else{
                    tvAccelerometer.setText("Accelerometer: not shaking \n [" + x + ", " + y + ", " + z + "]");
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }

        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            float[] values = sensorEvent.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            tvLinearAccelerometer.setText("Non-gravity accelerometer: [" + x + ", " + y + ", " + z + "]");
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float[] values = sensorEvent.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            tvMagneticField.setText("Magnetic field: [" + x + ", " + y + ", " + z + "]");
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float[] values = sensorEvent.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            tvGyroscope.setText("Gyroscpe : [" + x + ", " + y + ", " + z + "]");
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float[] values = sensorEvent.values;
            float x = values[0];
            if (x == 0) {
                // here we are setting our status to our textview..
                // if sensor event return 0 then object is closed
                // to sensor else object is away from sensor.
                tvProximitySensor.setText("Proximity sensor: Blizu");
            } else {
                tvProximitySensor.setText("Proximity sensor: Daleko");
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.i("REZ_ACCELEROMETER", String.valueOf(accuracy));
        }else if(sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
            Log.i("REZ_LINEAR_ACCELERATION", String.valueOf(accuracy));
        }else if(sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            Log.i("REZ_MAGNETIC_FIELD", String.valueOf(accuracy));
        }else if(sensor.getType() == Sensor.TYPE_GYROSCOPE){
            Log.i("REZ_GYROSCOPE", String.valueOf(accuracy));
        }else if(sensor.getType() == Sensor.TYPE_PROXIMITY){
            Log.i("REZ_TYPE_PROXIMITY", String.valueOf(accuracy));
        }else{
            Log.i("REZ_OTHER_SENSOR", String.valueOf(accuracy));
        }

    }
}
