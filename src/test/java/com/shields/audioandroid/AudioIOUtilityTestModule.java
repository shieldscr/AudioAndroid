package com.shields.audioandroid;

import android.media.MediaRecorder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import static org.mockito.Mockito.mock;

@Module(
        injects = {MainActivity.class, AudioIOUtility.class},
        overrides = true
)
public class AudioIOUtilityTestModule {

    private AudioIOUtilityInterface audioIOUtilityMock = mock(AudioIOUtilityInterface.class);
    private MediaRecorder mediaRecorderMock = mock(MediaRecorder.class);

    @Provides
    @Singleton
    public AudioIOUtilityInterface provideAudioIOUtility() {
        return audioIOUtilityMock;
    }

    @Provides
    @Singleton
    public MediaRecorder provideMediaRecorder() {
        return mediaRecorderMock;
    }

}

