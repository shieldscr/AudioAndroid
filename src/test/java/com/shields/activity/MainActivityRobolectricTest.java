package com.shields.activity;

import android.widget.Button;

import com.shields.AudioAndroidApplication;
import com.shields.AudioIOUtility;
import com.shields.AudioIOUtilityModule;
import com.shields.R;
import com.shields.audioandroid.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Config(manifest = "./src/main/AndroidManifest.xml", emulateSdk = 21)
@RunWith(RobolectricTestRunner.class)
public class MainActivityRobolectricTest {

    private MainActivity mainActivity;
    private Button recordButton;

    @Module(
            includes = AudioIOUtilityModule.class,
            injects = {
                    AudioAndroidApplication.class,
                    AudioIOUtility.class,
                    MainActivityRobolectricTest.class
            },
            overrides = true
    )
    static class TestModule {
        @Provides @Singleton AudioIOUtility provideAudioIOUtility() {
            return Mockito.mock(AudioIOUtility.class);
        }
    }

    @Inject AudioIOUtility audioIOUtility;

    @Before
    public void setUp() {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        recordButton = (Button) mainActivity.findViewById(R.id.recordButton);
        ObjectGraph.create(new TestModule()).inject(this);
    }

    @Test
    public void canGetAudioIOUtiliti() {
        assertNotNull(mainActivity.audioIOUtility);
    }

    @Test
    public void canGetMainActivity() {
        assertTrue(mainActivity != null);
    }

    @Test
    public void recordButtonExists() {
        assertTrue(recordButton != null);
    }

//    @Test
//    public void whenRecordButtonIsClickedThenAudioIOUtilityStartsRecording() {
//        recordButton.performClick();
//        verify(audioIOUtility, times(1)).startRecording(mainActivity);
//    }

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
