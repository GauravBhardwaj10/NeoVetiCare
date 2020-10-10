package com.demo.neoveticare;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ImageFirebaseUploadDemo extends AppCompatActivity {

    //constant to track image chooser intent
    private static final int PICK_IMAGE_REQUEST = 234;

    //view objects
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;

    private Button buttonChoose;
    private Button buttonUpload;
    private EditText editTextName, editTextphone, editTextaddress, editTextprovience, editTextaboutyourself, editTextexperience, editTextcity, editTextemailaddress, editTextprice, editTextage;
    private TextView textViewShow;
    private ImageView imageView, getImageView;
    Spinner spinnerjobtype, spinnergender, spinnerttimings;

    //uri to store file
    private Uri filePath;

    String downloadUri;

    //firebase objects
    private StorageReference storageReference, parttimestoragereference;
    //    private FirebaseStorage firebaseStorage;
    private FirebaseFirestore mDatabase, parttimeDatabase;

    private RecyclerView RecyclerView;
    int count = 0;
    ArrayList<String> arrlst = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main5);


        drawerLayout = findViewById(R.id.drawer_layout);
        mtoggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.nav_view);
        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        imageView = (ImageView) findViewById(R.id.imageView);
        editTextName = (EditText) findViewById(R.id.name);
        editTextphone = (EditText) findViewById(R.id.phone);
        editTextaddress = (EditText) findViewById(R.id.address);
        textViewShow = (TextView) findViewById(R.id.textViewShow);
        spinnerjobtype = findViewById(R.id.JobType);
        spinnergender = findViewById(R.id.gender);
        editTextaboutyourself = (EditText) findViewById(R.id.writeaboutyourself);
        editTextexperience = (EditText) findViewById(R.id.experience);
        editTextprovience = (EditText) findViewById(R.id.Provience);
        editTextcity = (EditText) findViewById(R.id.City);
        editTextemailaddress = (EditText) findViewById(R.id.emailaddress);
        editTextprice = (EditText) findViewById(R.id.price);
        editTextage = (EditText) findViewById(R.id.age);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    Toast.makeText(ImageFirebaseUploadDemo.this, "home Page:", Toast.LENGTH_SHORT).show();
                    m1();
                }
                if (item.getItemId() == R.id.Admin) {
                    Toast.makeText(ImageFirebaseUploadDemo.this, "Admin Login page:", Toast.LENGTH_SHORT).show();
                    m2();
                }
                if (item.getItemId() == R.id.security) {
                    Toast.makeText(ImageFirebaseUploadDemo.this, "Privacy & security Page:", Toast.LENGTH_SHORT).show();
                    m3();
                }
                if(item.getItemId()==R.id.rating)
                {
                    Toast.makeText(ImageFirebaseUploadDemo.this,"Rate this app:",Toast.LENGTH_SHORT).show();
                    k5();
                }
                if(item.getItemId()==R.id.share)
                {
                    Toast.makeText(ImageFirebaseUploadDemo.this,"Share the link of app by:",Toast.LENGTH_SHORT).show();
                    k6();
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        mDatabase = FirebaseFirestore.getInstance();
//        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("uploadChildern");

        parttimeDatabase = FirebaseFirestore.getInstance();
        parttimestoragereference = FirebaseStorage.getInstance().getReference("uploadChildrenparttime");

//        final CollectionReference dbupload = mDatabase.collection("upload");

        textViewShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                  to see all image in recycler view
//                startActivity(new Intent(ImageFirebaseUploadDemo.this,ImageActivity.class));
                Picasso.with(ImageFirebaseUploadDemo.this)
                        .load(downloadUri)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(getImageView);

            }
        });


        String firstname = getIntent().getStringExtra("firstname");
        String email = getIntent().getStringExtra("email");

        editTextName.setText(firstname);
        editTextemailaddress.setText(email);


        spinnerjobtype = findViewById(R.id.JobType);

        List<String> job = new ArrayList<>();
        job.add(0, "Job Type");
        job.add("Full Time");
        job.add("Part Time");


        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, job);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerjobtype.setAdapter(dataAdapter);

        spinnerjobtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Job Type")) {

                } else {

                    String jobtype = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + jobtype, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnergender = findViewById(R.id.gender);

        List<String> gender = new ArrayList<>();
        gender.add(0, "Gender");
        gender.add("Male");
        gender.add("Female");
        gender.add("Other");


        ArrayAdapter<String> dataAdaptergender;
        dataAdaptergender = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);

        dataAdaptergender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnergender.setAdapter(dataAdaptergender);

        spinnergender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Gender")) {

                } else {

                    String gender = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + gender, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerttimings = findViewById(R.id.Timings);

        final List<String> Timings = new ArrayList<>();
        Timings.add(0, "Timings");
        Timings.add("Morning");
        Timings.add("Evening");
        Timings.add("Night");


        ArrayAdapter<String> dataAdaptertimings;
        dataAdaptertimings = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Timings);

        dataAdaptertimings.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerttimings.setAdapter(dataAdaptertimings);

        spinnerttimings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Timings")) {

                } else {

                    String timings = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + timings, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        RecyclerView = (RecyclerView) findViewById(R.id.recyclerlistview);

        //RecyclerView layout manager
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        RecyclerView.setLayoutManager(recyclerLayoutManager);

        //RecyclerView item decorator
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(RecyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        RecyclerView.addItemDecoration(dividerItemDecoration);

        //RecyclerView adapater
        ProductFilterRecyclerViewAdapter recyclerViewAdapter = new
                ProductFilterRecyclerViewAdapter(getSchedules(), this);
        RecyclerView.setAdapter(recyclerViewAdapter);


        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });


        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((!TextUtils.isEmpty(editTextName.getText().toString())) && (!TextUtils.isEmpty(editTextphone.getText().toString()))
                        && (!TextUtils.isEmpty(editTextaddress.getText().toString())) &&
                        (!TextUtils.isEmpty(editTextaboutyourself.getText().toString())) &&
                        (!TextUtils.isEmpty(editTextexperience.getText().toString())) &&
                        (!TextUtils.isEmpty(editTextprovience.getText().toString()))
                        && (!TextUtils.isEmpty(editTextcity.getText().toString()))
                        && (!TextUtils.isEmpty(editTextemailaddress.getText().toString()))
                        && (!TextUtils.isEmpty(editTextprice.getText().toString()))
                        && (!TextUtils.isEmpty(editTextage.getText().toString()))
                        && ((spinnerjobtype.getSelectedItem().toString().equals("Full Time")) || (spinnerjobtype.getSelectedItem().toString().equals("Part Time")))
                        && ((spinnerttimings.getSelectedItem().toString().equals("Morning")) || (spinnerttimings.getSelectedItem().toString().equals("Evening")) || (spinnerttimings.getSelectedItem().toString().equals("Night")))
                        && ((spinnergender.getSelectedItem().toString().equals("Male")) || (spinnergender.getSelectedItem().toString().equals("Female")) || (spinnergender.getSelectedItem().toString().equals("other")))
                ) {

                    if (spinnerjobtype.getSelectedItem().toString().equals("Full Time")) {

                        if (filePath != null) {
                            //displaying progress dialog while image is uploading
                            final ProgressDialog progressDialog = new ProgressDialog(ImageFirebaseUploadDemo.this);
                            progressDialog.setTitle("Uploading");
                            progressDialog.show();

                            final StorageReference sRef = storageReference.child(editTextName.getText().toString().trim() + "." + getFileExtension(filePath));

//                    sRef.putFile(filePath)
//                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                    progressDialog.dismiss();
//
//                                    //displaying success toast
//                                    Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
//
//                                    //creating the upload object to store uploaded image details
////                                    Upload upload = new Upload(editTextName.getText().toString().trim(), taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
//////
////                                    CollectionReference dbupload = mDatabase.collection("images");
////                                    dbupload.add(upload).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
////                                        @Override
////                                        public void onSuccess(DocumentReference documentReference) {
////                                            Toast.makeText(ImageFirebaseUploadDemo.this, "Success", Toast.LENGTH_SHORT).show();
////                                        }
////                                    });
//
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(ImageFirebaseUploadDemo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//
//                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                            progressDialog.setProgress((int)progress);
//
//                        }
//                    });

                            sRef.putFile(filePath).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    if (!task.isSuccessful()) {
                                        throw task.getException();
                                    }
                                    return sRef.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                progressDialog.setProgress(0);
                                            }
                                        }, 500);
                                    }
                                    downloadUri = task.getResult().toString();
                                    Upload upload = new Upload(editTextName.getText().toString().trim(),
                                            editTextphone.getText().toString().trim(),
                                            editTextaddress.getText().toString().trim(),
                                            editTextaboutyourself.getText().toString(),
                                            editTextexperience.getText().toString()
                                                    .trim(),
                                            spinnerttimings.getSelectedItem().toString(),
                                            spinnerjobtype.getSelectedItem().toString(),
                                            downloadUri,
                                            spinnergender.getSelectedItem().toString(),
                                            arrlst, editTextprovience.getText().toString(), editTextcity.getText().toString(),
                                            editTextemailaddress.getText().toString(), editTextprice.getText().toString(), editTextage.getText().toString());

                                    CollectionReference dbupload = mDatabase.collection("uploadchildernfulltime");
                                    dbupload.add(upload).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(ImageFirebaseUploadDemo.this, "Success", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                }
                            });
                        }

                    } else if (spinnerjobtype.getSelectedItem().toString().equals("Part Time")) {


                        if (filePath != null) {
                            //displaying progress dialog while image is uploading
                            final ProgressDialog progressDialog = new ProgressDialog(ImageFirebaseUploadDemo.this);
                            progressDialog.setTitle("Uploading");
                            progressDialog.show();

                            final StorageReference sRef = parttimestoragereference.child(editTextName.getText().toString().trim() + "." + getFileExtension(filePath));

//                    sRef.putFile(filePath)
//                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                    progressDialog.dismiss();
//
//                                    //displaying success toast
//                                    Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
//
//                                    //creating the upload object to store uploaded image details
////                                    Upload upload = new Upload(editTextName.getText().toString().trim(), taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
//////
////                                    CollectionReference dbupload = mDatabase.collection("images");
////                                    dbupload.add(upload).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
////                                        @Override
////                                        public void onSuccess(DocumentReference documentReference) {
////                                            Toast.makeText(ImageFirebaseUploadDemo.this, "Success", Toast.LENGTH_SHORT).show();
////                                        }
////                                    });
//
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(ImageFirebaseUploadDemo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//
//                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                            progressDialog.setProgress((int)progress);
//
//                        }
//                    });

                            sRef.putFile(filePath).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    if (!task.isSuccessful()) {
                                        throw task.getException();
                                    }
                                    return sRef.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                progressDialog.setProgress(0);
                                            }
                                        }, 500);
                                    }
                                    downloadUri = task.getResult().toString();
                                    UploadChildrenPartTimepojo uploadChildrenPartTimepojo = new UploadChildrenPartTimepojo(editTextName.getText().toString().trim(),
                                            editTextphone.getText().toString().trim(),
                                            editTextaddress.getText().toString().trim(),
                                            editTextaboutyourself.getText().toString(),
                                            editTextexperience.getText().toString()
                                                    .trim(),
                                            spinnerttimings.getSelectedItem().toString(),
                                            spinnerjobtype.getSelectedItem().toString(),
                                            downloadUri,
                                            spinnergender.getSelectedItem().toString(),

                                            arrlst, editTextprovience.getText().toString(), editTextcity.getText().toString(),
                                            editTextemailaddress.getText().toString(), editTextprice.getText().toString(), editTextage.getText().toString());

                                    CollectionReference dbupload = mDatabase.collection("childernparttime");
                                    dbupload.add(uploadChildrenPartTimepojo).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(ImageFirebaseUploadDemo.this, "Success", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                }
                            });
                        }

                    }

                } else {
                    Toast.makeText(ImageFirebaseUploadDemo.this, "Please enter all the Credentials", Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    private List<FilterModel> getSchedules() {
        List<FilterModel> modelList = new ArrayList<FilterModel>();
        modelList.add(new FilterModel("Monday", false));
        modelList.add(new FilterModel("Tuesday", false));
        modelList.add(new FilterModel("Wednesday", false));
        modelList.add(new FilterModel("Thursday", false));
        modelList.add(new FilterModel("Friday", false));
        modelList.add(new FilterModel("Saturday", false));
        modelList.add(new FilterModel("Sunday", false));


        return modelList;
    }


    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
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
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public class ProductFilterRecyclerViewAdapter extends
            RecyclerView.Adapter<ProductFilterRecyclerViewAdapter.ViewHolder> {

        private List<FilterModel> filterList;
        private Context context;

        public ProductFilterRecyclerViewAdapter(List<FilterModel> filterModelList
                , Context ctx) {
            filterList = filterModelList;
            context = ctx;
        }

        @Override
        public ProductFilterRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                              int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.filter_brand_item, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            FilterModel filterM = filterList.get(position);
            holder.days.setText(filterM.getSchedule());
            //holder.productCount.setText("" + filterM.getPrice());
            if (filterM.getval()) {
                holder.selectionState.setChecked(true);

            } else {
                holder.selectionState.setChecked(false);
            }

        }

        @Override
        public int getItemCount() {
            return filterList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public TextView days;
            public TextView productCount;
            public CheckBox selectionState;

            public ViewHolder(View view) {
                super(view);
                days = (TextView) view.findViewById(R.id.brand_name);
                productCount = (TextView) view.findViewById(R.id.product_count);
                selectionState = (CheckBox) view.findViewById(R.id.brand_select);

                //item click event listener
                view.setOnClickListener(this);

                //checkbox click event handling
                selectionState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {

                        if (spinnerjobtype.getSelectedItem().toString().equals("Full Time")) {

                            if (count <= 4) {
                                if (!isChecked) {
                                    count--;
                                    Toast.makeText(ProductFilterRecyclerViewAdapter.this.context,
                                            "unselect ",
                                            Toast.LENGTH_SHORT).show();
                                    String val = days.getText().toString();
                                    arrlst.remove(val);
                                } else {
                                    String val = days.getText().toString();
                                    arrlst.add(val);

                                    count++;

                                    Toast.makeText(ProductFilterRecyclerViewAdapter.this.context,
                                            "selected day is " + days.getText(),
                                            Toast.LENGTH_SHORT).show();

                                    //aarrylist.add(brandName.getText())
                                }
                            } else {
                                Toast.makeText(ProductFilterRecyclerViewAdapter.this.context,
                                        "you can select only 5 days ",
                                        Toast.LENGTH_SHORT).show();
//                        if (!isChecked) {
//                            count--;
//                            Toast.makeText(ProductFilterRecyclerViewAdapter.this.context,
//                                    "unselect ",
//                                    Toast.LENGTH_SHORT).show();
//                        }
                            }
                        } else if (spinnerjobtype.getSelectedItem().toString().equals("Part Time")) {


                            if (count <= 2) {
                                if (!isChecked) {
                                    count--;
                                    Toast.makeText(ProductFilterRecyclerViewAdapter.this.context,
                                            "unselect ",
                                            Toast.LENGTH_SHORT).show();
                                    String val = days.getText().toString();
                                    arrlst.remove(val);
                                } else {
                                    String val = days.getText().toString();
                                    arrlst.add(val);

                                    count++;

                                    Toast.makeText(ProductFilterRecyclerViewAdapter.this.context,
                                            "selected day is " + days.getText(),
                                            Toast.LENGTH_SHORT).show();

                                    //aarrylist.add(brandName.getText())
                                }
                            } else {
                                Toast.makeText(ProductFilterRecyclerViewAdapter.this.context,
                                        "you can select only 3 days ",
                                        Toast.LENGTH_SHORT).show();
//                        if (!isChecked) {
//                            count--;
//                            Toast.makeText(ProductFilterRecyclerViewAdapter.this.context,
//                                    "unselect ",
//                                    Toast.LENGTH_SHORT).show();
//                        }
                            }


                        }
                    }

                });

            }

            @Override
            public void onClick(View v) {
                TextView brndName = (TextView) v.findViewById(R.id.brand_name);
                //show more information about brand
            }
        }
    }

    public void m2() {
        Intent intent = new Intent(ImageFirebaseUploadDemo.this, Adminactivity.class);
        startActivity(intent);
    }

    public void m3() {
        Intent intent = new Intent(ImageFirebaseUploadDemo.this, privaceandsecurity.class);
        startActivity(intent);
    }

    public void m1() {
        Intent intent = new Intent(ImageFirebaseUploadDemo.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Hellp:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.back:
                Toast.makeText(this, "Back to previous Page:", Toast.LENGTH_SHORT).show();
                homeee();
                break;
            case R.id.LogOut:
                Toast.makeText(this, "Log Out:", Toast.LENGTH_SHORT).show();
                a5();
                break;
        }
        {
            if (mtoggle.onOptionsItemSelected(item)) {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void homeee() {
        Intent intent = new Intent(ImageFirebaseUploadDemo.this, naniesUpload.class);
        startActivity(intent);
    }

    public void a5() {
        finish();
    }
    public void k5()
    {
        Intent intent=new Intent(ImageFirebaseUploadDemo.this,Ratetheapp.class);
        startActivity(intent);
    }
    public void k6()
    {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("Text");
        String sharebody="Your Body Here";
        String sharesub="Your subject here";
        intent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
        intent.putExtra(Intent.EXTRA_TEXT,sharebody);
        startActivity(Intent.createChooser(intent,"Share using"));
    }
}
