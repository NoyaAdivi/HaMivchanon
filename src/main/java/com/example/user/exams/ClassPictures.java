package com.example.user.exams;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClassPictures extends Activity implements View.OnClickListener {

    Uri imageUri;
    ImageView ivPicture;
    private Button btnCamera;
    private Button btnGallery;

    // private ArrayList<Image> images;
    private View.OnClickListener onItemClickListener;
    private RecyclerView rvImage;
    SharedPreferences prefs;

    private DatabaseReference database;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int PICK_IMAGE = 2;
    private static final int PICK_IMG_FILE = 3;
    private String currentPhotoPath;
    Bitmap bitmap;

    private StorageReference storageRef;
    private UploadTask uploadTask;
    private Uri downloadUri;
    private String memoId;
    ArrayList<Image> images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_pictures);

        btnCamera =(Button) findViewById(R.id.btnCamera);
        btnGallery =(Button) findViewById(R.id.btnGallery);
        ivPicture =(ImageView) findViewById(R.id.ivPicture);

        btnCamera.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
        storageRef = FirebaseStorage.getInstance().getReference();


        storageRef= FirebaseStorage.getInstance().getReference();
        prefs = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);

        Intent i = getIntent();
       if (i.getExtras() != null) {
           memoId = i.getStringExtra("memoId");
       }

       btnGallery.setOnClickListener(new View.OnClickListener() {
        @Override
          public void onClick(View view) {
            if (view.getId() == R.id.btnGallery)
            {
                openImageFile();
            }
          }
       });


        database = FirebaseDatabase.getInstance().getReference();

        /**
         * Save the file to Firebase
         */
        onItemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int position = viewHolder.getAdapterPosition();
                Image imageItem = images.get(position);
                Intent i = new Intent(ClassPictures.this,OpenPictures.class);
                i.putExtra("image_id", imageItem.getLocation());
                startActivity(i);
                Toast.makeText(ClassPictures.this, "בחרת: 'לפתוח את התמונה'", Toast.LENGTH_SHORT).show();
               // Toast.makeText(ClassPictures.this, "you clicked" + imageItem.getLocation(), Toast.LENGTH_SHORT).show();
            }
        };


        /**
         * View the pictures
         */
        rvImage = (RecyclerView) findViewById(R.id.rvImage);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        rvImage.setLayoutManager(layoutManager);


        /**
         * Connect to the data structure
         */
        database = FirebaseDatabase.getInstance().getReference();

        database.child("users").child(prefs.getString(MainActivity.EMAIL,"")).child(memoId).child("images").addValueEventListener(new ValueEventListener() {
            /**
             * The operation occurs whenever there is an update on the server
             * @param dataSnapshot a parameter for dataSnapshot
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                images = new ArrayList<>();
                for (DataSnapshot imageSnapshot : dataSnapshot.getChildren()) {

                    images.add(imageSnapshot.getValue(Image.class));

                }

                ImageAdapter imageAdapter = new ImageAdapter(images);
                rvImage.setAdapter(imageAdapter);
                imageAdapter.setmOnItemClickListener(onItemClickListener);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                /**
                 * Getting Post failed, log a message
                 */
            }
        });

    }

    /**
     * Save a full picture
     * @param requestCode request for code
     * @param resultCode resultfor code
     * @param data get information
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setPic();
            uploadFullImage();
            //uploadCompressedFile();
        }

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                uploadFile(uri, ".jpg" );
            }
        }
    }

    /**
     * Save the image as a jpg file in a database
     * @param uri a parameter
     * @param suffix a parameter
     */
    private void uploadFile(Uri uri, String suffix)
    {
        StorageReference riversRef = null;
        if (suffix.equals(".jpg"))
            riversRef = storageRef.child("images/"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + suffix);
        UploadTask uploadTask = riversRef.putFile(uri);
        final StorageReference finalRiversRef = riversRef;
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                /**
                 * Continue with the task to get the download URL
                 */
                return finalRiversRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    downloadUri = task.getResult();
                    Image image = new Image( downloadUri.toString(),downloadUri.toString());
                    database.child("users").child(prefs.getString(MainActivity.EMAIL,"")).child(memoId).child("images").child(image.getIdImage()).setValue(image);

                } else {
                    /**
                     * Handle failures
                     */
                    // ...
                }
            }
        });
    }

    /**
     * Press the camera button or gallery
     * @param view a parameter for view
     */
    @Override
    public void onClick(View view) {
        String id = prefs.getString(MainActivity.EMAIL, "").toString();
        if (view.getId()== R.id.btnCamera){
            dispatchTakePictureIntent();
        }
        else if(view.getId() == R.id.btnGallery)
        {
                openImageFile();
        }
    }

    /**
     * If there is a user took a photo it is saved in its folder
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFike();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.user.exams.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    /**
     * Open the image file
     */
    private void openImageFile() {
       Intent intent = new Intent();
       intent.setType("images/*");
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(gallery, PICK_IMAGE);
       //intent.setAction(Intent.ACTION_GET_CONTENT);
      // startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMG_FILE);
    }

    /**
     * If the user selected a picture, it is saved in a folder
     */
    private void dispatchGalleryPictureIntent() {
        Intent galleryPictureIntent = new Intent(Intent.ACTION_PICK);
        if (galleryPictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFike();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.user.exams.fileprovider", photoFile);
                galleryPictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(galleryPictureIntent, PICK_IMAGE);
            }
        }
    }

    /**
     * Save the image in Firebase as a jpg file
     * @return image in Firebase as a jpg file
     * @throws IOException a parameter
     */
    private File createImageFike() throws IOException {
        /**
         * Create an image file name
         */
        String timeStamp = new SimpleDateFormat("yyyymmdd_HHmms").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName /*directory*/,
                ".jpg" /*directory*/,
                storageDir /*directory*/
        );

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /**
     * Maintain the length and width of the picture
     */
    private void setPic(){
        int targetW = 100;
        int targeth = 100;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath,bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        int scaleFactor = Math.max(100,Math.min(photoW/photoW, photoH/photoH));

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);


    }

    /**
     * Download the image
     */
    private void uploadCompressedFile() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        Uri file = Uri.fromFile(new File(currentPhotoPath));
        final StorageReference riversRef= storageRef.child("comressed_imagess/"+file.getLastPathSegment());
        UploadTask uploadTask = riversRef.putBytes(data);
        Task <Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if(!task.isSuccessful()){
                    throw task.getException();
                }
                return riversRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
             if (task.isSuccessful()){
                 downloadUri = task.getResult();
             }
             else {

             }
            }
        });
    }

    /**
     * Download a full picture
     */
    private void uploadFullImage(){
        Uri file = Uri.fromFile(new File(currentPhotoPath));
        final StorageReference riversRef= storageRef.child("images/"+file.getLastPathSegment());
        uploadTask = riversRef.putFile(file);
        Task <Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()){
                    throw task.getException();
                }

                return riversRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    downloadUri = task.getResult();
                    Image image = new Image( downloadUri.toString(),downloadUri.toString());
                    database.child("users").child(prefs.getString(MainActivity.EMAIL,"")).child(memoId).child("images").child(image.getIdImage()).setValue(image);
                } else {

                }
            }
        });
    }
}
