package com.shields;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
        injects = {
                AudioAndroidApplication.class,
                AudioIOUtility.class
        }
)
public class AudioIOUtilityModule {
    @Provides @Singleton AudioIOUtility provideAudioIOUtility() {
        return new AudioIOUtility();
    }
}
