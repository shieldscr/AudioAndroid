package com.shields.audioandroid;

import android.media.MediaRecorder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
        MediaRecorder mediaRecorder = new MediaRecorder();
        MediaRecorder mediaRecorderSpy = Mockito.spy(mediaRecorder);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderSpy);
        verify(mediaRecorderSpy).setAudioSource(MediaRecorder.AudioSource.MIC);
    }

}
