package com.shields.audioandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shields.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @Inject
    AudioIOUtilityInterface audioIOUtilityInterface;

    @InjectView(R.id.recordButton)
    Button recordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.inject(this);

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioIOUtilityInterface.startRecording(getApplicationContext());
            }
        });
    }

}
