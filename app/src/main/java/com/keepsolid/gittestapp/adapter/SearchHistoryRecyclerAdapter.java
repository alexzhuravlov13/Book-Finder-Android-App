package com.keepsolid.gittestapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.activity.SearchHistoryActivity;
import com.keepsolid.gittestapp.utils.listeners.OnHistoryRecyclerItemClickListener;

import java.util.List;

public class SearchHistoryRecyclerAdapter extends RecyclerView.Adapter<SearchHistoryRecyclerAdapter.ViewHolder> {
    private List<String> items;
    private Context ctx;
    private OnHistoryRecyclerItemClickListener listener;

    public SearchHistoryRecyclerAdapter(List<String> items, SearchHistoryActivity searchHistoryActivity, OnHistoryRecyclerItemClickListener onHistoryRecyclerItemClickListener) {
        this.items = items;
        this.ctx = searchHistoryActivity;
        this.listener = onHistoryRecyclerItemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.historyQuery.setText(items.get(position));
        holder.container.setOnClickListener(view -> {
            if (listener != null) {
                listener.onItemClick(view, holder.getAdapterPosition(), items.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView historyQuery;
        View container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            historyQuery = itemView.findViewById(R.id.history_query);
            container = itemView;
        }
    }
}
