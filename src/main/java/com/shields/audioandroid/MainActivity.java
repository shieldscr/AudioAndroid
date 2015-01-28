package com.shields.audioandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shields.AudioIOUtility;
import com.shields.R;

public class MainActivity extends Activity {

    private AudioIOUtility audioIOUtility;
    private Button recordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        audioIOUtility = new AudioIOUtility();

        recordButton = (Button) this.findViewById(R.id.recordButton);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioIOUtility.startRecording(getApplicationContext());
            }
        });
    }

}
