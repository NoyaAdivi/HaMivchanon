package com.example.user.exams;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClassDocs extends Activity implements View.OnClickListener {
    Intent myFileIntent;
   TextView tvDoc;
    private Button btnDocs;
    private ArrayList<Doc> docs;
    private View.OnClickListener onItemClickListener;
    private RecyclerView rvDocs;
    private static final int PICK_PDF_FILE= 2;
    private StorageReference storageRef;
    private Uri downloadUri;
    SharedPreferences prefs;
    private String memoId;
    private String idDoc;



    private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_docs);

        btnDocs = (Button) findViewById(R.id.btnDocs);
        tvDoc =  (TextView) findViewById(R.id.tvDoc);

        btnDocs.setOnClickListener(this);
        storageRef = FirebaseStorage.getInstance().getReference();

        /**
         * Connect to the data structure
         */
        database= FirebaseDatabase.getInstance().getReference();
        prefs = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);

        Intent i = getIntent();
        if (i.getExtras() != null) {
            memoId = i.getStringExtra("memoId");
        }
        Intent N = getIntent();
        if (N.getExtras() != null) {
            idDoc = i.getStringExtra("idDoc");
        }

        docs = new ArrayList<>();

        /**
         * Save the file to firebase
         */
        onItemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view){
               RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
               int position = viewHolder.getAdapterPosition();
               Doc docItem = docs.get(position);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setDataAndType(Uri.parse(docItem.getLocation()), "application/pdf");
                startActivity(browserIntent);
               //database.child("user").child(docItem.getIdDoc()).removeValue();
                Toast.makeText(ClassDocs.this, "בחרת: 'לפתוח את המסמך'", Toast.LENGTH_SHORT).show();
              // Toast.makeText(ClassDocs.this, "you clicked" + docItem.getLocation(), Toast.LENGTH_SHORT).show();
            }
        };

        rvDocs = (RecyclerView) findViewById(R.id.rvDocs);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDocs.setLayoutManager(layoutManager);

        /**
         * Create a file in database
         */
        database.child("users").child(prefs.getString(MainActivity.EMAIL,"")).child(memoId).child("docs").addValueEventListener(new ValueEventListener() {
            /**
             * The operation occurs whenever there is an update on the server
             * @param dataSnapshot a parameter
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                docs= new ArrayList<>();
                for(DataSnapshot docSnapshot : dataSnapshot. getChildren())
                {
                    docs.add(docSnapshot.getValue(Doc.class));
                }
                DocAdapter DocsAdapter = new DocAdapter(docs);
                rvDocs.setAdapter(DocsAdapter);
                DocsAdapter.setmOnItemClickListener(onItemClickListener);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    /**
     * set up pdf file
     * @param requestCode request for code
     * @param resultCode resultfor code
     * @param data get information
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_PDF_FILE && resultCode == RESULT_OK) {
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                uploadFile(uri, ".pdf" );
            }
        }
        }

    /**
     * Save a file in database
     * @param uri a parameter
     * @param suffix a parameter
     */
    private void uploadFile(Uri uri, String suffix) {
        StorageReference riversRef = null;

       if (suffix.equals(".pdf"))
            riversRef = storageRef.child("docs/" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + suffix);
        UploadTask uploadTask = riversRef.putFile(uri);
        final StorageReference finalRiversRef = riversRef;
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                /**
                 *  Continue with the task to get the download URL
                 */
                return finalRiversRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    downloadUri = task.getResult();
                    Doc doc = new Doc( downloadUri.toString(),downloadUri.toString());
                    database.child("users").child(prefs.getString(MainActivity.EMAIL,"")).child(memoId).child("docs").child(doc.getIdDoc()).setValue(doc);
                } else {
                    /**
                     * Handle failures
                     */
                }
            }
        });
    }


    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnDocs)
         {
            openFile();
        }
        //String id = prefs.getString(MainActivity.EMAIL,"").toString();


        /*Doc doc = new Doc(btnDocs.getText().toString() );
        database.child("users").child(prefs.getString(MainActivity.EMAIL, "")).child(doc.getIdDoc()).setValue(doc);*/
    }

    /**
     * Open the file
     */
    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        startActivityForResult(intent, PICK_PDF_FILE);
    }

}
