package com.shields.audioandroid;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import com.shields.R;
import com.shields.audioandroid.adapters.LoopListViewAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    AudioIOUtilityInterface audioIOUtilityInterface;

    private Button recordButton;
    private RecyclerView.Adapter recordArrayAdapter;
    private ArrayList<String> loops;
    private Integer loopCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupRecordButton();

        loops = new ArrayList<>();
        recordArrayAdapter = new LoopListViewAdapter(loops, getApplicationContext());

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recordRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(recordArrayAdapter);
        addSwipeToRemove(recyclerView);
    }

    private void setupRecordButton() {
        recordButton = (Button)findViewById(R.id.recordButton);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loopCount = 0;
                recordButton.setClickable(false);


                Integer loopString = loopCount + 1;
                loops.add("Playing loop" + " " + loopString.toString());

                animateRecordButton();
                startButtonCountdownTimer();

                audioIOUtilityInterface.startRecording(getApplicationContext(), loopCount);
            }
        });
    }

    private void animateRecordButton() {
        ObjectAnimator animator = ObjectAnimator.ofInt(recordButton, "backgroundColor", Color.parseColor("#EF5350"), Color.parseColor("#FFE082")).setDuration(4000);
        BackgroundColorAnimationTask backgroundColorAnimationTask = new BackgroundColorAnimationTask();
        backgroundColorAnimationTask.doInBackground(animator);

        animator.addListener(new RecordButtonSecondaryAnimation());
    }

    private void startButtonCountdownTimer() {
        recordButton.setText(getText(R.string.recordButtonText) + " (8)");
        new CountDownTimer(9000, 1000) {

            public void onTick(long millisUntilFinished) {
                recordButton.setText(getText(R.string.recordButtonText) + " (" + String.valueOf(millisUntilFinished / 1000) + ")");
            }

            public void onFinish() {
                recordButton.setText(R.string.recordButtonText);
                audioIOUtilityInterface.stopRecording(getApplicationContext());
                audioIOUtilityInterface.play(getApplicationContext());
                setupNextRecordingButton();
            }
        }.start();
    }

    private void setupNextRecordingButton() {
        recordButton.setClickable(true);
    }

    private class RecordButtonMainAnimation implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {
            recordArrayAdapter.notifyDataSetChanged();
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    }

    private class RecordButtonSecondaryAnimation implements Animator.AnimatorListener {
        @Override
        public void onAnimationStart(Animator animator) {
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            ObjectAnimator pauseAnimator = ObjectAnimator.ofInt(recordButton, "backgroundColor", Color.parseColor("#FFE082"), Color.parseColor("#81C784")).setDuration(4000);
            pauseAnimator.setEvaluator(new ArgbEvaluator());
            pauseAnimator.start();
            pauseAnimator.addListener(new RecordButtonMainAnimation());
        }

        @Override
        public void onAnimationCancel(Animator animator) {
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
        }
    }

    private class BackgroundColorAnimationTask extends AsyncTask<ObjectAnimator, Integer, Integer> {

        @Override
        protected Integer doInBackground(ObjectAnimator... objectAnimators) {
            objectAnimators[0].setEvaluator(new ArgbEvaluator());
            objectAnimators[0].start();
            return null;
        }
    }

    private void addSwipeToRemove(RecyclerView recyclerView) {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                audioIOUtilityInterface.stopPlaying(getApplicationContext());
                loops.remove(0);
                recordArrayAdapter.notifyDataSetChanged();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
