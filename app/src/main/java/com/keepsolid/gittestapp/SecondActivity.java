package com.keepsolid.gittestapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.keepsolid.gittestapp.base.BaseActivity;
import com.keepsolid.gittestapp.utils.Constants;

public class SecondActivity extends BaseActivity {
    private AppCompatTextView textView;
    private AppCompatButton okButton;
    private AppCompatButton cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        setListeners();
        textViewSetText();
        initToolbar(this.getClass().getSimpleName());
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
        okButton = findViewById(R.id.ok_button);
        cancelButton = findViewById(R.id.cancel_button);
    }


}