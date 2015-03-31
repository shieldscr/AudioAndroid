package com.shields.audioandroid;

import android.content.Context;
import android.media.MediaRecorder;
import android.widget.Toast;

import com.shields.R;

import javax.inject.Inject;

public class AudioIOUtility implements AudioIOUtilityInterface {

    @Inject
    MediaRecorder mediaRecorder;

    public AudioIOUtility() {
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    }

    public AudioIOUtility(MediaRecorder mediaRecorder) {
        this.mediaRecorder = mediaRecorder;
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
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
