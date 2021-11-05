package com.example.hypointervention;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;


public class FullscreenActivity_voice_LED extends AppCompatActivity {

    private BlobVisualizer mVisualizer;

    private AudioPlayer mAudioPlayer;

    private Logger Logger;

    private static final String TAG = "Voice + LED";

    static boolean voice_led_activityVisible = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_voice_led);

        mVisualizer = findViewById(R.id.blob);


        mAudioPlayer = new AudioPlayer();

        Logger = new Logger();

    }

    @Override
    protected void onStart() {
        super.onStart();

        voice_led_activityVisible = true;

        Timer start = new Timer();
        start.schedule(new TimerTask() {

                           public void run() {
                               if(voice_led_activityVisible) {
                                   Log.w(TAG, "intervention about to start");
                                   Instant instant = Instant.now();

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

                                   Log.w(TAG, "intervention played");
                               }
                           }

                       },
                2000);

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayingVoice();
        voice_led_activityVisible = false;
    }

    private void startPlayingVoice(int resId) {
        mAudioPlayer.play(this, resId, () -> {
            if (mVisualizer != null)
                mVisualizer.hide();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                public void run() {
                    if(voice_led_activityVisible) {
                        launchMainActivity();
                    }
                }

            },
                    15000);
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
        Intent intent = new Intent(FullscreenActivity_voice_LED.this,
                    FullscreenActivity.class);
        startActivity(intent);
    }
}