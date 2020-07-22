package com.keepsolid.gittestapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.model.Smartphone;
import com.keepsolid.gittestapp.utils.listeners.OnSmartphoneRecyclerItemClickListener;
import com.keepsolid.gittestapp.utils.repository.SmartphoneRepository;

import java.util.List;

public class SmartphoneRecyclerAdapter extends RecyclerView.Adapter<SmartphoneRecyclerAdapter.ViewHolder> {
    private List<Smartphone> smartphones;
    private OnSmartphoneRecyclerItemClickListener listener;

    public SmartphoneRecyclerAdapter(List<Smartphone> smartphones) {
        this.smartphones = smartphones;
    }

    @Override
    public SmartphoneRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chooser_list_smartphone, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onItemClick(view, viewHolder.getAdapterPosition());
                }
            }
        });

        return viewHolder;
    }

    public void setListener(OnSmartphoneRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Smartphone smartphone = smartphones.get(position);
        SmartphoneRepository smartphoneRepository = new SmartphoneRepository();

        holder.smartphoneBrandImage.setImageResource(smartphoneRepository.getLogoIdByBrand(smartphone.getManufacturer()));
        holder.smartphoneName.setText(smartphone.toString());
        holder.smartphoneYear.setText(String.valueOf(smartphone.getYear()));

        if(position == smartphones.size() -1){
            holder.divider.setVisibility(View.INVISIBLE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return smartphones.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView smartphoneBrandImage;
        TextView smartphoneName;
        TextView smartphoneYear;
        View divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            smartphoneBrandImage = itemView.findViewById(R.id.smartphone_brand_image);
            smartphoneName = itemView.findViewById(R.id.smartphone_name);
            smartphoneYear = itemView.findViewById(R.id.smartphone_year);
            divider = itemView.findViewById(R.id.divider);
        }
    }
}
