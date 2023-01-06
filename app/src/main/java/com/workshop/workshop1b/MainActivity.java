package com.workshop.workshop1b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnStart;
    Button btnScore;

    TextView txtScore;

    int mSeconds;
    int mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScore = findViewById(R.id.btnScore);
        btnStart = findViewById(R.id.btnStart);

        txtScore = findViewById(R.id.txtScore);
        txtScore.setText(String.valueOf(mScore));

        mSeconds = 0;
        mScore = 0;

        txtScore.setText("Your score: " + mScore);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSeconds = 30;
                mScore = 0;

                txtScore.setText("Your score: " + mScore);
                btnScore.setEnabled(true);
            }
        });

        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScore++;
                txtScore.setText("Your score: " + mScore);
            }
        });

        runTimer();
    }

    //run seconds
    private void runTimer() {
        final TextView txtTime = findViewById(R.id.txtTime);
        final Handler handler = new Handler();
        //rud background thread
        handler.post(new Runnable() {
            @Override
            public void run() {
                txtTime.setText("Time left: " + mSeconds);

                if (mSeconds > 0) {
                    btnScore.setEnabled(true);
                    mSeconds--;
                } else {
                    btnScore.setEnabled(false);
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    //STEP 1 on save instance state
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i("SavedInstanceState", "onSaveInstanceState is called");
        super.onSaveInstanceState(outState);

        //STEP 1 on save instance state
        outState.putInt("seconds", mSeconds);
        outState.putInt("score", mScore);
    }

    //STEP 2 RestoreInstanceState
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i("SavedInstanceState", "onRestoreInstanceState is called");
        super.onRestoreInstanceState(savedInstanceState);

        //STEP 2 Restoring an instance's State
        mSeconds = savedInstanceState.getInt("seconds");
        mScore = savedInstanceState.getInt("score");
        txtScore.setText("Your score: " + mScore);
    }

}
