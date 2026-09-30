package com.example.user.exams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OpenPictures extends Activity {
    ImageView ivPicture;


 //i.putExtra("image_id", imageItem.getLocation());
   // Image imageItem = images.get(position);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pictures);
        ivPicture =(ImageView) findViewById(R.id.ivPicture);
        Bundle bundle = getIntent().getExtras();
        if(bundle!= null)
        {
            /**
             * Display the image to the user according to the id of the image
             */
            Glide.with(this)
                    .load(bundle.getString("image_id"))
                    .into(ivPicture);

        }
    }
}

