package com.keepsolid.gittestapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.utils.listeners.SmartphoneSelectListener;

public class ChooserFragment extends Fragment {
    private MaterialButton htcButton;
    private MaterialButton pixelButton;
    private MaterialButton galaxyButton;
    private MaterialButton motoButton;

    private SmartphoneSelectListener smartphoneSelectListener;

    public ChooserFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chooser, container, false);

        htcButton = view.findViewById(R.id.btn_htc);
        pixelButton = view.findViewById(R.id.btn_pixel);
        galaxyButton = view.findViewById(R.id.btn_galaxy);
        motoButton = view.findViewById(R.id.btn_moto);

        htcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (smartphoneSelectListener != null) {
                    smartphoneSelectListener.onHtcSelected();
                }
            }
        });

        pixelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (smartphoneSelectListener != null) {
                    smartphoneSelectListener.onPixelSelected();
                }
            }
        });

        galaxyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (smartphoneSelectListener != null) {
                    smartphoneSelectListener.onGalaxySelected();
                }
            }
        });

        motoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (smartphoneSelectListener != null) {
                    smartphoneSelectListener.onMotoSelected();
                }
            }
        });

        return view;
    }

    public void setSmartphoneSelectListener(SmartphoneSelectListener smartphoneSelectListener) {
        this.smartphoneSelectListener = smartphoneSelectListener;
    }
}