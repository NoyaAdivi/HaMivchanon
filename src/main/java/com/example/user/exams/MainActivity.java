package com.example.user.exams;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private ImageButton ibEnterMail;
    private EditText etEmail;
    private Button btnMyReminder;
    private Button btnNewReminder;
    public static final String PREFS_NAME = "MY_PREFERENCES";
    SharedPreferences prefs;
    public static final String EMAIL = "EMAIL";
    public static final String ID = "ID";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibEnterMail = (ImageButton) findViewById(R.id.ibEnterMail);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnMyReminder = (Button) findViewById(R.id.btnMyReminder);
        btnNewReminder = (Button) findViewById(R.id.btnNewReminder);

        btnMyReminder.setOnClickListener(this);
        btnNewReminder.setOnClickListener(this);
        ibEnterMail.setOnClickListener(this);

        prefs = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
        etEmail.setText(prefs.getString(MainActivity.EMAIL,""));
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnMyReminder) {
            Intent i = new Intent(this, MyReminder.class);
            startActivity(i);
        } else if (view.getId() == R.id.btnNewReminder) {
            Intent i = new Intent(this, NewReminder.class);
            startActivity(i);
        } else if (view.getId() == R.id.etEmail) {


        } else if (view.getId() == R.id.ibEnterMail) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("האם לשלוח כתובת זו?");
            alertDialog.setPositiveButton("כן", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    /**
                     * User select Yes
                     * Save the email address to prefs
                     */
                    SharedPreferences.Editor editor = prefs.edit();
                    String email = etEmail.getText().toString();
                    if (email.indexOf('@')>0) {
                        email = email.substring(0, email.indexOf('@')).replace(".", "");
                        editor.putString(MainActivity.EMAIL, email);
                        editor.commit();
                    }
                }
            });

            alertDialog.setNegativeButton("לא", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    /**
                     * User select No
                     */
                }
            });

            alertDialog.show();
        }

    }



}