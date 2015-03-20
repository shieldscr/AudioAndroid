package com.shields.audioandroid;

import android.content.Context;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@Ignore
@Config(emulateSdk = 21)
@RunWith(RobolectricTestRunner.class)
public class AudioIOUtilityTest {

    private MainActivity mainActivity;
    private AudioIOUtilityInterface audioIOService;
    private Context applicationContext;

    @Before
    public void setUp() {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().start().get();
        applicationContext = RuntimeEnvironment.application;
        audioIOService = ((TestAudioAndroidApplication) applicationContext).getTestModule().provideAudioIOUtility();
    }

//    @Test
//    public void recordButtonClickedReturnsDisplaysToastWithCorrectToastText() {
//        audioIOService.startRecording(applicationContext);
//        assertNotNull(applicationContext);
//        ShadowHandler.idleMainLooper();
//        assertEquals(1, ShadowToast.shownToastCount());
//        assertEquals(applicationContext.getString(R.string.recordingToast), ShadowToast.getTextOfLatestToast());
//    }

//    @Test
//    public void stopRecordButtonClickedDisplaysToastWithCorrectTestText() {
//        audioIOService.stopRecording(applicationContext);
//        assertEquals(applicationContext.getString(R.string.stopRecordingToast), ShadowToast.getTextOfLatestToast());
//    }
//
//    @Test
//    public void playRecordingButtonClickedDisplaysToastWithCorrectTestText() {
//        audioIOService.play(applicationContext);
//        assertEquals("Play", ShadowToast.getTextOfLatestToast());
//    }
}
