package com.shields.activity;

import android.app.Activity;
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

    Activity mainActivity = null;

    public MainActivityRobolectricTest() {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void canGetMainActivity() {
        assertTrue(mainActivity != null);
    }

    @Test
    public void canGetRecordButton() {
        Button recordButton = (Button) mainActivity.findViewById(R.id.recordButton);
        assertTrue(recordButton != null);
    }

}
