package com.keepsolid.gittestapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.fragment.ChooserFragment;
import com.keepsolid.gittestapp.model.Smartphone;
import com.keepsolid.gittestapp.utils.repository.SmartphoneRepository;

import static com.keepsolid.gittestapp.activity.AddActivity.NEW_SMRT_CODE;

public class FirstActivity extends BaseActivity {
    private ChooserFragment chooserFragment;
    private FloatingActionButton addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initToolbar(getString(R.string.app_name));

        chooserFragment = (ChooserFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chooser);

        addBtn = findViewById(R.id.add_smrt);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, AddActivity.class);
                startActivityForResult(intent, NEW_SMRT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String manufacturer = data.getStringExtra("manufacturer");
        String model = data.getStringExtra("model");
        int year = data.getIntExtra("year", 2000);
        int image = R.drawable.default_logo;
        SmartphoneRepository smartphoneRepository = SmartphoneRepository.getInstance();

        try {
            image = smartphoneRepository.getLogoIdByBrand(manufacturer);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Smartphone smartphone = new Smartphone(manufacturer, model, year, image);
        smartphoneRepository.addSmartphone(smartphone);

        chooserFragment.update();
    }

}