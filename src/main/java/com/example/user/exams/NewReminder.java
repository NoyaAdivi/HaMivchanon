package com.example.user.exams;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.WorkerParameters;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.DatabaseMetaData;
import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NewReminder extends Activity implements View.OnClickListener {
    private EditText etSubject;
    private Spinner spView;
    private TextView tvFromDate;
    private TextView tvToDate;
    private TextView tvTime1;
    private TextView tvTime2;
    private TextView tvTime3;
    private ImageButton ibPlus;
    private Button btnFinish;
    private String reminderType;
    private Calendar calendarForWork;
    private int hour1 = -1;
    private int hour2 = -1;
    private int hour3 = -1;

    private int minutes1 = -1;
    private int minutes2 = -1;
    private int minutes3 = -1;
    private String time1;
    private String time2;
    private String time3;


    SharedPreferences prefs;
    private DatabaseReference database;

    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);

        etSubject = (EditText) findViewById(R.id.etSubject);
        spView = (Spinner) findViewById(R.id.spView);
        tvFromDate = (TextView) findViewById(R.id.tvFromDate);
        tvToDate = (TextView) findViewById(R.id.tvToDate);
        tvTime1 = (TextView) findViewById(R.id.tvTime1);
        tvTime2 = (TextView) findViewById(R.id.tvTime2);
        tvTime3 = (TextView) findViewById(R.id.tvTime3);
        ibPlus = (ImageButton) findViewById(R.id.ibPlus);
        btnFinish = (Button) findViewById(R.id.btnFinish);

        database = FirebaseDatabase.getInstance().getReference();
        calendarForWork = Calendar.getInstance();

        ibPlus.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        /**
         * assign variable
         */
        tvFromDate= findViewById(R.id.tvFromDate);
        tvToDate= findViewById(R.id.tvToDate);
        tvTime1= findViewById(R.id.tvTime1);
        tvTime2= findViewById(R.id.tvTime2);
        tvTime3= findViewById(R.id.tvTime3);

        prefs = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);


        /**
         * Initialize calendar
         */
        final Calendar calendar = Calendar.getInstance();

        /**
         * get year
         */
        final int year = calendar.get(Calendar.YEAR);
        /**
         * get month
         */
        final int month = calendar.get(Calendar.MONTH);
        /**
         * get day
         */
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        /**
         * get hour
         */
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        /**
         * get minute
         */
        final int minute = calendar.get(Calendar.MINUTE);


        tvFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *  Initialize date picker dialog
                 */
                DatePickerDialog datePickerDialog = new DatePickerDialog(NewReminder.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        /**
                         * store date in
                         */
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, month, dayOfMonth);
                        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                        String sDate= format1.format(newDate.getTime());
                        /**
                         * set date on text view
                         */
                        tvFromDate.setText(sDate);
                    }
                },year,month,day
                );
                /**
                 * Disable past date
                 */
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()- 1000);
                /**
                 * show date picker dialog
                 */
                datePickerDialog.show();
            }
        });


        tvToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Initialize date picker dialog
                 */
                DatePickerDialog datePickerDialog = new DatePickerDialog(NewReminder.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        /**
                         * store date in string
                         */
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, month, dayOfMonth);
                        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                        String sDate= format1.format(newDate.getTime());
                        tvToDate.setText(sDate);
                    }
                },year,month,day);
                /**
                 * Disable past date
                 */
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                /**
                 * show date picker dialog
                 */
                datePickerDialog.show();
            }
        });


        /**
         * Choose time for the first alert through dialog
         */
        tvTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvTime1.setText(hourOfDay + ":" + minute);
                        hour1 = hourOfDay;
                        minutes1 = minute;

                        if(minute < 10 && hourOfDay > 9){
                            time1 = hourOfDay + ":0" + minute;
                        } else if(minute > 9 & hourOfDay < 10){
                            time1 = "0" + hourOfDay + ":" + minute;
                        } else if(minute < 10 && hourOfDay < 10){
                            time1 = "0" + hourOfDay + ":0" + minute;
                        } else if(minute > 9 && hourOfDay > 9){
                            time1 = hourOfDay + ":" + minute;
                        }
                        tvTime1.setText(time1);

                    }
                }, hour, minute, android.text.format.DateFormat.is24HourFormat(mContext));
                timePickerDialog.show();

            }

        });

        /**
         * Choose time for the second alert through dialog
         */
        tvTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvTime2.setText(hourOfDay + ":" + minute);
                        hour2 = hourOfDay;
                        minutes2 = minute;
                        String time = " ";

                        if(minute < 10 && hourOfDay > 9){
                            time2 = hourOfDay + ":0" + minute;
                        } else if(minute > 9 & hourOfDay < 10){
                            time2 = "0" + hourOfDay + ":" + minute;
                        } else if(minute < 10 && hourOfDay < 10){
                            time2 = "0" + hourOfDay + ":0" + minute;
                        } else if(minute > 9 && hourOfDay > 9){
                            time2 = hourOfDay + ":" + minute;
                        }
                        tvTime2.setText(time2);

                    }
                },hour, minute,android.text.format.DateFormat.is24HourFormat(mContext));
                timePickerDialog.show();

            }
        });

        /**
         * Choose time for the third alert through dialog
         */
        tvTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvTime3.setText(hourOfDay + ":" + minute);
                        hour3 = hourOfDay;
                        minutes3 = minute;

                        if(minute < 10 && hourOfDay > 9){
                            time3 = hourOfDay + ":0" + minute;
                        } else if(minute > 9 & hourOfDay < 10){
                            time3 = "0" + hourOfDay + ":" + minute;
                        } else if(minute < 10 && hourOfDay < 10){
                            time3 = "0" + hourOfDay + ":0" + minute;
                        } else if(minute > 9 && hourOfDay > 9){
                            time3 = hourOfDay + ":" + minute;
                        }
                        tvTime3.setText(time3);

                    }

                },hour, minute,android.text.format.DateFormat.is24HourFormat(mContext));
                timePickerDialog.show();
            }
        });

        /**
         * Open a spinner to select a description
         */
       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.views, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spView.setAdapter(adapter);
        spView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("בחר תיאור"))
                {
                    /**
                     * do nothing
                     */
                }
                /**
                 * else
                 */
                    {
                        /**
                         * on selecting a spinner item
                         */
                   reminderType = parent.getItemAtPosition(position).toString();
                   Toast.makeText(parent.getContext(), "בחרת בתיאור: " + reminderType, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /*

     */
    int hours_counter = 0;

    /**
     * Add new alert time by pressing a button
     * @param view a parameter for view
     */
    @Override
    public void onClick(View view) {
        String id= etSubject.getText().toString();
        if (view.getId() == R.id.ibPlus) {
            hours_counter++;
            //for (int  counter=0;  counter<3;  counter++){
                if ((view.getId() == R.id.ibPlus) && ( hours_counter == 1)) {
                    tvTime2.setVisibility(View.VISIBLE);
                }
                if((view.getId() == R.id.ibPlus) && ( hours_counter == 2)) {
                    tvTime3.setVisibility(View.VISIBLE);
                }
            //}
            /**
             * Go to menu
             */
        }

        /**
         * View a dialog as soon as the user clicks the "I'm done" button
         */
        if (view.getId() == R.id.btnFinish) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("האם אתה בטוח שסיימת?");

            final Intent i = new Intent(this, MyClass.class);

            /**
             * If the user clicks "Yes" the alert is saved to the firebase
             */
            alertDialog.setPositiveButton("כן", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Memo memo = new Memo(etSubject.getText().toString(), reminderType, tvFromDate.getText().toString(), tvToDate.getText().toString(),tvTime1.getText().toString(), tvTime2.getText().toString(), tvTime3.getText().toString() );
                    database.child("users").child(prefs.getString(MainActivity.EMAIL,"")).child(memo.getIdMemo()).setValue(memo);

                    NewReminder.setWork(NewReminder.getHours(memo.getTime1()), NewReminder.getMinutes(memo.getTime1()), memo.getDateStart(),getApplicationContext());
                    NewReminder.stopWork(NewReminder.getHours(memo.getTime1()), NewReminder.getMinutes(memo.getTime1()), memo.getDateFinish(),getApplicationContext());
                    if (memo.getTime2().length()<=7)
                    {
                        NewReminder.setWork(NewReminder.getHours(memo.getTime2()), NewReminder.getMinutes(memo.getTime2()), memo.getDateStart(),getApplicationContext());
                        NewReminder.stopWork(NewReminder.getHours(memo.getTime2()), NewReminder.getMinutes(memo.getTime2()), memo.getDateFinish(),getApplicationContext());
                    }
                    if (memo.getTime3().length()<=7)
                    {
                        NewReminder.setWork(NewReminder.getHours(memo.getTime3()), NewReminder.getMinutes(memo.getTime3()), memo.getDateStart(),getApplicationContext());
                        NewReminder.stopWork(NewReminder.getHours(memo.getTime3()), NewReminder.getMinutes(memo.getTime3()), memo.getDateFinish(),getApplicationContext());
                    }

                    i.putExtra("memoId", memo.getIdMemo());

                    startActivity(i);
                }
            });

            alertDialog.setNegativeButton("לא", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    /**
                     * If the user clicked "No" the reminder is not saved
                     */
                }
            });

            alertDialog.show();
        }

    }

    /**
     * Set an alert by what the user entered
     * @param hour parameter for hour
     * @param minute parameter for minute
     * @param calendarForWorkstr Calendar
     * @param c Context
     */
    public static void setWork(int hour, int minute, String calendarForWorkstr, Context c){
        Calendar calendarForWork = Calendar.getInstance();

        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            calendarForWork.setTime(format1.parse(calendarForWorkstr));// all done
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendarForWork.set(Calendar.HOUR_OF_DAY, hour);
        calendarForWork.set(Calendar.MINUTE, minute);
        calendarForWork.set(Calendar.SECOND, 0);
        calendarForWork.set(Calendar.MILLISECOND, 0);

        //Constraints mConstraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).setRequiresCharging(true).build();

        PeriodicWorkRequest myWorkRequest =
                new PeriodicWorkRequest.Builder(ReminderWorker.class, 1, TimeUnit.DAYS)
                        .setInitialDelay(calendarForWork.getTimeInMillis() - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .addTag("work" + hour + ":" + minute)
                        //.setConstraints(mConstraints)
                        // Constraints
                        .build();


        WorkManager.getInstance(c).enqueueUniquePeriodicWork(
                "sendLogs",
                ExistingPeriodicWorkPolicy.REPLACE,
                myWorkRequest);
    }

    /**
     * Stop the alert by the last day the user entered
     * @param hour parameter for hour
     * @param minute parameter for minute
     * @param calendarForWorkstr Calendar
     * @param c Context
     */
    public static void stopWork(int hour, int minute, String calendarForWorkstr, Context c) {
        Calendar calendarForWork = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            calendarForWork.setTime(format1.parse(calendarForWorkstr));
            /**
             * all done
             */
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendarForWork.set(Calendar.HOUR_OF_DAY, hour);
        calendarForWork.set(Calendar.MINUTE, minute+1);
        calendarForWork.set(Calendar.SECOND, 0);
        calendarForWork.set(Calendar.MILLISECOND, 0);

        Data myData = new Data.Builder()
        /**
         * We need to pass three integers: X, Y, and Z
         */
                .putString("work tag", "work" + hour + ":" + minute)
                .build();
        /**
         * Stop an alert
         */
        WorkRequest uploadWorkRequest =
                new OneTimeWorkRequest.Builder(StopWorker.class)
                        .setInitialDelay(calendarForWork.getTimeInMillis() - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .setInputData(myData)
                        .addTag("stop work" + hour + ":" + minute)
                        .build();

        WorkManager.getInstance(c).enqueue(uploadWorkRequest);
    }
    public static int getHours(String time)
    {
        return Integer.parseInt(time.substring(0,time.indexOf(":")));
    }
    public static int getMinutes(String time)
    {
        return Integer.parseInt(time.substring(time.indexOf(":") + 1));
    }
}
