package com.shields.audioandroid;

import android.content.Context;

import com.shields.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static junit.framework.Assert.assertEquals;

@Config(emulateSdk = 21)
@RunWith(RobolectricTestRunner.class)
public class AudioIOUtilityTest {

    private MainActivity mainActivity;
    private AudioIOUtilityInterface audioIOService;
    private Context applicationContext;

    public AudioIOUtilityTest() {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().start().get();
        applicationContext = mainActivity.getApplicationContext();
        audioIOService = ((TestAudioAndroidApplication) RuntimeEnvironment.application).getTestModule().provideAudioIOUtility();
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
