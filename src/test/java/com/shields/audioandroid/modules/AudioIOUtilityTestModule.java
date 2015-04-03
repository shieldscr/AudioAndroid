package com.shields.audioandroid.modules;

import android.media.MediaRecorder;

import com.shields.audioandroid.AudioIOUtility;
import com.shields.audioandroid.AudioIOUtilityInterface;
import com.shields.audioandroid.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import static org.mockito.Mockito.mock;

@Module(
        injects = {
            MainActivity.class
        },
        overrides = true
)
public class AudioIOUtilityTestModule {

    private AudioIOUtilityInterface audioIOUtilityMock = mock(AudioIOUtilityInterface.class);

    @Provides
    @Singleton
    public AudioIOUtilityInterface provideAudioIOUtility() {
        return audioIOUtilityMock;
    }

}

