package com.example.stepcount_drachev;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    public boolean active = true;
    private SensorManager sensorManager;
    private int count = 0;
    private TextView text;
    private long lastUpdate;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text = findViewById(R.id.textView2);
        text.setText(String.valueOf(count));
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorManager.registerListener((SensorEventListener) this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        lastUpdate = System.currentTimeMillis();
    }


    @Override
    protected  void onResume(){
        super.onResume();

        sensorManager.registerListener(
                (SensorEventListener) this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected  void onPause(){
        super.onPause();

        sensorManager.unregisterListener((SensorEventListener) this);
    }

    public  void OnStoped(View view)
    {

        active = !active;
        if(!active){
            Button button = findViewById(R.id.button);
            button.setText("ВОЗОБНОВИТЬ");
        }else{
            Button button = findViewById(R.id.button);
            button.setText("ПАУЗА");
        }
    }
}