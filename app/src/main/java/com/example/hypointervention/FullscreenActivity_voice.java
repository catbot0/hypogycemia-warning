package com.example.hypointervention;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.time.Instant;
import java.time.format.DateTimeFormatter;


public class FullscreenActivity_voice extends AppCompatActivity {

    private BlobVisualizer mVisualizer;

    private AudioPlayer mAudioPlayer;

    private Logger Logger;

    private static final String TAG = "Voice";

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



        String timestamp;
        timestamp = DateTimeFormatter.ISO_INSTANT.format(instant);

        startPlayingAudio(R.raw.voice);

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
        stopPlayingAudio();
    }

    private void startPlayingAudio(int resId) {
        mAudioPlayer.play(this, resId, new AudioPlayer.AudioPlayerEvent() {
            @Override
            public void onCompleted() {
                if (mVisualizer != null)
                    mVisualizer.hide();
            }
        });
        int audioSessionId = mAudioPlayer.getAudioSessionId();
        if (audioSessionId != -1)
            mVisualizer.setAudioSessionId(audioSessionId);
    }

    private void stopPlayingAudio() {
        if (mAudioPlayer != null)
            mAudioPlayer.stop();
        if (mVisualizer != null)
            mVisualizer.release();
    }
}