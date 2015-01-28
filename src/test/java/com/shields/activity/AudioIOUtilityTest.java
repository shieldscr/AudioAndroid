package com.shields.activity;

import android.content.Context;

import com.shields.AudioIOUtility;
import com.shields.R;
import com.shields.audioandroid.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static junit.framework.Assert.assertEquals;

@Config(manifest = "./src/main/AndroidManifest.xml", emulateSdk = 21)
@RunWith(RobolectricTestRunner.class)
public class AudioIOUtilityTest {

    private MainActivity mainActivity;
    private AudioIOUtility audioIOService;
    private Context applicationContext;

    public AudioIOUtilityTest() {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        audioIOService = new AudioIOUtility();
        applicationContext = mainActivity.getApplicationContext();
    }

    @Test
    public void recordButtonClickedReturnsDisplaysToastWithCorrectToastText() {
        audioIOService.startRecording(applicationContext);
        assertEquals(ShadowToast.getTextOfLatestToast(), applicationContext.getString(R.string.recordingToast));
    }

    @Test
    public void stopRecordButtonClickedDisplaysToastWithCorrectTestText() {
        audioIOService.stopRecording(applicationContext);
        assertEquals(ShadowToast.getTextOfLatestToast(), applicationContext.getString(R.string.stopRecordingToast));
    }

    @Test
    public void playRecordingButtonClickedDisplaysToastWithCorrectTestText() {
        audioIOService.play(applicationContext);
        assertEquals(ShadowToast.getTextOfLatestToast(), applicationContext.getString(R.string.playToast));
    }
}
