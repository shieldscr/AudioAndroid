package com.shields.audioandroid;

import android.widget.Button;

import com.shields.BuildConfig;
import com.shields.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityRobolectricTest extends RobolectricTestBase {

    private MainActivity mainActivity;

    @InjectView(R.id.recordButton)
    Button recordButton;

    @Before
    public void setUp() {
        super.setUp();
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        recordButton = (Button) mainActivity.findViewById(R.id.recordButton);
        ButterKnife.inject(this, mainActivity);
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

}
