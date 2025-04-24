package com.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerTop, recyclerNews;
    private List<NewsItem> topList, newsList;

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerTop = view.findViewById(R.id.recycler_top);
        recyclerNews = view.findViewById(R.id.recycler_news);

        topList = getDummyTopStories();
        newsList = getDummyNews();

        recyclerTop.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerNews.setLayoutManager(
                new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));

        NewsAdapter.OnItemClickListener listener = item -> openDetail(item);
        StoryAdapter.OnItemClickListener storyListener = item -> openDetail(item);
        recyclerTop.setAdapter(new StoryAdapter(topList, storyListener));
        recyclerNews.setAdapter(new NewsAdapter(newsList, listener));

        return view;
    }

    private void openDetail(NewsItem item) {
        DetailFragment detail = DetailFragment.newInstance(item.imageResId, item.title, item.description);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detail)
                .addToBackStack(null)
                .commit();
    }

    private List<NewsItem> getDummyTopStories() {
        return Arrays.asList(
                new NewsItem(R.drawable.news3, "Wildfire Spreads in California Hills",
                        "Firefighters battle intense wildfires near Los Angeles as dry winds push flames across residential areas."),
                new NewsItem(R.drawable.news4, "NASA Launches New Mars Rover",
                        "NASA's latest mission to Mars begins with the launch of the Perseverance rover, aiming to uncover signs of ancient life."),
                new NewsItem(R.drawable.news5, "Global Leaders Meet for Climate Summit",
                        "World leaders gather in Geneva to discuss urgent climate reforms, pledging to reduce emissions and invest in green technologies."),
                new NewsItem(R.drawable.news2, "Massive Snowstorm Hits New York",
                        "Heavy snowfall blankets the northeastern United States, disrupting traffic and flights as emergency crews work through the night.")
        );
    }


    private List<NewsItem> getDummyNews() {
        return Arrays.asList(
                new NewsItem(R.drawable.n1, "AI Tech Transforms Healthcare Industry",
                        "Hospitals are adopting AI to improve diagnostics, automate admin tasks, and assist in patient treatment planning across departments."),
                new NewsItem(R.drawable.n2, "Solar Power Usage Surges in Europe",
                        "A new report shows that solar energy adoption across Europe has doubled this year due to subsidies and rising fuel costs."),
                new NewsItem(R.drawable.n3, "Local School Wins Robotics Championship",
                        "A high school robotics team from Texas wins international title, showcasing innovation and teamwork on a global stage."),
                new NewsItem(R.drawable.n4, "New Wildlife Sanctuary Opens in India",
                        "India opens a vast new wildlife reserve in Assam to protect endangered species and promote eco-tourism in the region."),
                new NewsItem(R.drawable.n5, "Electric Cars Dominate Auto Show",
                        "Automakers unveil their latest electric vehicles at the Tokyo Motor Show, focusing on range, speed, and sustainable design."),
                new NewsItem(R.drawable.n6, "Ocean Cleanup Device Hits Milestone",
                        "An ocean-cleaning device designed by engineers has successfully removed over 10,000 pounds of plastic waste from the Pacific.")
        );
    }

}
