package com.example.user.exams;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import javax.xml.transform.Result;


public class ReminderWorker extends Worker {
    public static final String CHANNEL_ID = "WORKER_CHANNEL";
    public ReminderWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        /**
         *Do the work here--in this case, upload the images
         */
        Log.i("Worker", "work done");
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder( super.getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
                .setContentTitle("תזכורת")
                .setContentText("הגיע הזמן להתחיל ללמוד!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        /**
         notificationId is a unique int for each notification that you must define*
         */
        notificationManager.notify(1, builder.build());

        /**
         * Indicate whether the work finished successfully with the Result
         */
        return Result.success();
    }

    private void createNotificationChannel() {
        /**
         *Create the NotificationChannel, but only on API 26+ because
         *the NotificationChannel class is new and not in the support library
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            /**
            *Register the channel with the system; you can't change the importance
             *or other notification behaviors after this
             */
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
