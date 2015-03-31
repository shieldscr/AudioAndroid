package com.shields.audioandroid;

import android.media.MediaRecorder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.verify;

@Config(emulateSdk = 21)
@RunWith(RobolectricTestRunner.class)
public class AudioIOUtilityTest extends RobolectricTestBase {

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        super.setUp();
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void whenAudioIOUtilityIsInitializedItHasAMediaRecorderWithMicSetAsTheSource() {
        //Shadow of media recorder
        verify(super.audioIOUtilityTestModule().provideMediaRecorder()).setAudioSource(MediaRecorder.AudioSource.MIC);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility();
    }

}
