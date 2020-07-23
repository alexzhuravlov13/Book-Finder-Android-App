package com.keepsolid.gittestapp.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        manufacturer = findViewById(R.id.add_manufacturer);
        model = findViewById(R.id.add_model);
        year = findViewById(R.id.add_year);
        saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("manufacturer", manufacturer.getText().toString());
                intent.putExtra("model", model.getText().toString());
                int yearInt;
                try {
                    yearInt = Integer.parseInt(year.getText().toString());
                }
                catch (Exception e) {
                    yearInt = 2000;
                }
                intent.putExtra("year", yearInt);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}