package com.dicoding.picodiploma.mywidgets;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int JOB_ID = 100;
    private static final int SCHEDULE_OF_PERIOD = 86000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_start:
                startJob();
                break;

            case R.id.btn_stop:
                cancelJob();
                break;
            default:
                break;
        }
    }

    private void startJob() {
        ComponentName mServiceComponent = new ComponentName(this, UpdateWidgetService.class);

        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, mServiceComponent);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            builder.setMinimumLatency(SCHEDULE_OF_PERIOD);
//        } else {
//            builder.setPeriodic(SCHEDULE_OF_PERIOD);
//        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setPeriodic(900000); //15 menit
        } else {
            builder.setPeriodic(SCHEDULE_OF_PERIOD); //3 menit
        }
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            jobScheduler.schedule(builder.build());
        }

        Toast.makeText(this, "Job Service started", Toast.LENGTH_SHORT).show();
    }

    private void cancelJob() {
        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (tm != null) {
            tm.cancel(JOB_ID);
        }
        Toast.makeText(this, "Job Service canceled", Toast.LENGTH_SHORT).show();
    }
}


