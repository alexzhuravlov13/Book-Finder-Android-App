package com.keepsolid.gittestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.keepsolid.gittestapp.utils.Constants;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        textViewSetText();
    }

    private void textViewSetText() {
        Intent intent = getIntent();
        String messageText = intent.getStringExtra(Constants.EXTRA_TEXT);
        textView.setText(messageText);
    }

    private void initViews(){
        textView = findViewById(R.id.show_text);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }


}