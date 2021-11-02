package com.example.hypointervention;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity_standard extends AppCompatActivity {

    private AudioPlayer mAudioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_standard);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_standard);

        mAudioPlayer = new AudioPlayer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startPlayingAudio(R.raw.beep);
        // TODO: log timestamp
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