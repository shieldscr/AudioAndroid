package com.shields.audioandroid;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

public class AudioIOUtility implements AudioIOUtilityInterface {

    private boolean isRecording = false;

    RehearsalAudioRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    @Inject
    public AudioIOUtility(RehearsalAudioRecorder mediaRecorder, MediaPlayer mediaPlayer) {
        this.mediaRecorder = mediaRecorder;
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void startRecording(Context context, Integer loopCount) {
        if (!isRecording) {
            initializeAndStartMediaRecorder(context);
        }
    }

    @Override
    public void stopRecording(Context context) {
        mediaRecorder.stop();
        mediaRecorder.release();
        isRecording = false;
    }

    @Override
    public void play(Context context) {
        File cacheDir = context.getCacheDir();
        File outputFile = new File(cacheDir.getPath() + "/" + "temp_audio_recording");
        try {
            mediaPlayer.setDataSource(outputFile.toString());
            mediaPlayer.setLooping(true);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopPlaying(Context context) {
        mediaPlayer.setLooping(false);
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    private void initializeAndStartMediaRecorder(Context context) {
        MediaRecordTask mediaRecordTask = new MediaRecordTask();

        File cacheDir = context.getCacheDir();
        File outputFile = new File(cacheDir.getPath() + "/" + "temp_audio_recording");
        outputFile.deleteOnExit();
        mediaRecorder.setOutputFile(outputFile.toString());

        mediaRecordTask.doInBackground();

        isRecording = true;
    }

    private class MediaRecordTask extends AsyncTask<MediaRecorder, Integer, Integer> {

        @Override
        protected Integer doInBackground(MediaRecorder... mediaRecorders) {
            mediaRecorder.prepare();
            mediaRecorder.start();
            return null;
        }
    }
}
