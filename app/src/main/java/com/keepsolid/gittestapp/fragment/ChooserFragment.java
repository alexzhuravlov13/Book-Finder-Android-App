package com.keepsolid.gittestapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.activity.FirstActivity;
import com.keepsolid.gittestapp.activity.SecondActivity;
import com.keepsolid.gittestapp.adapter.SmartphoneRecyclerAdapter;
import com.keepsolid.gittestapp.model.Smartphone;
import com.keepsolid.gittestapp.utils.Constants;
import com.keepsolid.gittestapp.utils.listeners.OnSmartphoneRecyclerItemClickListener;
import com.keepsolid.gittestapp.utils.repository.SmartphoneRepository;

import java.util.List;


public class ChooserFragment extends Fragment {

    private RecyclerView recyclerView;
    private SmartphoneRecyclerAdapter adapter;
    private List<Smartphone> smartphones;
    private Listener listener;


    static interface Listener {
        void itemClicked(long id);
    };


    public ChooserFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chooser, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_recycler);

        smartphones = new SmartphoneRepository().getSmartphones();

        adapter = new SmartphoneRecyclerAdapter(smartphones);

        adapter.setListener(new OnSmartphoneRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra(Constants.KEY_RES_ID, position);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }



}