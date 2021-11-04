package com.example.hypointervention;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    public static final int PERMISSION_REQUEST = 1;

    Button voice_button;
    Button standard_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        if (!checkAudioPermission() | !checkWritePermission()) {
            requestPermission();
        }

        voice_button =  findViewById(R.id.voice_btn);
        voice_button.setOnClickListener(v -> launchVoiceActivity());

        standard_button = findViewById(R.id.standard_btn);
        standard_button.setOnClickListener(v -> launchStandardActivity());
    }

    private void launchVoiceActivity() {
        if (checkAudioPermission()) {
            Intent intent = new Intent(FullscreenActivity.this,
                    FullscreenActivity_voice.class);
            startActivity(intent);
        } else {
            requestPermission();
          }
    }

    private void launchStandardActivity() {
        if (checkAudioPermission()) {
            Intent intent = new Intent(FullscreenActivity.this,
                    FullscreenActivity_standard.class);
            startActivity(intent);
        } else {
            requestPermission();
        }
    }

    private boolean checkAudioPermission() {
        return ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkWritePermission() {
        return ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PERMISSION_REQUEST);
    }
}