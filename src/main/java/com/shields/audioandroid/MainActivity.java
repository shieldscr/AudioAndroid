package com.shields.audioandroid;

import android.graphics.drawable.TransitionDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
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

        recordButton = (Button) this.findViewById(R.id.recordButton);

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation buttonAnimation = AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.button_animation);

                /*Spike animation code*/

                Animation buttonMoveAnimation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f
                        , Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_PARENT, -0.25f);

                buttonMoveAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        recordButton.setBackgroundResource(R.drawable.round_button_pressed);

//                        int durationMillis = 1000;
//                        TransitionDrawable transition = (TransitionDrawable) recordButton.getBackground();
//                        transition.startTransition(durationMillis);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

//                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.75F);

                buttonMoveAnimation.setInterpolator(new LinearInterpolator());
                buttonMoveAnimation.setDuration(8000);

                buttonMoveAnimation.setFillAfter(true);

                AnimationSet aniSet = new AnimationSet(true);
                aniSet.addAnimation(buttonAnimation);
                aniSet.addAnimation(buttonMoveAnimation);
//                aniSet.addAnimation(buttonClick);
                aniSet.setFillAfter(true);

                recordButton.startAnimation(aniSet);

                /*End spike animation code*/

                audioIOUtilityInterface.startRecording(getApplicationContext());
            }
        });
    }

}
