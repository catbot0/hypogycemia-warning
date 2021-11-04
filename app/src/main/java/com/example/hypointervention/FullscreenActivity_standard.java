package com.example.hypointervention;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity_standard extends AppCompatActivity {

    private AudioPlayer mAudioPlayer;

    private Logger Logger;

    private static final String TAG = "Standard";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_standard);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_standard);

        mAudioPlayer = new AudioPlayer();

        Logger = new Logger();

    }

    @Override
    protected void onStart() {
        super.onStart();

        Instant instant = Instant.now();



        String timestamp;
        timestamp = DateTimeFormatter.ISO_INSTANT.format(instant);

        startPlayingAudio(R.raw.beep);

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
        mAudioPlayer.play(this, resId, () -> {
        });
    }

    private void stopPlayingAudio() {
        if (mAudioPlayer != null)
            mAudioPlayer.stop();

    }

}