package com.keepsolid.gittestapp.utils.repository;

import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.model.Smartphone;

import java.util.Arrays;
import java.util.List;

public class SmartphoneRepository {
    private List<Smartphone> smartphones;

    public SmartphoneRepository() {
        smartphones = Arrays.asList(
                new Smartphone("HTC", "Dream", 2009, R.drawable.htc_dream),
                new Smartphone("Motorola", "Moto X", 2013, R.drawable.moto_x),
                new Smartphone("Google", "Pixel", 2016, R.drawable.google_pixel),
                new Smartphone("Samsung", "Galaxy S10", 2019, R.drawable.samsung_galaxy_s10)
        );

    }

    public Smartphone getSmartphoneById(int id) {
        return smartphones.get(id);
    }
}
