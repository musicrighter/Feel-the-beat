package com.example.music.feel_the_beat;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetListActivity extends AppCompatActivity implements View.OnClickListener {

    //declare variables
    private TextView settopTextView;
    private TextView setbottomTextView;
    private EditText setbpmEditText;
    private EditText setnumMeasuresEditText;
    private ListView setList;
    private List<String> listSetList;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_list);

        //initialize variables
        settopTextView = (TextView) findViewById(R.id.settopTextView);
        setbottomTextView = (TextView) findViewById(R.id.setbottomTextView);
        setbpmEditText = (EditText) findViewById(R.id.setbpmEditText);
        setnumMeasuresEditText = (EditText) findViewById(R.id.setnumMeasuresEditText);
        setList = (ListView) findViewById(R.id.setList);

        listSetList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listSetList);
        setList.setAdapter(adapter);

        Button settopLessButton = (Button) findViewById(R.id.settopLessButton);
        Button settopMoreButton = (Button) findViewById(R.id.settopMoreButton);
        Button setbottomLessButton = (Button) findViewById(R.id.setbottomLessButton);
        Button setbottomMoreButton = (Button) findViewById(R.id.setbottomMoreButton);
        Button setaddSetToListButton = (Button) findViewById(R.id.setaddSetToListButton);


        settopLessButton.setOnClickListener(this);
        settopMoreButton.setOnClickListener(this);
        setbottomLessButton.setOnClickListener(this);
        setbottomMoreButton.setOnClickListener(this);
        setaddSetToListButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String input = setbpmEditText.getText().toString();
        String input2 = setnumMeasuresEditText.getText().toString();
        if (input.isEmpty()) {
            input = "120";
        }
        if (input2.isEmpty()) {
            input2 = "20";
        }
        int tempo = Integer.parseInt(input);
        int measures = Integer.parseInt(input2);

        int top = Integer.parseInt(settopTextView.getText().toString());
        int bottom = Integer.parseInt(setbottomTextView.getText().toString());

        // Get instance of Vibrator from current Context
        Vibrator mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        switch (view.getId()) {
            case R.id.settopLessButton:
                if (top != 1) {
                    top--;
                }
                settopTextView.setText(String.valueOf(top));
                break;
            case R.id.settopMoreButton:
                if (top != 32 ) {
                    top++;
                }
                settopTextView.setText(String.valueOf(top));
                break;
            case R.id.setbottomLessButton:
                if (bottom != 1) {
                    bottom = bottom / 2;
                }
                setbottomTextView.setText(String.valueOf(bottom));
                break;
            case R.id.setbottomMoreButton:
                if (bottom != 32 ) {
                    bottom = bottom * 2;
                }
                setbottomTextView.setText(String.valueOf(bottom));
                break;
            case R.id.setaddSetToListButton:
                String newString = Integer.toString(tempo) + " bpm\t"
                        + Integer.toString(top) + "/" + Integer.toString(bottom)
                        + "\tFor " + Integer.toString(measures) + " measures";
                listSetList.add(newString);
                adapter.notifyDataSetChanged();
                break;
        }
    }

}
