package com.shields.audioandroid;

import android.media.MediaRecorder;

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

}
