package com.shields.audioandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shields.AudioIOService;
import com.shields.R;

public class MainActivity extends Activity {

    AudioIOService audioIOService;
    Button recordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        audioIOService = new AudioIOService();

        recordButton = (Button) this.findViewById(R.id.recordButton);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioIOService.startRecording();
            }
        });
    }

}
