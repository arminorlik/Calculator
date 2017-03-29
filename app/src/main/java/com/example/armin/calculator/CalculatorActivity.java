package com.example.armin.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    public static final String DISPLAY_KLUCZ = "display";
    public static final String AKUMULATOR_KLUCZ = "Akumulator_Klucz";
    public static final String OPERACJA_KLUCZ = "Operacja_Klucz";
    private String display = "0";
    private double akumuator = 0.0;
    private Operation currentOperation = Operation.NONE;
    private TextView displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        displayText = (TextView) findViewById(R.id.Textview);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DISPLAY_KLUCZ, display);
        outState.putDouble(AKUMULATOR_KLUCZ, akumuator);
        outState.putString(OPERACJA_KLUCZ, currentOperation.name());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        display = savedInstanceState.getString(DISPLAY_KLUCZ, "0"); //jeśli nic nie przywroci, domyślnie wpisze 0
        akumuator = savedInstanceState.getDouble(AKUMULATOR_KLUCZ);
        currentOperation = Operation.valueOf(savedInstanceState.getString(OPERACJA_KLUCZ ));
        UpdateDisplay();
    }

    public void WybranyPrzycisk(View view) {
        Button button = (Button) view;
        String key = button.getText().toString();

        switch (key) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (display.equals("0")) {
                    display = "";
                }

                display = display + key;
                break;
            case ".":
                if (!display.contains(".")) {
                    display = display + key;
                }
                break;
            case "-":
            case "+":
                operacjeKalkulatora(key);
                break;
            case "=":
                Wynik();
                break;
            case "CE":
                czyscJedenZnak();
                break;
            case "C":
                czyscWszystko();
                break;
        }

        UpdateDisplay();
    }

    private void UpdateDisplay() {
        displayText.setText(display);
    }

    private void czyscWszystko() {
        display = "0";
        akumuator = 0.0;
        currentOperation = Operation.NONE;
    }

    private void czyscJedenZnak() {
        if (display.length() > 1) {
            display = display.substring(0, display.length() - 1);
        } else {
            display = "0";
        }
    }

    private void Wynik() {
        double drugaLiczba = Double.parseDouble(display);
        switch (currentOperation) {
            case ADD:
                displayResult(akumuator + drugaLiczba);
                break;
            case SUBSTRACT:
                displayResult(akumuator - drugaLiczba);
                break;
        }
        akumuator = 0.0;
        currentOperation = Operation.NONE;
    }

    private void displayResult(double result) {
        if (result == (long) result) {
            display = String.format("%d", (long) result);
        } else {
            display = String.format("%s", result);
        }
    }

    private void operacjeKalkulatora(String key) {
        currentOperation = Operation.operationFromKey(key); //zapamiętujemy jaką operację chcemy wykonać
        akumuator = Double.parseDouble(display);
        display = "0";
    }
}
