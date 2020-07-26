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

        initListeners();
    }

    private void initListeners() {
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

        if (manufacturerText != null && manufacturerText.toString().trim().length() == 0 ||
                modelText != null && modelText.toString().trim().length() == 0 ||
                yearText != null && yearText.toString().trim().length() == 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Fill all fields")
                    .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
                    .create()
                    .show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("manufacturer", manufacturerText.toString().trim());
            intent.putExtra("model", modelText.toString().trim());
            int yearInt;

            try {
                String year = yearText.toString();
                if (year.length() != 4) {
                    throw new Exception("Year length must be 4");
                }
                yearInt = Integer.parseInt(year);
                intent.putExtra("year", yearInt);
                setResult(RESULT_OK, intent);
                finish();
            } catch (Exception e) {
                new AlertDialog.Builder(this).
                        setTitle("Error")
                        .setMessage("Year must be in number format \"yyyy\"")
                        .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        })
                        .create()
                        .show();
            }
        }
    }

    private void initViews() {
        manufacturer = findViewById(R.id.add_manufacturer);
        model = findViewById(R.id.add_model);
        year = findViewById(R.id.add_year);
        saveBtn = findViewById(R.id.save_btn);
    }
}