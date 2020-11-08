package com.demo.neoveticare;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editTextName, editTextphone, editTextaddress, editTextprovience, editTextaboutyourself,
            editTextexperience, editTextcity, editTextemailaddress, editTextprice, editTextage;
    String table, email, documentId;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    Button btnUpdate;
    CircleImageView ivProfile;
    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;
    private StorageReference storageReference;
    String profileURL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_editprofile_activity);

        table = getIntent().getStringExtra("table");
        storageReference = FirebaseStorage.getInstance().getReference(table);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        email = firebaseAuth.getCurrentUser().getEmail();

        editTextName = (EditText) findViewById(R.id.name);
        editTextphone = (EditText) findViewById(R.id.phone);
        editTextaddress = (EditText) findViewById(R.id.address);
        editTextaboutyourself = (EditText) findViewById(R.id.writeaboutyourself);
        editTextexperience = (EditText) findViewById(R.id.experience);
        editTextprovience = (EditText) findViewById(R.id.Provience);
        editTextcity = (EditText) findViewById(R.id.City);
        editTextemailaddress = (EditText) findViewById(R.id.emailaddress);
        editTextprice = (EditText) findViewById(R.id.price);
        editTextage = (EditText) findViewById(R.id.age);
        ivProfile = findViewById(R.id.ivProfile);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DocumentReference docRef = firebaseFirestore.collection(table).document(documentId);


                Map<String, Object> updates = new HashMap<>();

                updates.put("name", editTextName.getText().toString());
                updates.put("phone", editTextphone.getText().toString());
                updates.put("address", editTextaddress.getText().toString());
                updates.put("writeaboutyourself", editTextaboutyourself.getText().toString());
                updates.put("experience", editTextexperience.getText().toString());
                updates.put("provience", editTextprovience.getText().toString());
                updates.put("city", editTextcity.getText().toString());
                updates.put("emailaddress", editTextemailaddress.getText().toString());
                updates.put("price", editTextprice.getText().toString());
                updates.put("age", editTextage.getText().toString());
                updates.put("url", profileURL);


                docRef.update((Map<String, Object>) updates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(), "Profile Updated...", Toast.LENGTH_SHORT).show();

                                finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Data Not Updated. Try Again..,", Toast.LENGTH_SHORT).show();


                            }
                        });


            }
        });

        getProfile();

    }

    private void getProfile() {

        firebaseFirestore.collection(table).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            //  Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            // Convert the whole Query Snapshot to a list
                            // of objects directly! No need to fetch each
                            // document.
                            List<Upload> uploads = documentSnapshots.toObjects(Upload.class);
                            for (int i = 0; i < uploads.size(); i++) {
                                Log.e("documentid", documentSnapshots.getDocuments().get(i).getId());
                                Upload upload = uploads.get(i);
                                if (TextUtils.equals(upload.getEmailaddress(), email)) {
                                    documentId = documentSnapshots.getDocuments().get(i).getId();
                                    Picasso.with(EditProfileActivity.this).load(upload.getUrl()).into(ivProfile);
                                    profileURL = upload.getUrl();
                                    editTextName.setText(upload.getName());
                                    editTextage.setText(upload.getAge());
                                    editTextaddress.setText(upload.getAddress());
                                    editTextcity.setText(upload.getCity());
                                    editTextemailaddress.setText(upload.getEmailaddress());
                                    editTextphone.setText(upload.getPhone());
                                    editTextprice.setText(upload.getPrice());
                                    editTextexperience.setText(upload.getExperience());
                                    editTextprovience.setText(upload.getProvience());
                                    editTextaboutyourself.setText(upload.getWritaboutyourself());
                                    // tvType.setText("Job type : " + upload.getJobtype());
                                }


                            }

                        }
                    }


                });


    }

    private void showFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivProfile.setImageBitmap(bitmap);
                uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadImage() {
        final StorageReference sRef = storageReference.child(editTextName.getText().toString().trim() + "." + getFileExtension(filePath));

        sRef.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri downloadPhotoUrl) {

                                profileURL = downloadPhotoUrl.toString();

                            }
                        });
                    }
                });
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}
