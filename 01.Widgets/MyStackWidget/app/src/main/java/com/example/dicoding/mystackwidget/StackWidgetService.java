package com.example.dicoding.mystackwidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by dicoding on 1/9/2017.
 */

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}