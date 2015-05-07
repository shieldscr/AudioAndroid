package com.shields.audioandroid;

import android.widget.Button;

import com.shields.BuildConfig;
import com.shields.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@Config(sdk = 21, constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class MainActivityRobolectricTest extends RobolectricTestBase {

    private MainActivity mainActivity;
    private Button recordButton;

    @Before
    public void setUp() {
        super.setUp();
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
        recordButton.performClick();
        verify(super.audioIOUtilityTestModule().provideAudioIOUtility()).startRecording(mainActivity.getApplicationContext());
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
