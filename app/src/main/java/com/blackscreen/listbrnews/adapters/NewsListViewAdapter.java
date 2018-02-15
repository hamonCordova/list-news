package com.blackscreen.listbrnews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackscreen.listbrnews.R;
import com.blackscreen.listbrnews.interfaces.ViewToAdapter;
import com.blackscreen.listbrnews.models.NewsModel;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.sql.SQLOutput;
import java.util.List;

/**
 * Created by Hamon on 14/02/2018.
 */

public class NewsListViewAdapter extends BaseAdapter {

    private List<NewsModel> mListNewsModels;
    private Context mContext;
    private ViewToAdapter.MainActivityNewsListViewInterface mMainInterface;

    public NewsListViewAdapter(List<NewsModel> mListNewsModels, Context mContext, ViewToAdapter.MainActivityNewsListViewInterface mainInterface) {
        this.mListNewsModels = mListNewsModels;
        this.mContext = mContext;
        this.mMainInterface = mainInterface;
    }

    @Override
    public int getCount() {
        return mListNewsModels.size();
    }

    @Override
    public Object getItem(int i) {
        return mListNewsModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //Inflate view
        view = LayoutInflater.from(mContext).inflate(R.layout.news_listview_item, null, false);

        //Get XML elements and get current model
        TextView txtvTitle = view.findViewById(R.id.news_listview_item_title);
        TextView txtvDate = view.findViewById(R.id.news_listview_item_date);
        TextView txtvPublisher = view.findViewById(R.id.news_listview_item_publisher);
        TextView txtvDescription = view.findViewById(R.id.news_listview_item_description);
        ImageView newsPicture = view.findViewById(R.id.news_listview_item_image);

        final NewsModel model = mListNewsModels.get(i);

        //Set content inside elements
        txtvTitle.setText(model.getTitle());
        txtvDescription.setText(model.getDescription());
        txtvPublisher.setText(model.getPublisher());
        txtvDate.setText(model.getPublishDateAsString());


        if (!model.getImageUrl().equals("null")) {
            newsPicture.setBackground(null);
            Picasso.with(mContext).load(model.getImageUrl()).fit().into(newsPicture);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainInterface.openInternetPage(model.getNewsUrl());
            }
        });

        return view;

    }
}
