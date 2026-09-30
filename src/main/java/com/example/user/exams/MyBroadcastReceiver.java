package com.example.user.exams;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class MyBroadcastReceiver extends BroadcastReceiver {
    SharedPreferences prefs;
    @Override
    public void onReceive(final Context context, Intent intent) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //database.child("test").setValue("ביצעת אתחול לטלפון");
        //database.child(Toast.makeText, intent.getAction(),"hello", Toast.LENGTH_SHORT).show();
        /**
         * If the user boots to the phone
         */
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_LONG).show();
        Log.d("MyBroadcastReceiver", intent.getAction());
        prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);


        /**
         *Activate alert times by email
         */
        database.child("users").child(prefs.getString(MainActivity.EMAIL,"")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot memoSnapshot : dataSnapshot. getChildren())
                {
                    Memo memo = memoSnapshot.getValue(Memo.class);
                    NewReminder.setWork(NewReminder.getHours(memo.getTime1()), NewReminder.getMinutes(memo.getTime1()), memo.getDateStart(),context);
                    NewReminder.stopWork(NewReminder.getHours(memo.getTime1()), NewReminder.getMinutes(memo.getTime1()), memo.getDateFinish(),context);
                    if (memo.getTime2().length()<=7)
                    {
                        NewReminder.setWork(NewReminder.getHours(memo.getTime2()), NewReminder.getMinutes(memo.getTime2()), memo.getDateStart(),context);
                        NewReminder.stopWork(NewReminder.getHours(memo.getTime2()), NewReminder.getMinutes(memo.getTime2()), memo.getDateFinish(),context);
                    }
                    if (memo.getTime3().length()<=7)
                    {
                        NewReminder.setWork(NewReminder.getHours(memo.getTime3()), NewReminder.getMinutes(memo.getTime3()), memo.getDateStart(),context);
                        NewReminder.stopWork(NewReminder.getHours(memo.getTime3()), NewReminder.getMinutes(memo.getTime3()), memo.getDateFinish(),context);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }



}
