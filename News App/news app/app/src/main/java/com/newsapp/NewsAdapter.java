package com.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(NewsItem item);
    }

    private List<NewsItem> newsList;
    private OnItemClickListener listener;

    public NewsAdapter(List<NewsItem> list, OnItemClickListener listener) {
        this.newsList = list;
        this.listener = listener;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView description;

        public NewsViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.news_image);
            title = view.findViewById(R.id.news_title);
            description = view.findViewById(R.id.news_description);
        }

        public void bind(final NewsItem item, final OnItemClickListener listener) {
            image.setImageResource(item.imageResId);
            title.setText(item.title);
            description.setText(item.description);
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bind(newsList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
