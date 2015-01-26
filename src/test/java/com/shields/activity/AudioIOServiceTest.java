package com.shields.activity;

import com.shields.AudioIOService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

@Config(manifest = "./src/main/AndroidManifest.xml", emulateSdk = 21)
@RunWith(RobolectricTestRunner.class)
public class AudioIOServiceTest {

    AudioIOService audioIOService;

    public AudioIOServiceTest() {
        audioIOService = new AudioIOService();
    }

    @Test
    public void recordButtonClickedReturnsTrue() {
        assertTrue(audioIOService.startRecording());
    }

    @Test
    public void stopRecordButtonClickedReturnsTrue() {
        assertTrue(audioIOService.stopRecording());
    }

    @Test
    public void playRecordingButtonClickedReturnsTrue() {
        assertTrue(audioIOService.play());
    }
}
