package com.shields.audioandroid;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.widget.Toast;

import com.shields.R;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

public class AudioIOUtility implements AudioIOUtilityInterface {

    private boolean isRecording = false;

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    @Inject
    public AudioIOUtility(MediaRecorder mediaRecorder, MediaPlayer mediaPlayer) {
        this.mediaRecorder = mediaRecorder;
        this.mediaPlayer = mediaPlayer;
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
    }

    @Override
    public void startRecording(Context context) {
        if (!isRecording) {
            initializeAndStartMediaRecorder(context);
        }
    }

    @Override
    public void stopRecording(Context context) {
        mediaRecorder.stop();
        mediaRecorder.release();
        isRecording = false;

        Toast.makeText(context, R.string.stopRecordingToast, Toast.LENGTH_LONG).show();
    }

    @Override
    public void play(Context context) {
        File cacheDir = context.getCacheDir();
        File outputFile = new File(cacheDir.getPath() + "/" + "temp_audio_recording");
        try {
            mediaPlayer.setDataSource(outputFile.toString());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, R.string.playToast, Toast.LENGTH_LONG).show();
    }

    private void initializeAndStartMediaRecorder(Context context) {
        File cacheDir = context.getCacheDir();
        File outputFile = new File(cacheDir.getPath() + "/" + "temp_audio_recording");
        mediaRecorder.setOutputFile(outputFile.toString());
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
        isRecording = true;
    }
}
