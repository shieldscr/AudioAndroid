package com.shields.audioandroid;

import android.media.MediaPlayer;
import android.media.MediaRecorder;

import com.shields.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Config(sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class AudioIOUtilityTest extends RobolectricTestBase {

    private File outputFile;

    private MainActivity mainActivity;
    private MediaPlayer mediaPlayerMock = Mockito.mock(MediaPlayer.class);

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
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock, mediaPlayerMock);
        verify(mediaRecorderMock).setAudioSource(MediaRecorder.AudioSource.MIC);
        verify(mediaRecorderMock).setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
    }

    @Test
    public void whenStartRecordingIsCalledThenMediaRecorderHasTheCorrectFileOutputPathSet() throws IOException {
        MediaRecorder mediaRecorderMock = Mockito.mock(MediaRecorder.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock, mediaPlayerMock);
        localAudioIOUtility.startRecording(mainActivity.getApplicationContext());

        verify(mediaRecorderMock).setOutputFile(outputFile.toString());
        verify(mediaRecorderMock).setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        verify(mediaRecorderMock).prepare();
        verify(mediaRecorderMock).start();
    }

    @Test
    public void whenStopRecordingIsCalledThenMediaRecorderIsStoppedAndReleased() {
        MediaRecorder mediaRecorderMock = Mockito.mock(MediaRecorder.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock, mediaPlayerMock);
        localAudioIOUtility.stopRecording(mainActivity.getApplicationContext());

        verify(mediaRecorderMock).stop();
        verify(mediaRecorderMock).release();
    }

    @Test
    public void whenAudioIOUtilityIsInitializedThenItHasAMediaPlayerSetupCorrectly() throws IOException {
        MediaRecorder mediaRecorderMock = Mockito.mock(MediaRecorder.class);
        MediaPlayer localMediaPlayerMock = Mockito.mock(MediaPlayer.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock, localMediaPlayerMock);

        localAudioIOUtility.play(mainActivity.getApplicationContext());
        verify(localMediaPlayerMock).setDataSource(outputFile.toString());
        verify(localMediaPlayerMock).prepare();
        verify(localMediaPlayerMock).start();
    }

    @Test
    public void whenAudioIOUtilityIsRecordingThenItCannotBeStartedAgain() {
        MediaRecorder mediaRecorderMock = Mockito.mock(MediaRecorder.class);
        MediaPlayer localMediaPlayerMock = Mockito.mock(MediaPlayer.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock, localMediaPlayerMock);

        localAudioIOUtility.startRecording(mainActivity.getApplicationContext());
        localAudioIOUtility.startRecording(mainActivity.getApplicationContext());
        verify(mediaRecorderMock, times(1)).start();
    }

    @Test
    public void recordingCanBeStartedStoppedAndRestarted() {
        MediaRecorder mediaRecorderMock = Mockito.mock(MediaRecorder.class);
        MediaPlayer localMediaPlayerMock = Mockito.mock(MediaPlayer.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock, localMediaPlayerMock);

        localAudioIOUtility.startRecording(mainActivity.getApplicationContext());
        localAudioIOUtility.startRecording(mainActivity.getApplicationContext());
        verify(mediaRecorderMock, times(1)).start();

        Mockito.reset(mediaRecorderMock);
        localAudioIOUtility.stopRecording(mainActivity.getApplicationContext());
        localAudioIOUtility.startRecording(mainActivity.getApplicationContext());
        verify(mediaRecorderMock).start();
    }

}
