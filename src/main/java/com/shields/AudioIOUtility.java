package com.shields;

import android.content.Context;
import android.widget.Toast;

public class AudioIOUtility {
    public AudioIOUtility() {
    }

    public void startRecording(Context context) {
        Toast.makeText(context, R.string.recordingToast, Toast.LENGTH_SHORT).show();
    }

    public boolean stopRecording(Context context) {
        Toast.makeText(context, R.string.stopRecordingToast, Toast.LENGTH_LONG).show();
        return true;
    }

    public boolean play(Context context) {
        Toast.makeText(context, "Play", Toast.LENGTH_LONG).show();
        return true;
    }
}
