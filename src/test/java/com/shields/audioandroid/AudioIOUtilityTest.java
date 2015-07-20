package com.shields.audioandroid;

import android.media.MediaPlayer;

import com.shields.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
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
        RehearsalAudioRecorder mediaRecorderMock = Mockito.mock(RehearsalAudioRecorder.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock, mediaPlayerMock);
    }

    @Test
    public void whenStartRecordingIsCalledThenMediaRecorderHasTheCorrectFileOutputPathSet() throws IOException {
        RehearsalAudioRecorder mediaRecorderMock = Mockito.mock(RehearsalAudioRecorder.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock, mediaPlayerMock);
        localAudioIOUtility.startRecording(mainActivity.getApplicationContext(), 0);

        verify(mediaRecorderMock).setOutputFile(outputFile.toString());
        verify(mediaRecorderMock).prepare();
        verify(mediaRecorderMock).start();
    }

    @Test
    public void whenStopRecordingIsCalledThenMediaRecorderIsStoppedAndReleased() {
        RehearsalAudioRecorder mediaRecorderMock = Mockito.mock(RehearsalAudioRecorder.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock, mediaPlayerMock);
        localAudioIOUtility.stopRecording(mainActivity.getApplicationContext());

        verify(mediaRecorderMock).stop();
        verify(mediaRecorderMock).release();
    }

    @Test
    public void whenAudioIOUtilityIsInitializedThenItHasAMediaPlayerSetupCorrectly() throws IOException {
        RehearsalAudioRecorder mediaRecorderMock = Mockito.mock(RehearsalAudioRecorder.class);
        MediaPlayer localMediaPlayerMock = Mockito.mock(MediaPlayer.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock, localMediaPlayerMock);

        localAudioIOUtility.play(mainActivity.getApplicationContext());
        verify(localMediaPlayerMock).setDataSource(outputFile.toString());
        verify(localMediaPlayerMock).setLooping(true);
        verify(localMediaPlayerMock).prepare();
        verify(localMediaPlayerMock).start();
    }

    @Test
    public void whenAudioIOUtilityIsRecordingThenItCannotBeStartedAgain() {
        RehearsalAudioRecorder mediaRecorderMock = Mockito.mock(RehearsalAudioRecorder.class);
        MediaPlayer localMediaPlayerMock = Mockito.mock(MediaPlayer.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock, localMediaPlayerMock);

        localAudioIOUtility.startRecording(mainActivity.getApplicationContext(), 0);
        localAudioIOUtility.startRecording(mainActivity.getApplicationContext(), 0);
        verify(mediaRecorderMock, times(1)).start();
    }

    @Test
    public void recordingCanBeStartedStoppedAndRestarted() {
        RehearsalAudioRecorder mediaRecorderMock = Mockito.mock(RehearsalAudioRecorder.class);
        MediaPlayer localMediaPlayerMock = Mockito.mock(MediaPlayer.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock, localMediaPlayerMock);

        localAudioIOUtility.startRecording(mainActivity.getApplicationContext(), 0);
        localAudioIOUtility.startRecording(mainActivity.getApplicationContext(), 0);
        verify(mediaRecorderMock, times(1)).start();

        Mockito.reset(mediaRecorderMock);
        localAudioIOUtility.stopRecording(mainActivity.getApplicationContext());
        localAudioIOUtility.startRecording(mainActivity.getApplicationContext(), 0);
        verify(mediaRecorderMock).start();
    }

    @Test
    public void whenStopPlayingIsCalledThenMediaPlayerStopsAndIsRetired() {
        RehearsalAudioRecorder mediaRecorderMock = Mockito.mock(RehearsalAudioRecorder.class);
        MediaPlayer localMediaPlayerMock = Mockito.mock(MediaPlayer.class);
        AudioIOUtilityInterface localAudioIOUtility = new AudioIOUtility(mediaRecorderMock, localMediaPlayerMock);

        localAudioIOUtility.stopPlaying(mainActivity.getApplicationContext());
        verify(localMediaPlayerMock).setLooping(false);
        verify(localMediaPlayerMock).stop();
        verify(localMediaPlayerMock).release();
    }

}
