package com.example.android.myapplication;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("practice");
        textView.setAllCaps(true);
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(18);


        setContentView(textView);
    }
}
