package com.example.coronavirusreport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

//Implements
public class NewsActivity extends AppCompatActivity implements NewsAdapter.OnNewsListener {
    private RecyclerView recyclerView;
    private List<News> mNewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.newsRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(NewsActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(NewsActivity.this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(itemDecoration);

        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.initialize();
        newsViewModel.getNewsLiveData().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                mNewsList = news;
                NewsAdapter newsAdapter = new NewsAdapter(news, NewsActivity.this);
                recyclerView.setAdapter(newsAdapter);
            }
        });
    }

    @Override
    public void onNewsClick(int position) {
        News thisNews = mNewsList.get(position);
        //this is where we open an in app browser(WebView)
        //an intent that open another activity containing the webview

        String url = thisNews.getNewsUrl();

        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("URL", url);
        startActivity(intent);

    }
}
