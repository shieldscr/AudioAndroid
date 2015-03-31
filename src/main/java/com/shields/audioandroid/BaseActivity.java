package com.shields.audioandroid;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AudioAndroidApplication) getApplication()).inject(this);
    }
}