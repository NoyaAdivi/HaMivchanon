package com.example.user.exams;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MyReminder extends Activity{
    private ArrayList<Memo> memos;
    private View.OnClickListener onItemClickListener;
    private DatabaseReference database;
    private RecyclerView rvSubject1;
    SharedPreferences prefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reminder);

        database = FirebaseDatabase.getInstance().getReference();

        prefs = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);


        /**
         * Click Reminder
         */
        onItemClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder)  view.getTag();
             int position= viewHolder.getAdapterPosition();
             Memo memoItem= memos.get(position);
                Intent i = new Intent(getApplicationContext(),MyClass.class);
               i.putExtra("memoId", memoItem.getIdMemo());
                startActivity(i);
            Toast.makeText( MyReminder.this, "בחרת בתזכורת:"+ " "+"'" +memoItem.getSubject()+" " +memoItem.getDescription()+"'" , Toast.LENGTH_SHORT).show();
            }
        };


        rvSubject1= (RecyclerView) findViewById(R.id.rvSubject1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvSubject1.setLayoutManager(layoutManager);

        /**
         * View a reminder for a user
         */
        database.child("users").child(prefs.getString(MainActivity.EMAIL,"")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                memos= new ArrayList<>();
                for(DataSnapshot memoSnapshot : dataSnapshot. getChildren())
                {
                   memos.add(memoSnapshot.getValue(Memo.class));
                }
                MemoAdapter memoAdapter = new  MemoAdapter (memos);
                rvSubject1.setAdapter(memoAdapter);
                memoAdapter.setmOnItemClickListener(onItemClickListener);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
