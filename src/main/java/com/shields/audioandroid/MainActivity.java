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
import android.widget.Button;

import com.shields.R;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    AudioIOUtilityInterface audioIOUtilityInterface;

    private Button recordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        recordButton = (Button)findViewById(R.id.recordButton);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRecordButton();
                startButtonCountdownTimer();

                audioIOUtilityInterface.startRecording(getApplicationContext());
            }
        });
    }

    private void animateRecordButton() {
        Animation buttonMoveAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f
                , Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_PARENT, -0.35f);
        buttonMoveAnimation.setRepeatMode(0);
        buttonMoveAnimation.setDuration(8000);
        buttonMoveAnimation.setFillAfter(true);
        recordButton.startAnimation(buttonMoveAnimation);

        ObjectAnimator animator = ObjectAnimator.ofInt(recordButton, "backgroundColor", Color.parseColor("#00CDCD"), Color.parseColor("#EEE685")).setDuration(3000);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ObjectAnimator pauseAnimator = ObjectAnimator.ofInt(recordButton, "backgroundColor", Color.parseColor("#EEE685"), Color.parseColor("#FF7256")).setDuration(3000);
                pauseAnimator.setEvaluator(new ArgbEvaluator());
                pauseAnimator.start();
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
