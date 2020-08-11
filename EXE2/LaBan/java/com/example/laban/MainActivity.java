package com.example.laban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView compassImage;


    public float initialDegree = 0f;
    private SensorManager manager;

    TextView infoTextAboutDegrees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBtn = (Button) findViewById(R.id.makeCall);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               finish();
            }
        });
        compassImage = (ImageView) findViewById(R.id.imageViewCompass);

        infoTextAboutDegrees = (TextView) findViewById(R.id.tvHeading);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }




    @Override
    protected void onResume() {
        super.onResume();

        manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        manager.unregisterListener(this);
    }

    public void emergencyCall() {

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:911"));

        try {
            startActivity(phoneIntent);
            finish();
            Log.i("Call success...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "Call fail", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        float currentDegree = Math.round(event.values[0]);

        infoTextAboutDegrees.setText("Current degrees: " + Float.toString(currentDegree));

        RotateAnimation rotateAnimation = new RotateAnimation(
                initialDegree,
                -currentDegree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        rotateAnimation.setDuration(210);

        rotateAnimation.setFillAfter(true);

        compassImage.startAnimation(rotateAnimation);
        initialDegree = -currentDegree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}