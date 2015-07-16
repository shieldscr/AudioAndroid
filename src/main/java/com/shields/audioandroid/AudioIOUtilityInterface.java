package com.shields.audioandroid;

import android.content.Context;

public interface AudioIOUtilityInterface {
    void startRecording(Context context, Integer loopCount);

    void stopRecording(Context context);

    void play(Context context);

    void stopPlaying(Context context);
}
