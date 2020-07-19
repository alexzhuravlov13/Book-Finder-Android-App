package com.keepsolid.gittestapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.model.Smartphone;
import com.keepsolid.gittestapp.utils.repository.SmartphoneRepository;

public class ViewerFragment extends Fragment {


    private static final String ID_ARG = "ID_ARG";

    private SmartphoneRepository smartphoneRepository = new SmartphoneRepository();
    private ImageView smartphoneImage;
    private TextView smartphoneName;
    private TextView smartphoneYear;

    private int smartphoneId;

    public ViewerFragment() {
    }

    public static ViewerFragment newInstance(int id) {
        ViewerFragment viewerFragment = new ViewerFragment();
        Bundle args = new Bundle();
        args.putInt(ID_ARG, id);
        viewerFragment.setArguments(args);
        return viewerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            smartphoneId = getArguments().getInt(ID_ARG);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);
        smartphoneImage = view.findViewById(R.id.smartphone_image);
        smartphoneName = view.findViewById(R.id.smartphone_name);
        smartphoneYear = view.findViewById(R.id.smartphone_year);

        displayResource(smartphoneId);

        return view;
    }

    public void displayResource(int resId) {
        Smartphone smartphone = smartphoneRepository.getSmartphoneById(resId);
        smartphoneImage.setImageResource(smartphone.getImage());
        smartphoneImage.setContentDescription(smartphone.toString());
        smartphoneName.setText(smartphone.toString());
        smartphoneYear.setText(String.valueOf(smartphone.getYear()));
    }
}