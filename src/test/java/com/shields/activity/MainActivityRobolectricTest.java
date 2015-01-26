package com.shields.activity;

import android.widget.Button;

import com.shields.R;
import com.shields.audioandroid.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;

@Config(manifest = "./src/main/AndroidManifest.xml", emulateSdk = 21)
@RunWith(RobolectricTestRunner.class)
public class MainActivityRobolectricTest {

    MainActivity mainActivity = null;

    public MainActivityRobolectricTest() {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void canGetMainActivity() {
        assertTrue(mainActivity != null);
    }

    @Test
    public void whenRecordButtonIsClickedThenRecordingIsStarted() {
        Button recordButton = (Button) mainActivity.findViewById(R.id.recordButton);
        assertTrue(recordButton != null);

        recordButton.performClick();

        assertTrue(mainActivity.startRecording());
    }

    @Test
    public void whenRecordButtonIsClickedThenRecordingIsStopped() {
        Button stopRecordButton = (Button) mainActivity.findViewById(R.id.stopRecordButton);
        assertTrue(stopRecordButton != null);

        stopRecordButton.performClick();

        assertTrue(mainActivity.stopRecording());
    }

    @Test
    public void whenPlayButtonIsClickedThenPlaybackBegins() {
        Button playButton = (Button) mainActivity.findViewById(R.id.playButton);
        assertTrue(playButton != null);

        playButton.performClick();

        assertTrue(mainActivity.play());
    }

}
