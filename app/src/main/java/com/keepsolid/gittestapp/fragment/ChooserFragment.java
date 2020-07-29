package com.keepsolid.gittestapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.utils.listeners.OnBookRecyclerItemClickListener;
import com.keepsolid.gittestapp.utils.repository.SmartphoneRepository;

import java.util.List;


public class ChooserFragment extends Fragment {

    private RecyclerView recyclerView;
    private SmartphoneRecyclerAdapter adapter;
    private List<Smartphone> smartphones;

    public ChooserFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        smartphones = SmartphoneRepository.getInstance().getSmartphones();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chooser, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_recycler);

        adapter = new SmartphoneRecyclerAdapter(smartphones);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }


    public void update() {
        adapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(recyclerView.getBottom());
    }

    public void setSmartphoneSelectListener(OnBookRecyclerItemClickListener smartphoneSelectListener) {
        adapter.setListener(smartphoneSelectListener);
    }
}