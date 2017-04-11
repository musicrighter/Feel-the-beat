package com.example.music.feel_the_beat;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //declare variables
    private TextView topTextView;
    private TextView bottomTextView;
    private EditText bpmEditText;

    private int count;
    private long[] pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize variables
        topTextView = (TextView) findViewById(R.id.topTextView);
        bottomTextView = (TextView) findViewById(R.id.bottomTextView);
        bpmEditText = (EditText) findViewById(R.id.bpmEditText);

        Button bpmLessButton = (Button) findViewById(R.id.bpmLessButton);
        Button bpmMoreButton = (Button) findViewById(R.id.bpmMoreButton);
        Button topLessButton = (Button) findViewById(R.id.topLessButton);
        Button topMoreButton = (Button) findViewById(R.id.topMoreButton);
        Button bottomLessButton = (Button) findViewById(R.id.bottomLessButton);
        Button bottomMoreButton = (Button) findViewById(R.id.bottomMoreButton);
        Button startStopButton = (Button) findViewById(R.id.startStopButton);

        bpmLessButton.setOnClickListener(this);
        bpmMoreButton.setOnClickListener(this);
        topLessButton.setOnClickListener(this);
        topMoreButton.setOnClickListener(this);
        bottomLessButton.setOnClickListener(this);
        bottomMoreButton.setOnClickListener(this);
        startStopButton.setOnClickListener(this);

        count = 0;
    }

    @Override
    public void onClick(View view) {
        String input = bpmEditText.getText().toString();
        if (input.isEmpty()) {
            input = "120";
        }
        int tempo = Integer.parseInt(input);

        int top = Integer.parseInt(topTextView.getText().toString());
        int bottom = Integer.parseInt(bottomTextView.getText().toString());

        // Get instance of Vibrator from current Context
        Vibrator mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Start immediately
        // Vibrate for 200 milliseconds
        // Sleep for 0 milliseconds
        pattern = new long[]{0, 200, 1000};

        switch (view.getId()) {
            case R.id.bpmLessButton:
                tempo--;
                pattern[2] = 60/tempo*1000-200;
                bpmEditText.setText(String.valueOf(tempo));
                break;
            case R.id.bpmMoreButton:
                tempo++;
                pattern[2] = 60/tempo*1000-200;
                bpmEditText.setText(String.valueOf(tempo));
                break;
            case R.id.topLessButton:
                if (top != 1) {
                    top--;
                }
                topTextView.setText(String.valueOf(top));
                break;
            case R.id.topMoreButton:
                if (top != 32 ) {
                    top++;
                }
                topTextView.setText(String.valueOf(top));
                break;
            case R.id.bottomLessButton:
                if (bottom != 1) {
                    bottom = bottom / 2;
                }
                bottomTextView.setText(String.valueOf(bottom));
                break;
            case R.id.bottomMoreButton:
                if (bottom != 32 ) {
                    bottom = bottom * 2;
                }
                bottomTextView.setText(String.valueOf(bottom));
                break;
            case R.id.startStopButton:
                count++;
                // The "0" means to repeat the pattern starting at the beginning and -1 means do not stop
                // You will vibrate for your pause times and pause for your vibrate times !
                if (count%2 == 0) {
                    mVibrator.cancel();
                }
                else {
                    mVibrator.vibrate(pattern, 0);
                }

                break;
        }
    }
}
