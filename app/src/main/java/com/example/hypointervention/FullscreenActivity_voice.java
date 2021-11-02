package com.example.hypointervention;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class FullscreenActivity_voice extends AppCompatActivity {

    private BlobVisualizer mVisualizer;

    private AudioPlayer mAudioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_voice);

        mVisualizer = findViewById(R.id.blob);

        mAudioPlayer = new AudioPlayer();
    }


    @Override
    protected void onStart() {
        super.onStart();
        startPlayingAudio(R.raw.voice);
        // TODO: log timestamp
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