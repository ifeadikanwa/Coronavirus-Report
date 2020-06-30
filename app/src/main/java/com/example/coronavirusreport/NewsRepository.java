package com.example.coronavirusreport;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsRepository {
    private static NewsRepository instance;
    private List<News> newsArr = new ArrayList<>();
    private OkHttpClient client = new OkHttpClient();
    private MutableLiveData<List<News>> mutableNewsList = new MutableLiveData<>();
    private LiveData<List<News>> newsList = mutableNewsList;

    private NewsRepository(){
    }

    public static NewsRepository getInstance(){
        instance = new NewsRepository();
        return instance;
    }

    public LiveData<List<News>> getNewsList(){
        loadNewsList();
        return newsList;
    }

    private void loadNewsList() {
        Request request = new Request.Builder()
                .url("https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/Search/NewsSearchAPI?autoCorrect=false&pageNumber=1&pageSize=50&q=coronavirus%20usa&safeSearch=false")
                .get()
                .addHeader("x-rapidapi-host", "contextualwebsearch-websearch-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "888c012920msh3b306e7cac1899ap11c722jsnd21797080252")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String jsonStr = response.body().string();

                    try {
                        JSONObject jsonObject = new JSONObject(jsonStr);
                        JSONArray valueArr = jsonObject.getJSONArray("value");
                        for(int i = 0; i < valueArr.length(); i++){
                            JSONObject newsObj = valueArr.getJSONObject(i);
                            String rawTitle = newsObj.getString("title");
                            String newsUrl = newsObj.getString("url");

                            JSONObject imgObj = newsObj.getJSONObject("image");
                            String imgUrl = imgObj.getString("thumbnail");

//                            String finalTitle = null;
//                            String pattern = "[^<b>]+";
//                            Pattern p = Pattern.compile(pattern);
//                            Matcher m = p.matcher(rawTitle);
//                            while ((m.find())){
//                                finalTitle = m.group();
//                            }
//                            if(finalTitle == null){
//                                finalTitle = rawTitle;
//                            }

                            News instance = new News(rawTitle, newsUrl, imgUrl);
                            newsArr.add(instance);
                        }

                        mutableNewsList.postValue(newsArr);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    throw new IOException("Unexpected code " + response);
                }
            }
        });
    }
}
