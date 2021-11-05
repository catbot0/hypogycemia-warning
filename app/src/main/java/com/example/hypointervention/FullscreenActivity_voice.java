package com.example.hypointervention;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;


public class FullscreenActivity_voice extends AppCompatActivity {

    private BlobVisualizer mVisualizer;

    private AudioPlayer mAudioPlayer;

    private Logger Logger;

    private static final String TAG = "Voice";

    static boolean voice_activityVisible = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_voice);

        mVisualizer = findViewById(R.id.blob);


        mAudioPlayer = new AudioPlayer();

        Logger = new Logger();

    }

    @Override
    protected void onStart() {
        super.onStart();

        Instant instant = Instant.now();

        voice_activityVisible = true;

        String timestamp;
        timestamp = DateTimeFormatter.ISO_INSTANT.format(instant);

        startPlayingVoice(R.raw.voice);

        String log;
        log = timestamp + ";" + TAG;

        Log.i(TAG, log);
        if(log == null) {
            Log.e("Exception", "Problem with 'log' object ");
        }else {
            Log.i(TAG, "'log' object is non-null");
            Logger.appendLog(log);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayingVoice();
        voice_activityVisible = false;
    }

    private void startPlayingVoice(int resId) {
        mAudioPlayer.play(this, resId, () -> {
            if (mVisualizer != null)
                mVisualizer.hide();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                public void run() {
                    if(voice_activityVisible) {
                        launchMainActivity();
                    }
                }

            }, 15000);
        });
        int audioSessionId = mAudioPlayer.getAudioSessionId();
        if (audioSessionId != -1)
            mVisualizer.setAudioSessionId(audioSessionId);
    }

    private void stopPlayingVoice() {
        if (mAudioPlayer != null)
            mAudioPlayer.stop();
        if (mVisualizer != null)
            mVisualizer.release();
    }

    private void launchMainActivity() {
        Intent intent = new Intent(FullscreenActivity_voice.this,
                    FullscreenActivity.class);
        startActivity(intent);
    }
}