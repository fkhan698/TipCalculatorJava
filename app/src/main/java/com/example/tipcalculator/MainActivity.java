package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TipCalculator tipCalc;
    private NumberFormat money = NumberFormat.getCurrencyInstance();

    private EditText billEditText;
    private EditText tipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator(.17f, 100.0f);
        setContentView(R.layout.activity_main);
        billEditText = findViewById(R.id.billAmt);
        tipEditText = findViewById(R.id.tipAmt);

        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener((TextWatcher) tch);
        tipEditText.addTextChangedListener(tch);


    }

    //Called when user clicks button
    public void calculate() {




        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();

        TextView tipTextView = findViewById(R.id.tv_tip_total);
        TextView billTotalTextView = findViewById(R.id.tv_bill_total);

        try{
            //convert billString and tipString to floats
            float billAmount = Float.parseFloat(billString);
            float tipPercent = Integer.parseInt(tipString);

            //update the Model
            tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);

            //Ask Model to calculate tip and total amounts
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();

            //Update the view
            tipTextView.setText(money.format(tip));
            billTotalTextView.setText(money.format(total));

        } catch(NumberFormatException nfe){
            //
        }

    }

    private class TextChangeHandler implements TextWatcher {

        public void afterTextChanged(Editable e ){
           calculate();
        }

       public void beforeTextChanged ( CharSequence s , int start, int count, int after){

       }
       public void onTextChanged (CharSequence s, int start, int before, int after){

       }
    }
}