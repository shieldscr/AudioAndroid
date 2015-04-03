package com.shields.audioandroid.modules;

import android.media.MediaPlayer;
import android.media.MediaRecorder;

import com.shields.audioandroid.AudioIOUtility;
import com.shields.audioandroid.AudioIOUtilityInterface;
import com.shields.audioandroid.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
        injects = { MainActivity.class, AudioIOUtility.class }
)
public class AudioIOUtilityModule {
    @Provides
    @Singleton
    public AudioIOUtilityInterface provideAudioIOUtility() {
        return new AudioIOUtility();
    }

    @Provides
    @Singleton
    public MediaRecorder provideMediaRecorder() {
        return new MediaRecorder();
    }

    @Provides
    @Singleton
    public MediaPlayer provideMediaPlayer() {
        return new MediaPlayer();
    }

}
