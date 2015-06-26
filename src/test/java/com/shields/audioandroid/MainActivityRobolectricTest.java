package com.shields.audioandroid;

import android.widget.Button;
import android.widget.ListView;

import com.shields.BuildConfig;
import com.shields.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
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
    public void whenRecordButtonIsClickedThenATimerStartsAndDisplaysTheStartingTimeOnTheButton() {
        assertEquals(mainActivity.getString(R.string.recordButtonText), recordButton.getText().toString());
        recordButton.performClick();
        assertEquals((mainActivity.getString(R.string.recordButtonText) + " (8)"), recordButton.getText().toString());
    }

    @Test
    public void whenTheRecordButtonIsClickedThenItIsNoLongerClickable() {
        assertEquals(true, recordButton.isClickable());
        recordButton.performClick();
        assertEquals(false, recordButton.isClickable());
    }

    @Test
    public void whenTheRecordButtonIsPressedThenARecordingIsAddedToThePlayingList() {
        ListView playingListView = (ListView) mainActivity.findViewById(R.id.recordListView);
        assertNotNull(playingListView);

        assertEquals(0, playingListView.getAdapter().getCount());
        recordButton.performClick();
        assertEquals(1, playingListView.getAdapter().getCount());
        assertEquals("Loop 1", playingListView.getAdapter().getItem(1).toString());
    }

}
