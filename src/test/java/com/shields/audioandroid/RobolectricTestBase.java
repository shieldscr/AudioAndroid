package com.shields.audioandroid;

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
