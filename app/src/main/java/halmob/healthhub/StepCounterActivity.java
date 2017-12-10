package halmob.healthhub;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StepCounterActivity extends BaseActivity implements SensorEventListener,View.OnClickListener {

    private SensorManager sensorManager;
    private Button startButton;
    private TextView count;
    boolean activityRunning;
    private Sensor countSensor;
    private float lastValue;
    private SharedPreferences prefs;
    private static String lastValueKey = "com.example.app.step";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter_second);
        prefs = this.getSharedPreferences(
                "halmob.healthhub", Context.MODE_PRIVATE);
        lastValue = prefs.getFloat(lastValueKey, 0);
        count = (TextView) findViewById(R.id.count);
        startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        count.setText("0");
        activityRunning=false;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_button:
                if(activityRunning==false) {
                    activityRunning = true;
                    countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
                    if (countSensor != null) {
                        sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
                    } else {
                        Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
                    }
                    startButton.setText("Stop Counter");
                }else{
                    Toast.makeText(this, "Congratulations! "+count.getText()+" steps", Toast.LENGTH_LONG).show();
                    stop();
                }
                break;
        }
    }
    public void stop(){
        activityRunning = false;
        startButton.setText("Start Counter");
        lastValue=lastValue+Float.valueOf(count.getText().toString());
        count.setText("0");

    }

    @Override
    protected void onResume() {
        super.onResume();
        lastValue = prefs.getFloat(lastValueKey, 0);

    }

    @Override
    protected void onPause() {
        super.onPause();
        stop();
        prefs.edit().putFloat(lastValueKey, lastValue).apply();
        // if you unregister the last listener, the hardware will stop detecting step events
//        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (activityRunning) {
            count.setText(String.valueOf(event.values[0]-lastValue));
        }else{
            lastValue=event.values[0];
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stop();
        prefs.edit().putFloat(lastValueKey, lastValue).apply();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        stop();
        prefs.edit().putFloat(lastValueKey, lastValue).apply();
    }
}
