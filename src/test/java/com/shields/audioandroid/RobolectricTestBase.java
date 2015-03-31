package com.shields.audioandroid;

import com.shields.audioandroid.modules.AudioIOUtilityTestModule;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

public class RobolectricTestBase {

    public void setUp() {
        ShadowLog.stream = System.out;
    }

    protected AudioIOUtilityTestModule audioIOUtilityTestModule() {
        return ((TestAudioAndroidApplication) RuntimeEnvironment.application).getTestModule();
    }
}
