package com.keepsolid.bookfinderapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.keepsolid.bookfinderapp.R;
import com.keepsolid.bookfinderapp.model.BookItem;
import com.keepsolid.bookfinderapp.model.ImageLinks;
import com.keepsolid.bookfinderapp.model.VolumeItem;
import com.keepsolid.bookfinderapp.utils.listeners.OnBookRecyclerItemClickListener;

import java.util.ArrayList;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder> {
    private ArrayList<BookItem> items;
    private Context ctx;
    private OnBookRecyclerItemClickListener listener;

    public void setListener(OnBookRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    public BookRecyclerAdapter(ArrayList<BookItem> items, Context ctx, OnBookRecyclerItemClickListener listener) {
        this.items = items;
        this.ctx = ctx;
        this.listener = listener;
    }

    public BookRecyclerAdapter(ArrayList<BookItem> items, Context ctx) {
        this.items = items;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_book_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BookRecyclerAdapter.ViewHolder holder, int position) {
        BookItem bookItem = items.get(position);
        VolumeItem volumeInfo = bookItem.getVolumeInfo();

        if (TextUtils.isEmpty(volumeInfo.getPublishedDate())) {
            holder.publishedDate.setVisibility(View.GONE);
        } else {
            String year = volumeInfo.getPublishedDate().split("-")[0];
            holder.publishedDate.setText(year);
            holder.publishedDate.setVisibility(View.VISIBLE);
        }
        Log.i("BOOKITEM", bookItem.toString());

        holder.title.setText(bookItem.getVolumeInfo().getTitle());
        ImageLinks imageLinks = bookItem.getVolumeInfo().getImageLinks();
        if (imageLinks != null && imageLinks.getSmallThumbnail() != null) {
            Glide.with(holder.thumbnail.getContext()).load(imageLinks.getSmallThumbnail()).placeholder(R.drawable.book).into(holder.thumbnail);
        } else {
            Glide.with(holder.thumbnail.getContext()).load(R.drawable.book).into(holder.thumbnail);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(view, holder.getAdapterPosition(), items.get(holder.getAdapterPosition()).getVolumeInfo());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView publishedDate;
        ImageView thumbnail;
        View container;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            publishedDate = itemView.findViewById(R.id.publishedDate);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            container = itemView;

        }
    }
}
