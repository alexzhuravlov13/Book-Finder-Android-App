package com.keepsolid.gittestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.keepsolid.gittestapp.utils.Constants;

public class FirstActivity extends AppCompatActivity {
    private EditText enterTextField;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initViews();
        setListeners();
    }

    private void initViews() {
        enterTextField = findViewById(R.id.enter_text_field);
        sendBtn = findViewById(R.id.send_btn);

    }

    private void setListeners() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredText = enterTextField.getText().toString();
                if(enteredText.trim().isEmpty()){
                    Toast.makeText(FirstActivity.this, "Empty field", Toast.LENGTH_LONG).show();
                }
                else {
                    openSecondActivityWithData(enteredText);
                }
            }
        });
    }

    public void openSecondActivityWithData(String text){
        Intent dataIntent = new Intent(FirstActivity.this, SecondActivity.class);
        dataIntent.putExtra(Constants.EXTRA_TEXT, text);
        startActivityForResult(dataIntent, Constants.TEXT_REQUEST_CODE);
    }


}
