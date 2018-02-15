package com.blackscreen.listbrnews.controller;

import android.content.Context;

import com.blackscreen.listbrnews.R;
import com.blackscreen.listbrnews.connection.JSONConnection;
import com.blackscreen.listbrnews.models.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Hamon on 14/02/2018.
 */

public class MainAcitivityController {

    private Context mContext;

    public MainAcitivityController(Context mContext) {
        this.mContext = mContext;
    }

    public List<NewsModel> getNews () {

        String jsonAsString = JSONConnection.getJsonAsString("https://newsapi.org/v2/top-headlines?country=" + Locale.getDefault().getCountry() +
                "&apiKey=" + mContext.getResources().getString(R.string.news_api_key) + "&pageSize=100&page=1");

        List<NewsModel> newsModelList = new ArrayList<>();

        try {
            JSONObject mainObj = new JSONObject(jsonAsString);
            JSONArray articles = mainObj.getJSONArray("articles");
            System.out.println(articles);

            //Get News
            for (int i = 0; i < articles.length(); i++) {
                JSONObject article = articles.getJSONObject(i);
                JSONObject source = article.getJSONObject("source");

                //Add new "NewsModel" on list
                newsModelList.add(new NewsModel(article.getString("title"),
                        source.getString("name"),
                        formatArticleDate(article.getString("publishedAt")),
                        article.getString("url"),
                        article.getString("urlToImage"),
                        new String(article.getString("description"))));

            }

            return newsModelList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    private String formatArticleDate (String articleDate) {

        articleDate = articleDate.replace("Z", "").replace("T", " ");
        Date date;

        try {

            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(articleDate);
            return new SimpleDateFormat().format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

}
