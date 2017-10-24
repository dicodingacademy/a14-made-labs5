package com.example.dicoding.mywidgets;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created by dicoding on 12/28/2016.
 */

public class UpdateWidgetService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        AppWidgetManager manager = AppWidgetManager.getInstance(this);

        RemoteViews view = new RemoteViews(getPackageName(), R.layout.bilangan_acak_widget);
        ComponentName theWidget = new ComponentName(this, BilanganAcakWidget.class);

        String lastUpdate = "Random: "+NumberGenerator.Generate(100);

        view.setTextViewText(R.id.appwidget_text, lastUpdate);

        manager.updateAppWidget(theWidget, view);

        jobFinished(jobParameters, false);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
