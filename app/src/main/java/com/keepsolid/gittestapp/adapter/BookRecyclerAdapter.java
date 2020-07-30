package com.keepsolid.gittestapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.keepsolid.gittestapp.R;
import com.keepsolid.gittestapp.model.BookItem;
import com.keepsolid.gittestapp.utils.listeners.OnBookRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder> {
    private ArrayList<BookItem> items;
    private Context ctx;
    private OnBookRecyclerItemClickListener listener;

    public BookRecyclerAdapter(ArrayList<BookItem> items, Context ctx, OnBookRecyclerItemClickListener listener) {
        this.items = items;
        this.ctx = ctx;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BookRecyclerAdapter.ViewHolder holder, int position) {
        BookItem bookItem = items.get(position);
        Log.e("BOOKITEM", bookItem.toString());
        holder.title.setText("Title");
        holder.publishedDate.setText("1995");
        Glide.with(holder.thumbnail).load(R.drawable.book).placeholder(R.drawable.ic_account_multiple_grey600_24dp);

        /*holder.title.setText(bookItem.getTitle());
        holder.publishedDate.setText(bookItem.getPublishedDate());*/
        //Glide.with(holder.thumbnail).load(bookItem.getThumbnail()).placeholder(R.drawable.book);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null) {
                    listener.onItemClick(view, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
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
