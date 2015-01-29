package com.shields.activity;

import android.widget.Button;

import com.shields.AudioIOUtility;
import com.shields.R;
import com.shields.audioandroid.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Config(manifest = "./src/main/AndroidManifest.xml", emulateSdk = 21)
@RunWith(RobolectricTestRunner.class)
public class MainActivityRobolectricTest {

    private MainActivity mainActivity;
    private Button recordButton;

    @Before
    public void setUp() {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        recordButton = (Button) mainActivity.findViewById(R.id.recordButton);
    }

    @Test
    public void canGetMainActivity() {
        assertTrue(mainActivity != null);
    }

    @Test
    public void recordButtonExists() {
        assertTrue(recordButton != null);
    }

    @Test
    public void whenRecordButtonIsClickedThenAudioIOUtilityStartsRecording() {
        //Need to inject a mock into MainActivity...
        AudioIOUtility mockAudioIOUtility = mock(AudioIOUtility.class);
        verify(mockAudioIOUtility, times(1)).startRecording(mainActivity);
        recordButton.performClick();
    }

    @Test
    public void stopRecordButtonExists() {
        Button stopRecordButton = (Button) mainActivity.findViewById(R.id.stopRecordButton);
        assertTrue(stopRecordButton != null);
    }

    @Test
    public void playButtonExists() {
        Button playButton = (Button) mainActivity.findViewById(R.id.playButton);
        assertTrue(playButton != null);
    }

}
