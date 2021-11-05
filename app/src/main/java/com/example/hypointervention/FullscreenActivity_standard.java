package com.example.hypointervention;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity_standard extends AppCompatActivity {

    private AudioPlayer mAudioPlayer;

    private Logger Logger;

    private static final String TAG = "Standard";

    static boolean standard_activityVisible = false;


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

        standard_activityVisible = true;

        Timer start = new Timer();
        start.schedule(new TimerTask() {

                           public void run() {
                               if(standard_activityVisible) {
                                   Log.w(TAG, "intervention about to start");
                                   Instant instant = Instant.now();

                                   String timestamp;
                                   timestamp = DateTimeFormatter.ISO_INSTANT.format(instant);

                                   startPlayingBeep(R.raw.beep);

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
        stopPlayingBeep();
        standard_activityVisible = false;

    }

    private void startPlayingBeep(int resId) {
        mAudioPlayer.play(this, resId, () -> {

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                public void run() {
                    if(standard_activityVisible) {
                        launchMainActivity();
                    }
                }

            }, 15000);
        });

    }

    private void stopPlayingBeep() {
        if (mAudioPlayer != null)
            mAudioPlayer.stop();
    }

    private void launchMainActivity() {
        Intent intent = new Intent(FullscreenActivity_standard.this,
                FullscreenActivity.class);
        startActivity(intent);
    }
}