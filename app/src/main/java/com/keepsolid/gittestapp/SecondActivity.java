package com.keepsolid.gittestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.keepsolid.gittestapp.utils.Constants;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;
    private Button okButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        setListeners();
        textViewSetText();
    }

    private void setListeners() {
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOkButton();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCancelButton();
            }
        });
    }

    private void handleCancelButton() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    private void handleOkButton() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    private void textViewSetText() {
        Intent intent = getIntent();
        String messageText = intent.getStringExtra(Constants.EXTRA_TEXT);
        textView.setText(messageText);
    }

    private void initViews() {
        textView = findViewById(R.id.show_text);
        textView.setMovementMethod(new ScrollingMovementMethod());
        okButton = findViewById(R.id.ok_btn);
        cancelButton = findViewById(R.id.cancel_btn);
    }


}