package com.example.user.exams;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyClass extends Activity implements View.OnClickListener{
    private TextView tvSubject;
    private TextView tvView;
    private TextView tvDate;
    private TextView tvTime;
    private Button btnDocs;
    private Button btnPictures;
    private ImageButton ibDelete;
    private DatabaseReference database;
    SharedPreferences prefs;
    Memo currentMemmo;
    private String memoId;
    private ArrayList<Memo> memos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class);

        database = FirebaseDatabase.getInstance().getReference();
        prefs = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);

        tvSubject = (TextView) findViewById(R.id.tvSubject);
        tvView = (TextView) findViewById(R.id.tvView);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvTime = (TextView) findViewById(R.id.tvTime);
        btnDocs = (Button) findViewById(R.id.btnDocs);
        btnPictures = (Button) findViewById(R.id.btnPictures);
        ibDelete = (ImageButton) findViewById(R.id.ibDelete);
        ImageView imageView = findViewById(R.id.hourglassAnimation);

        /**
         * Set up and display animation
         */
        imageView.setBackgroundResource(R.drawable.hourglass_animation_list);
        AnimationDrawable hourglass = (AnimationDrawable) imageView.getBackground();
        hourglass.start();

        database= FirebaseDatabase.getInstance().getReference();
        prefs = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);

        Intent n = getIntent();
        if (n.getExtras() != null) {
            memoId = n.getStringExtra("memoId");
        }

        btnDocs.setOnClickListener(this);
        btnPictures.setOnClickListener(this);

        /**
         * Retrieve information from the Firebase to the text boxes
         */
        Intent i = getIntent();
        if (i.getExtras() != null) {
            database.child("users").child(prefs.getString(MainActivity.EMAIL, "")).child(i.getStringExtra("memoId")).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    currentMemmo = dataSnapshot.getValue(Memo.class);
                    tvSubject.setText(" " + currentMemmo.getSubject());
                    tvView.setText(" " + currentMemmo.getDescription());
                    tvDate.setText(" " + currentMemmo.getDateStart()+ "-" + currentMemmo.getDateFinish());
                    tvTime.setText(" " + currentMemmo.getTime1()+"," + " " + currentMemmo.getTime2()+"," + " " + currentMemmo.getTime3());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        /**
         *Dialog for deleting a user reminder
         */
        ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyClass.this);
                alertDialog.setMessage("האם למחוק תזכורת זו?");
                alertDialog.setPositiveButton("כן", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /**
                         *User select Yes
                         */
                        deleteMemo("memoId");
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

            /**
             * Delete a user reminder
             * @param idMemo gets the id of the reminder
             */
            private void deleteMemo(String idMemo) {
                DatabaseReference drMemo = FirebaseDatabase.getInstance().getReference("users").child(prefs.getString(MainActivity.EMAIL,"")).child(memoId);
                drMemo.removeValue();
                Toast.makeText(MyClass.this,
                        "מחקת את התזכורת",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnDocs)
        {
            Intent i =new Intent(this,ClassDocs.class );
            i.putExtra("memoId", currentMemmo.getIdMemo());
            startActivity(i);
        }

        else if(view.getId()==R.id.btnPictures)
        {
            Intent i =new Intent(this,ClassPictures.class );
            i.putExtra("memoId", currentMemmo.getIdMemo());
            startActivity(i);
        }
    }

    /**
     * Set the menu on this page
     * @param menu get this
     * @return menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     *Switch between pages and display a message
     * @param item get this
     * @return items
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();

        if (id ==R.id.menu_main){
            Intent i =new Intent(MyClass.this,MainActivity.class );
            startActivity(i);
        }
        if (id ==R.id.menu_my_reminder){
            Intent i =new Intent(MyClass.this,MyReminder.class );
            startActivity(i);
        }
        if (id ==R.id.menu_new_reminder){
            Intent i =new Intent(MyClass.this,NewReminder.class );
            startActivity(i);
        }
        switch (item.getItemId()){
            case R.id.menu_main:
                Toast.makeText(this,"לחצת על 'חזרה לדף הראשי'",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_my_reminder:
                Toast.makeText(this,"לחצת על 'חזרה לתזכורת שלי'",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_new_reminder:
                Toast.makeText(this,"לחצת על 'יצירת תזכורת חדשה'",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}



