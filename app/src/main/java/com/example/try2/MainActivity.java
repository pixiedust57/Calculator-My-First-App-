package com.example.try2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView s,zero, one, two, three, four, five, six, seven, eight, nine, div, mul, dot, add, sub, bs, ac, equal;
    TextView operation;
    TextView result;
    String input = "", output;
    ArrayList<String> operands = new ArrayList<>();
    double finalresult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineHooks();
        setListener();
        result.setVisibility(View.GONE);
        operation.setText("0.00");


    }

    private void setListener() {
        s.setOnClickListener(this);
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        div.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        dot.setOnClickListener(this);
        bs.setOnClickListener(this);
        ac.setOnClickListener(this);
        equal.setOnClickListener(this);


    }

    private void defineHooks() {

        s = findViewById(R.id.spell);
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        div = findViewById(R.id.divide);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.subtract);
        mul = findViewById(R.id.multiply);
        dot = findViewById(R.id.dot);
        result = findViewById(R.id.result);
        bs = findViewById(R.id.backspace);
        ac = findViewById(R.id.Aclear);
        equal = findViewById(R.id.equals);

        operation = findViewById(R.id.operation);
    }

    @Override
    public void onClick(View view) {
        if (view == zero) {
            operation("0");
        } else if (view == one) {
            operation("1");
        } else if (view == two) {
            operation("2");
        } else if (view == three) {
            operation("3");
        } else if (view == four) {
            operation("4");
        } else if (view == five) {
            operation("5");
        } else if (view == six) {
            operation("6");
        } else if (view == seven) {
            operation("7");
        } else if (view == eight) {
            operation("8");
        } else if (view == nine) {
            operation("9");
        } else if (view == dot) {
            operation(".");
        } else if (view == equal) {
            operation("result");
        } else if (view == ac) {
            operation("ac");
        } else if (view == bs) {
            operation("<-");
        } else if (view == mul) {
            operation(" \u00D7 ");
        } else if (view == sub) {
            operation(" - ");
        } else if (view == add) {
            operation(" + ");
        } else if (view == div) {
            operation(" \u00F7 ");
        } else if (view == s) {
            operation("Murali Karthik :)");
        }
    }

    private void operation(String no) {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(80);


        if (no.equals(("ac"))) {
            input = "";
            result.setText("");


        } else if (no.equals("<-")) {

            if (!input.equals("")) {
                Character last = input.charAt(input.length() - 1);
                if (last.equals(' ')) {
                    input = input.substring(0, input.length() - 2);
                }
                input = input.substring(0, input.length() - 1);
            }
        } else if (no.equals("result")) {
            getResult();
        }
        else {
            boolean isTwiceopr = false;
            String prev = "";
            if (input.length() > 1) {
                prev = input.substring(input.length() - 2, input.length() - 1);
            }
            if ((prev.equals("+") || prev.equals("\u00D7")  || prev.equals("-") || prev.equals(".") || prev.equals("\u00F7")) && (no.equals(" - ")  || no.equals(" + ") || no.equals(" \u00D7 ") || no.equals(" \u00F7 ") || no.equals(" . "))) {
                isTwiceopr = true;
            }
            if (input.equals("") && (no.equals(" + ") || no.equals(" - ") || no.equals(" \u00D7 ")  || no.equals(" \u00F7 ") || no.equals(" . "))) {
                isTwiceopr = true;
            }
            if (!isTwiceopr) {
                input = input + no;
            }
        }
        operation.setText(input);

    }

    private void getResult() {
        result.setVisibility(View.VISIBLE);
        operands.clear();
        String dup = input;
        String value = "";
        dup = dup + " ";
        int len = dup.length();
        Character ch;
        for (int i = 0; i < len; i++) {
            ch = dup.charAt(i);
            if (ch.equals(' ')) {
                value = value.trim();
                operands.add(value);
                value = "";

            }
            value = value + ch;
        }
        getAns("\u00F7");
        getAns("\u00D7");
        getAns("+");
        getAns("-");
        finalresult = Double.parseDouble(operands.get(0));
        result.setText(String.valueOf(finalresult));

    }

    private void getAns(String operator) {
        double len = operands.size();
        for(int j=0;j<len;j++){
        for(int i = 0; i<len;i++) {
            if (operands.get(i).equals(operator)) {
                if (operands.get(i).equals("\u00F7")){
                    //perform div
                    finalresult = Double.parseDouble(operands.get(i - 1)) / Double.parseDouble(operands.get(i + 1));
                } else if (operands.get(i).equals("\u00D7")) {
                    //perform mul
                    finalresult = Double.parseDouble(operands.get(i - 1)) * Double.parseDouble(operands.get(i + 1));
                } else if (operands.get(i).equals("+")) {
                    //perform add
                    finalresult = Double.parseDouble(operands.get(i - 1)) + Double.parseDouble(operands.get(i + 1));
                } else if (operands.get(i).equals("-")) {
                    //perform sub
                    finalresult = Double.parseDouble(operands.get(i - 1)) - Double.parseDouble(operands.get(i + 1));
                }
                operands.remove(i - 1);
                operands.add(i-1,String.valueOf(finalresult));
                operands.remove(i + 1);
                operands.remove(i);
                len = len - 2;
                break;
            }


        }
        }

    }
}