package com.shields.audioandroid;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import static org.mockito.Mockito.mock;

@Module(
        injects = MainActivity.class,
        overrides = true
)
public class AudioIOUtilityTestModule {
    @Provides
    @Singleton
    public AudioIOUtilityInterface provideAudioIOUtility() {
        return mock(AudioIOUtilityInterface.class);
    }
}

