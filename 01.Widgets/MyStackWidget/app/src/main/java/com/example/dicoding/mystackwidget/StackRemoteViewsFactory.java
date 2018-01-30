package com.example.dicoding.mystackwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dicoding on 1/9/2017.
 */


class StackRemoteViewsFactory implements
        RemoteViewsService.RemoteViewsFactory {

    private List<Bitmap> mWidgetItems = new ArrayList<>();
    private Context mContext;
    private int mAppWidgetId;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    public void onCreate() {

        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.darth_vader));
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.star_wars_logo));
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.storm_trooper));

        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.starwars));

        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.falcon));

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        rv.setImageViewBitmap(R.id.imageView, mWidgetItems.get(position));

        Bundle extras = new Bundle();
        extras.putInt(ImagesBannerWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


}