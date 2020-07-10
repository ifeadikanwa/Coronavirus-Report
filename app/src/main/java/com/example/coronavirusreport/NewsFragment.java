package com.example.coronavirusreport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class NewsFragment extends Fragment implements NewsAdapter.OnNewsListener {

    private RecyclerView recyclerView;
    private List<News> mNewsList;
    private NewsAdapter newsAdapter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.newsRecyclerView);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(itemDecoration);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.i("OnActivity", "in here");

        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.initialize();
        newsViewModel.getNewsLiveData().observe(getViewLifecycleOwner(), new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                Log.i("OnActivity", "in the on onchanged method");
                if(news != null){
                    mNewsList = news;
                    newsAdapter = new NewsAdapter(news, NewsFragment.this);
                    recyclerView.setAdapter(newsAdapter);
                }
            }
        });
    }

    @Override
    public void onNewsClick(int position) {
        News thisNews = mNewsList.get(position);
        String url = thisNews.getNewsUrl();
        Uri web = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, web);
        if(intent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivity(intent);
        }
    }
}