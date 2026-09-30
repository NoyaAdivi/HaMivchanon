package com.example.user.exams;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Objects;

public class StopWorker extends Worker {
    public StopWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    /**
     * Stop the alert until the date the user has selected
     */
    public Result doWork() {
        Log.i("Worker", "work stops");
       WorkManager.getInstance(super.getApplicationContext()).cancelAllWorkByTag(Objects.requireNonNull(getInputData().getString("work tag")));
        return Result.success();
    }
}
