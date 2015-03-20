package com.shields.audioandroid;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
        injects = MainActivity.class
)
public class AudioIOUtilityModule {
    @Provides @Singleton public AudioIOUtilityInterface provideAudioIOUtility() {
        return new AudioIOUtility();
    }
}
