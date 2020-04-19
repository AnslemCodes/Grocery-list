package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        // get those values out of the intent in main activity
        Intent intent = getIntent();
        String text  = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        int number = intent.getIntExtra(MainActivity.EXTRA_NUMBER,0);

        // we need the text views created in second activity  layout

        TextView textView1 = (TextView) findViewById(R.id.tv_1);
        TextView textView2 = (TextView) findViewById(R.id.tv_2);

        //then parse those text views

        textView1.setText(text);
        textView2.setText("" + number);

    }
}
