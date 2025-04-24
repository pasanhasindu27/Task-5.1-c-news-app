package com.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(NewsItem item);
    }

    private List<NewsItem> storyList;
    private OnItemClickListener listener;

    public StoryAdapter(List<NewsItem> storyList, OnItemClickListener listener) {
        this.storyList = storyList;
        this.listener = listener;
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public StoryViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.story_image);
        }

        public void bind(final NewsItem item, final OnItemClickListener listener) {
            image.setImageResource(item.imageResId);
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story, parent, false);
        return new StoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        holder.bind(storyList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }
}

