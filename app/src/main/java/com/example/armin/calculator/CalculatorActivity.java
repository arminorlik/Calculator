package com.example.armin.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    private String display = "0";
    private double akumuator = 0.0;
    private Operation currentOperation = Operation.NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void WybranyPrzycisk(View view) {
        Button button = (Button) view;
        String key = button.getText().toString();
        TextView display = (TextView) findViewById(R.id.Textview);
        display.setText(display.getText() + key);
    }
}
