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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

public class imageUploadsenior extends AppCompatActivity {

    //constant to track image chooser intent
    private static final int PICK_IMAGE_REQUEST = 234;

    //view objects
    private Button buttonChoose;
    private Button buttonUpload;
    private EditText editTextName,editTextphone,editTextaddress,editTextprovience,editTextaboutyourself,editTextexperience,editTextcity,editTextemailaddress,editTextprice,editTextage;
    private TextView textViewShow;
    private ImageView imageView,getImageView;
    Spinner spinnerjobtype,spinnergender,spinnerttimings;

    //uri to store file
    private Uri filePath;

    String downloadUri;

    //firebase objects
    private StorageReference storageReference,parttimestoragereference;
    //    private FirebaseStorage firebaseStorage;
    private FirebaseFirestore mDatabase,parttimeDatabase;

    private RecyclerView RecyclerView;
    int count=0;
    ArrayList<String> arrlst=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_uploadsenior);



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



        mDatabase = FirebaseFirestore.getInstance();
//        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("seniorfulltime");

        parttimeDatabase=FirebaseFirestore.getInstance();
        parttimestoragereference=FirebaseStorage.getInstance().getReference("seniorparttime");

//        final CollectionReference dbupload = mDatabase.collection("upload");

        textViewShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                  to see all image in recycler view
//                startActivity(new Intent(ImageFirebaseUploadDemo.this,ImageActivity.class));
                Picasso.with(imageUploadsenior.this)
                        .load(downloadUri)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(getImageView);

            }
        });


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
        gender.add("other");


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
        Timings.add("evening");
        Timings.add("night");


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
                ProductFilterRecyclerViewAdapter(getSchedules(),this);
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
                final ProgressDialog progressDialog = new ProgressDialog(imageUploadsenior.this);
                progressDialog.setTitle("Uploading");
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
                                    UploadSenior uploadSenior = new UploadSenior(editTextName.getText().toString().trim(),
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

                                    CollectionReference dbupload = mDatabase.collection("seniorfulltime");
                                    dbupload.add(uploadSenior).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(imageUploadsenior.this, "Success", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();

                                            String firstname=editTextName.getText().toString().trim();
                                            String phone=editTextphone.getText().toString().trim();
                                            String address=editTextaddress.getText().toString().trim();
                                            String writeaboutyourself=editTextaboutyourself.getText().toString().trim();
                                            String experience=editTextexperience.getText().toString().trim();
                                            String timings=spinnerttimings.getSelectedItem().toString();

                                            String jobtype=spinnerjobtype.getSelectedItem().toString();
                                            String gender=spinnergender.getSelectedItem().toString();
                                            String provience=editTextaboutyourself.getText().toString().trim();
                                            String city=editTextcity.getText().toString().trim();
                                            String emailaddress=editTextemailaddress.getText().toString().trim();
                                            String price=editTextprice.getText().toString().trim();
                                            String age=editTextage.getText().toString().trim();



                                            String imageurl=downloadUri;
                                            Intent intent = new Intent(imageUploadsenior.this, displayProfile.class);
                                            intent.putExtra("firstname", firstname);
                                            intent.putExtra("url", downloadUri);
                                            intent.putExtra("phone", phone);
                                            intent.putExtra("address", address);
                                            intent.putExtra("writeaboutyourself", writeaboutyourself);
                                            intent.putExtra("experience", experience);
                                            intent.putExtra("timings", timings);
                                            intent.putExtra("jobtype", jobtype);
                                            intent.putExtra("gender", gender);
                                            intent.putExtra("provience", provience);
                                            intent.putExtra("city", city);
                                            intent.putExtra("emailaddress", emailaddress);
                                            intent.putExtra("url", imageurl);
                                            intent.putExtra("price", price);
                                            intent.putExtra("age", age);
                                            intent.putStringArrayListExtra("arraylist",arrlst);
                                            startActivity(intent);

                                        }
                                    });


                                }
                            });
                        }

                    } else if (spinnerjobtype.getSelectedItem().toString().equals("Part Time")) {


                        if (filePath != null) {
                            //displaying progress dialog while image is uploading

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
                                    UploadSeniorPartTimepojo uploadSeniorPartTimepojo = new UploadSeniorPartTimepojo(editTextName.getText().toString().trim(),
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

                                    CollectionReference dbupload = mDatabase.collection("seniorparttime");
                                    dbupload.add(uploadSeniorPartTimepojo).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(imageUploadsenior.this, "Success", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();

                                            String firstname=editTextName.getText().toString().trim();
                                            String phone=editTextphone.getText().toString().trim();
                                            String address=editTextaddress.getText().toString().trim();
                                            String writeaboutyourself=editTextaboutyourself.getText().toString().trim();
                                            String experience=editTextexperience.getText().toString().trim();
                                            String timings=spinnerttimings.getSelectedItem().toString();

                                            String jobtype=spinnerjobtype.getSelectedItem().toString();
                                            String gender=spinnergender.getSelectedItem().toString();
                                            String provience=editTextaboutyourself.getText().toString().trim();
                                            String city=editTextcity.getText().toString().trim();
                                            String emailaddress=editTextemailaddress.getText().toString().trim();
                                            String price=editTextprice.getText().toString().trim();
                                            String age=editTextage.getText().toString().trim();



                                            String imageurl=downloadUri;
                                            Intent intent = new Intent(imageUploadsenior.this, displayProfile.class);
                                            intent.putExtra("firstname", firstname);
                                            intent.putExtra("url", downloadUri);
                                            intent.putExtra("phone", phone);
                                            intent.putExtra("address", address);
                                            intent.putExtra("writeaboutyourself", writeaboutyourself);
                                            intent.putExtra("experience", experience);
                                            intent.putExtra("timings", timings);
                                            intent.putExtra("jobtype", jobtype);
                                            intent.putExtra("gender", gender);
                                            intent.putExtra("provience", provience);
                                            intent.putExtra("city", city);
                                            intent.putExtra("emailaddress", emailaddress);
                                            intent.putExtra("url", imageurl);
                                            intent.putExtra("price", price);
                                            intent.putExtra("age", age);
                                            intent.putStringArrayListExtra("arraylist",arrlst);
                                            startActivity(intent);

                                        }
                                    });


                                }
                            });
                        }

                    }

                }
                else{
                    Toast.makeText(imageUploadsenior.this,"Please enter all the Credentials",Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    private List<FilterModel> getSchedules(){
        List<FilterModel> modelList = new ArrayList<FilterModel>();
        modelList.add(new FilterModel("Monday",  false));
        modelList.add(new FilterModel("Tuesday", false));
        modelList.add(new FilterModel("Wednesday",  false));
        modelList.add(new FilterModel("Thursday",  false));
        modelList.add(new FilterModel("Friday",  false));
        modelList.add(new FilterModel("Saturday",  false));
        modelList.add(new FilterModel("Sunday",  false));


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

            ProductFilterRecyclerViewAdapter.ViewHolder viewHolder = new ProductFilterRecyclerViewAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ProductFilterRecyclerViewAdapter.ViewHolder holder, int position) {
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
                        }
                        else  if (spinnerjobtype.getSelectedItem().toString().equals("Part Time")) {


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


}
