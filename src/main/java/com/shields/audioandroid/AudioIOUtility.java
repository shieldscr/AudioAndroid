package com.shields.audioandroid;

import android.content.Context;
import android.widget.Toast;

import com.shields.R;

public class AudioIOUtility implements AudioIOUtilityInterface {
    public AudioIOUtility() {
    }

    @Override
    public void startRecording(Context context) {
        Toast.makeText(context, R.string.recordingToast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void stopRecording(Context context) {
        Toast.makeText(context, R.string.stopRecordingToast, Toast.LENGTH_LONG).show();
    }

    @Override
    public void play(Context context) {
        Toast.makeText(context, R.string.playToast, Toast.LENGTH_LONG).show();
    }
}
