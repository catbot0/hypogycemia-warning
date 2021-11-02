package com.example.hypointervention;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    //private static final int PERM_REQ_CODE = 23;
    Button voice_button;
    Button standard_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        voice_button =  findViewById(R.id.voice_btn);
        voice_button.setOnClickListener(v -> launchVoiceActivity());

        standard_button = findViewById(R.id.standard_btn);
        standard_button.setOnClickListener(v -> launchStandardActivity());
    }

    /*private boolean checkAudioPermission() {
        return ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestAudioPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO}, PERM_REQ_CODE);
    }*/

    private void launchVoiceActivity() {
        Intent intent = new Intent(FullscreenActivity.this,
                FullscreenActivity_voice.class);
        startActivity(intent);
    }

    private void launchStandardActivity() {
        Intent intent = new Intent(FullscreenActivity.this,
                FullscreenActivity_standard.class);
        startActivity(intent);
    }
}