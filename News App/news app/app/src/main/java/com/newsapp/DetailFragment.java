package com.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class DetailFragment extends Fragment {

    private static final String ARG_IMAGE = "image";
    private static final String ARG_TITLE = "title";
    private static final String ARG_DESC = "desc";

    public static DetailFragment newInstance(int imageResId, String title, String desc) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE, imageResId);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESC, desc);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ImageView img = view.findViewById(R.id.detail_image);
        TextView title = view.findViewById(R.id.detail_title);
        TextView desc = view.findViewById(R.id.detail_desc);
        RecyclerView recyclerRelated = view.findViewById(R.id.recycler_related);

        assert getArguments() != null;
        img.setImageResource(getArguments().getInt(ARG_IMAGE));
        title.setText(getArguments().getString(ARG_TITLE));
        desc.setText(getArguments().getString(ARG_DESC));

        List<NewsItem> related = getRelatedDummyNews();
        recyclerRelated.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerRelated.setAdapter(new RelatedNewsAdapter(related, item -> {
            DetailFragment detail = newInstance(item.imageResId, item.title, item.description);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, detail)
                    .addToBackStack(null)
                    .commit();
        }));

        return view;
    }

    private List<NewsItem> getRelatedDummyNews() {
        return Arrays.asList(
                new NewsItem(R.drawable.r1, "New Study Links Air Pollution to Asthma",
                        "Researchers find a strong correlation between rising urban pollution levels and increased asthma cases in children worldwide."),
                new NewsItem(R.drawable.r2, "Tech Startups Shift Toward Sustainability",
                        "Startups are focusing more on eco-friendly innovation, from zero-waste packaging to renewable-powered data centers."),
                new NewsItem(R.drawable.r3, "Rainforest Restoration Sees Success",
                        "Environmentalists in Brazil report promising regrowth in protected rainforest areas after years of aggressive reforestation efforts."),
                new NewsItem(R.drawable.r4, "UN Calls for Global Education Reform",
                        "The United Nations urges countries to adopt modern, inclusive, and digital-forward approaches to education to bridge inequality gaps.")
        );
    }

}
