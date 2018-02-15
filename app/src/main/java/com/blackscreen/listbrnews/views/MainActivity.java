package com.blackscreen.listbrnews.views;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.blackscreen.listbrnews.R;
import com.blackscreen.listbrnews.adapters.NewsListViewAdapter;
import com.blackscreen.listbrnews.controller.MainAcitivityController;
import com.blackscreen.listbrnews.interfaces.ViewToAdapter;
import com.blackscreen.listbrnews.models.NewsModel;

import java.util.List;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity implements ViewToAdapter.MainActivityNewsListViewInterface {

    private ListView mNewsListView;
    private SpotsDialog mSpotsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Get XML Elements
        this.mNewsListView = findViewById(R.id.act_main_news_listview);
        this.mNewsListView.setDivider(null);

    }

    @Override
    protected void onResume() {
        super.onResume();
        new AsyncGetNews().execute();
    }

    private void createSpotsDialog (String message) {

        if (mSpotsDialog != null && mSpotsDialog.isShowing()) {
            mSpotsDialog.dismiss();
        }

        mSpotsDialog = new SpotsDialog(this, message);
        mSpotsDialog.show();

    }

    private void populateNews(List<NewsModel> listNewsModels) {

        mNewsListView.setAdapter(new NewsListViewAdapter(listNewsModels, this, this));

    }

    @Override
    public void openInternetPage(String stringUrl) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(stringUrl));
        startActivity(i);
    }

    protected class AsyncGetNews extends AsyncTask<Void, Void, List<NewsModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            createSpotsDialog("Loading News");
        }

        @Override
        protected List<NewsModel> doInBackground(Void... voids) {
            return new MainAcitivityController(MainActivity.this).getNews();
        }

        @Override
        protected void onPostExecute(List<NewsModel> newsModels) {
            super.onPostExecute(newsModels);

            if (newsModels != null) {
                populateNews(newsModels);
            } else {
                Toast.makeText(MainActivity.this, "An error ocurrs, was not possible get news, try later.", Toast.LENGTH_SHORT).show();
            }

            mSpotsDialog.dismiss();
        }

    }

}
