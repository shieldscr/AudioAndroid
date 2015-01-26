package com.shields;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class AudioIOService extends Service {
    public AudioIOService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public boolean startRecording() {
        Toast.makeText(getBaseContext(), "Recording", Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean stopRecording() {
        Toast.makeText(getBaseContext(), "Recording Stopped", Toast.LENGTH_LONG).show();
        return true;
    }

    public boolean play() {
        Toast.makeText(getBaseContext(), "Play", Toast.LENGTH_LONG).show();
        return true;
    }
}
