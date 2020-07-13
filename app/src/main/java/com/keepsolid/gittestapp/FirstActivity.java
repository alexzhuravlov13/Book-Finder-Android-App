package com.keepsolid.gittestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.keepsolid.gittestapp.base.BaseActivity;
import com.keepsolid.gittestapp.utils.Constants;

public class FirstActivity extends BaseActivity {
    private EditText enterTextField;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initViews();
        setListeners();
        initToolbar(this.getClass().getSimpleName());
    }

    private void initViews() {
        enterTextField = findViewById(R.id.enter_text_field);
        enterTextField.setVerticalScrollBarEnabled(true);
        sendBtn = findViewById(R.id.send_button);

    }

    private void setListeners() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredText = enterTextField.getText().toString();
                if (enteredText.trim().isEmpty()) {
                    showToast("Empty field");
                } else {
                    openSecondActivityForResult(enteredText);
                }
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(FirstActivity.this, text, Toast.LENGTH_LONG).show();
    }

    public void openSecondActivityForResult(String text) {
        Intent dataIntent = new Intent(FirstActivity.this, SecondActivity.class);
        dataIntent.putExtra(Constants.EXTRA_TEXT, text);
        startActivityForResult(dataIntent, Constants.TEXT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            showToast("Success!");
        } else {
            enterTextField.setText(null);
        }
    }
}
