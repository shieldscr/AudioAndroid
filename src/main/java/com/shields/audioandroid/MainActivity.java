package com.shields.audioandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shields.R;

public class MainActivity extends Activity {

    Button recordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        recordButton = (Button) this.findViewById(R.id.recordButton);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording();
            }
        });
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
        Toast.makeText(getBaseContext(), "Recording Stopped", Toast.LENGTH_LONG).show();
        return true;
    }
}
