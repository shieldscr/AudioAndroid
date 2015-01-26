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
    public void recordButtonExists() {
        Button recordButton = (Button) mainActivity.findViewById(R.id.recordButton);
        assertTrue(recordButton != null);
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
