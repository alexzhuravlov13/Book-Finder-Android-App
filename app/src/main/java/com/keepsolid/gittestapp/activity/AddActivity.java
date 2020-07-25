package com.keepsolid.gittestapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.keepsolid.gittestapp.R;

public class AddActivity extends BaseActivity {
    public static final int NEW_SMRT_CODE = 1000;

    private TextInputEditText manufacturer;
    private TextInputEditText model;
    private TextInputEditText year;
    private MaterialButton saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initToolbar(getString(R.string.add_new_smarthpone));
        enableUpButton();

        initViews();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewSmartphone();
            }
        });
    }

    private void addNewSmartphone() {
        Editable manufacturerText = manufacturer.getText();
        Editable modelText = model.getText();
        Editable yearText = year.getText();

        if (manufacturerText.length() == 0 || modelText.length() == 0 || yearText.length() == 0) {
            new AlertDialog.Builder(this).setTitle("Error").setMessage("Fill all fields").setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            }).create().show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("manufacturer", manufacturerText.toString());
            intent.putExtra("model", modelText.toString());
            int yearInt;

            try {
                yearInt = Integer.parseInt(yearText.toString());
            } catch (Exception e) {
                yearInt = 2000;
            }
            intent.putExtra("year", yearInt);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private void initViews() {
        manufacturer = findViewById(R.id.add_manufacturer);
        model = findViewById(R.id.add_model);
        year = findViewById(R.id.add_year);
        saveBtn = findViewById(R.id.save_btn);
    }
}