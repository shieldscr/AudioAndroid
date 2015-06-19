package com.shields.audioandroid;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import com.dd.CircularProgressButton;

import com.shields.R;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    AudioIOUtilityInterface audioIOUtilityInterface;

    private CircularProgressButton recordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        recordButton = (CircularProgressButton) this.findViewById(R.id.recordButton);

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final long length_in_milliseconds = 8000;
                CountDownTimer timer = new CountDownTimer(length_in_milliseconds, 1) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        long progress = (millisUntilFinished / 100);
                        recordButton.setProgress((int) progress);
                    }

                    @Override
                    public void onFinish() {
                        recordButton.setProgress(100);
                    }
                }.start();

                audioIOUtilityInterface.startRecording(getApplicationContext());
            }
        });
    }

}
