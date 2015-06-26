package com.shields.audioandroid;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.shields.R;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    AudioIOUtilityInterface audioIOUtilityInterface;

    private Button recordButton;
    private ArrayAdapter recordArrayAdapter;
    private ArrayList<String> loops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        loops = new ArrayList<>();
        recordArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, loops);

        recordButton = (Button)findViewById(R.id.recordButton);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordButton.setClickable(false);

                loops.add("Loop 1");

                animateRecordButton();
                startButtonCountdownTimer();

                audioIOUtilityInterface.startRecording(getApplicationContext());
            }
        });

        ListView listView = (ListView) findViewById(R.id.recordListView);
        listView.setAdapter(recordArrayAdapter);

    }

    private void animateRecordButton() {
        Animation buttonMoveAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f
                , Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_PARENT, -0.35f);
        buttonMoveAnimation.setRepeatMode(0);
        buttonMoveAnimation.setDuration(8000);
        buttonMoveAnimation.setFillAfter(true);
        recordButton.startAnimation(buttonMoveAnimation);

        ObjectAnimator animator = ObjectAnimator.ofInt(recordButton, "backgroundColor", Color.parseColor("#EF5350"), Color.parseColor("#FFE082")).setDuration(4000);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ObjectAnimator pauseAnimator = ObjectAnimator.ofInt(recordButton, "backgroundColor", Color.parseColor("#FFE082"), Color.parseColor("#81C784")).setDuration(4000);
                pauseAnimator.setEvaluator(new ArgbEvaluator());
                pauseAnimator.start();
                recordArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

    private void startButtonCountdownTimer() {
        recordButton.setText(getText(R.string.recordButtonText) + " (8)");
        new CountDownTimer(9000, 1000) {

            public void onTick(long millisUntilFinished) {
                recordButton.setText(getText(R.string.recordButtonText) + " (" + String.valueOf(millisUntilFinished / 1000) + ")");
            }

            public void onFinish() {
                recordButton.setText("Playing");
            }
        }.start();
    }
}
