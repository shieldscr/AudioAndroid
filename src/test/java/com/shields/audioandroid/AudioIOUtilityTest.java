package com.shields.audioandroid;

import android.media.MediaRecorder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;

import static org.mockito.Mockito.verify;

@Config(emulateSdk = 21)
@RunWith(RobolectricTestRunner.class)
public class AudioIOUtilityTest extends RobolectricTestBase {

    private File outputFile;

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        super.setUp();
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();

        setUpConstants();
    }

    public void setUpConstants() {
        outputFile = new File(mainActivity.getCacheDir().getPath() + "/temp_audio_recording");
    }

    @Test
    public void whenAudioIOUtilityIsInitializedItHasAMediaRecorderWithCorrectAudioProperties() {
        MediaRecorder mediaRecorderMock = Mockito.mock(MediaRecorder.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock);
        verify(mediaRecorderMock).setAudioSource(MediaRecorder.AudioSource.MIC);
        verify(mediaRecorderMock).setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
    }

    @Test
    public void whenStartRecordingIsCalledThenMediaRecorderHasTheCorrectFileOutputPathSet() {
        MediaRecorder mediaRecorderMock = Mockito.mock(MediaRecorder.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock);
        localAudioIOUtility.startRecording(mainActivity.getApplicationContext());
        verify(mediaRecorderMock).setOutputFile(outputFile.toString());
    }
}
