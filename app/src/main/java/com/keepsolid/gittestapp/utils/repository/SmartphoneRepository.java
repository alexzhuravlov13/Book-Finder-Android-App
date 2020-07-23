package com.keepsolid.gittestapp.utils.repository;

import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.model.Smartphone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartphoneRepository {
    private static List<Smartphone> smartphones;
    private static Map<String, Integer> brandImages;
    private static SmartphoneRepository instance;

    public static SmartphoneRepository getInstance() {
        if (instance == null) {
            instance = new SmartphoneRepository();
        }
        return instance;
    }

    private SmartphoneRepository() {
        smartphones = new ArrayList<>(Arrays.asList(
                new Smartphone("HTC", "Dream", 2009, R.drawable.htc_dream),
                new Smartphone("Motorola", "Moto X", 2013, R.drawable.moto_x),
                new Smartphone("Google", "Pixel", 2016, R.drawable.google_pixel),
                new Smartphone("Google", "Pixel", 2016, R.drawable.google_pixel),
                new Smartphone("Google", "Pixel", 2016, R.drawable.google_pixel),
                new Smartphone("Google", "Pixel", 2016, R.drawable.google_pixel),
                new Smartphone("Google", "Pixel", 2016, R.drawable.google_pixel),
                new Smartphone("Google", "Pixel", 2016, R.drawable.google_pixel),
                new Smartphone("Google", "Pixel", 2016, R.drawable.google_pixel),
                new Smartphone("Google", "Pixel", 2016, R.drawable.google_pixel),
                new Smartphone("Samsung", "Galaxy S10", 2019, R.drawable.samsung_galaxy_s10)
        ));

        brandImages = new HashMap<>();
        brandImages.put("HTC", R.drawable.htc_logo);
        brandImages.put("Motorola", R.drawable.motorola_logo);
        brandImages.put("Google", R.drawable.google_logo);
        brandImages.put("Samsung", R.drawable.samsung_logo);

    }

    public List<Smartphone> getSmartphones() {
        return smartphones;
    }

    public Smartphone getSmartphoneById(int id) {
        return smartphones.get(id);
    }

    public int getLogoIdByBrand(String brand) {
        Integer integer = brandImages.get(brand);
        if (integer == null) {
            return R.drawable.default_logo;
        }
        return integer;
    }

    public void addSmartphone(Smartphone smartphone) {
        smartphones.add(smartphone);
        if (!brandImages.containsKey(smartphone.getManufacturer())) {
            brandImages.put(smartphone.getManufacturer(), R.drawable.default_logo);
        }
    }
}
