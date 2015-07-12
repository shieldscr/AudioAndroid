package com.shields.audioandroid.modules;

import android.media.AudioFormat;
import android.media.MediaPlayer;
import android.media.MediaRecorder;

import com.shields.audioandroid.AudioIOUtility;
import com.shields.audioandroid.AudioIOUtilityInterface;
import com.shields.audioandroid.MainActivity;
import com.shields.audioandroid.RehearsalAudioRecorder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
        injects = { MainActivity.class, AudioIOUtility.class }
)
public class AudioIOUtilityModule {
    @Provides
    @Singleton
    public AudioIOUtilityInterface provideAudioIOUtility(AudioIOUtility audioIOUtility) {
        return audioIOUtility;
    }

    @Provides
    @Singleton
    public RehearsalAudioRecorder provideMediaRecorder() {
        return new RehearsalAudioRecorder(RehearsalAudioRecorder.RECORDING_UNCOMPRESSED, MediaRecorder.AudioSource.MIC,
                44100, AudioFormat.CHANNEL_CONFIGURATION_STEREO, AudioFormat.ENCODING_PCM_16BIT);
    }

    @Provides
    @Singleton
    public MediaPlayer provideMediaPlayer() {
        return new MediaPlayer();
    }

}
