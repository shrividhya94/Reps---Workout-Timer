package com.fitness.reps;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    long timeInMilli;
    static int minuteRemaining = 5, secondsRemaining = 0;
    Button startTimerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTimerButton = findViewById(R.id.trigger_timer);
    }

    public void startCountDownTimer(View view) {
        setTimeToView(minuteRemaining, secondsRemaining);
        timeInMilli = ((minuteRemaining*60)+secondsRemaining)*1000;
        startTimerButton.setClickable(false);
        new CountDownTimer(timeInMilli, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("shrevs", String.valueOf(millisUntilFinished));
                if(secondsRemaining == 0) {
                    minuteRemaining--;
                    secondsRemaining = 59;
                } else {
                    secondsRemaining--;
                }
                setTimeToView(minuteRemaining, secondsRemaining);
            }

            @Override
            public void onFinish() {
                Log.d("shrevs", "done");
                hideDisplay();
                startTimerButton.setClickable(false);
            }
        }.start();
    }

    private void hideDisplay() {
        LinearLayout displayTimeLayout =  findViewById(R.id.display_time);
        displayTimeLayout.setVisibility(View.INVISIBLE);
    }

    private void setTimeToView(int minute, int seconds) {
        LinearLayout displayTimeLayout =  findViewById(R.id.display_time);
        TextView minuteIndex =  findViewById(R.id.minute_index);
        TextView secondsIndex =  findViewById(R.id.seconds_index);

        StringBuilder minuteAsString = new StringBuilder();
        StringBuilder secondsAsString = new StringBuilder();
        if(minute/10 == 0) minuteAsString.append('0');
        minuteAsString.append(minute);
        if(seconds/10 == 0) secondsAsString.append('0');
        secondsAsString.append(seconds);

        minuteIndex.setText(minuteAsString);
        secondsIndex.setText(secondsAsString);
        displayTimeLayout.setVisibility(View.VISIBLE);
    }
}
