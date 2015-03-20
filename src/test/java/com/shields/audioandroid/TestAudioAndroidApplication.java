package com.shields.audioandroid;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public class TestAudioAndroidApplication extends AudioAndroidApplication {

    AudioIOUtilityTestModule testModule;

    public TestAudioAndroidApplication() {
        testModule = new AudioIOUtilityTestModule();
    }

    @Override
    public void onCreate() {
        List<Object> modules = getModules();
        graph = ObjectGraph.create(modules.toArray());
    }

    public AudioIOUtilityTestModule getTestModule() {
        return testModule;
    }

    @Override
    protected List<Object> getModules() {

        return Arrays.<Object>asList(testModule);
    }
}
