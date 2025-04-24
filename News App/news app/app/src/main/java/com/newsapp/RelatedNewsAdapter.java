package com.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.RelatedNewsViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(NewsItem item);
    }

    private List<NewsItem> relatedNewsList;
    private OnItemClickListener listener;

    public RelatedNewsAdapter(List<NewsItem> list, OnItemClickListener listener) {
        this.relatedNewsList = list;
        this.listener = listener;
    }

    public static class RelatedNewsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView description;

        public RelatedNewsViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.related_news_image);
            title = view.findViewById(R.id.related_news_title);
            description = view.findViewById(R.id.related_news_description);
        }

        public void bind(final NewsItem item, final OnItemClickListener listener) {
            image.setImageResource(item.imageResId);
            title.setText(item.title);
            description.setText(item.description);
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }

    @Override
    public RelatedNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_related_news, parent, false);
        return new RelatedNewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RelatedNewsViewHolder holder, int position) {
        holder.bind(relatedNewsList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return relatedNewsList.size();
    }
}
