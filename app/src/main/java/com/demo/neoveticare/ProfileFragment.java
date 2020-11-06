package com.demo.neoveticare;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String email;
    AppCompatTextView tvName, tvGender, tvAddress, tvCity, tvEmail, tvPhone, tvPrice, tvExperience, tvType;
    TextView tvProfileType;
    CircleImageView ivProfile;
    Button btnEdit;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        email = firebaseAuth.getCurrentUser().getEmail();
        ivProfile = root.findViewById(R.id.ivProfile);
        tvName = root.findViewById(R.id.tvName);
        tvGender = root.findViewById(R.id.tvGender);
        tvAddress = root.findViewById(R.id.tvAddress);
        tvCity = root.findViewById(R.id.tvCity);
        tvEmail = root.findViewById(R.id.tvEmail);
        tvPhone = root.findViewById(R.id.tvPhone);
        tvPrice = root.findViewById(R.id.tvPrice);
        tvExperience = root.findViewById(R.id.tvExperience);
        tvType = root.findViewById(R.id.tvType);
        tvProfileType = root.findViewById(R.id.tvProfileType);
        if (TabActivity.table.contains("child")) {
            tvProfileType.setText("Babysitter");
        } else {
            tvProfileType.setText("Caretaker");
        }
        btnEdit = root.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditProfileActivity.class);
                i.putExtra("table", TabActivity.table);
                startActivity(i);
            }
        });

        getProfile();
        return root;

    }

    private void getProfile() {

        firebaseFirestore.collection(TabActivity.table).get()
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

                                Upload upload = uploads.get(i);
                                if (TextUtils.equals(upload.getEmailaddress(), email)) {
                                    Picasso.with(getContext()).load(upload.getUrl()).into(ivProfile);
                                    tvName.setText(upload.getName());
                                    tvGender.setText(upload.getAge() + "yrs., " + upload.getGender());
                                    tvAddress.setText(upload.getAddress());
                                    tvCity.setText(upload.getCity());
                                    tvEmail.setText(upload.getEmailaddress());
                                    tvPhone.setText(upload.getPhone());
                                    tvPrice.setText("Price : " + upload.getPrice());
                                    tvExperience.setText("Experience : " + upload.getExperience());
                                    tvType.setText("Job type : " + upload.getJobtype());
                                }


                            }

                        }
                    }


                });


    }

}
