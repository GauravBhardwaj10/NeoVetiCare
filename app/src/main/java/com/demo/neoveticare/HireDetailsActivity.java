package com.demo.neoveticare;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class HireDetailsActivity extends AppCompatActivity {

    TextView tvParentEmail, tvMonday, tvTuesday, tvWednesday, tvThursday, tvFriday, tvSaturday, tvSunday, tvTotalPayment, tvPaymentStatus;
    FirebaseFirestore firebaseFirestore;
    String documentId, hireId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_details);
        documentId = getIntent().getStringExtra("documentId");
        firebaseFirestore = FirebaseFirestore.getInstance();
        tvParentEmail = findViewById(R.id.tvParentEmail);
        tvMonday = findViewById(R.id.tvMonday);
        tvTuesday = findViewById(R.id.tvTuesday);
        tvWednesday = findViewById(R.id.tvWednesday);
        tvThursday = findViewById(R.id.tvThursday);
        tvFriday = findViewById(R.id.tvFriday);
        tvSaturday = findViewById(R.id.tvSaturday);
        tvSunday = findViewById(R.id.tvSunday);
        tvTotalPayment = findViewById(R.id.tvTotalPayment);
        tvPaymentStatus = findViewById(R.id.tvPaymentStatus);
        getHireDetails();
    }

    private void getHireDetails() {

        firebaseFirestore.collection("hires").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {

                            return;
                        } else {
                            List<Hire> hires = documentSnapshots.toObjects(Hire.class);

                            for (int i = 0; i < hires.size(); i++) {

                                if (TextUtils.equals(documentSnapshots.getDocuments().get(i).getId(), documentId)) {

                                    Hire hire = hires.get(i);
                                    tvParentEmail.setText(hire.getParentEmail());
                                    tvMonday.setText(hire.getMon() + " Hours");
                                    tvTuesday.setText(hire.getTue() + " Hours");
                                    tvWednesday.setText(hire.getWed() + " Hours");
                                    tvThursday.setText(hire.getThu()+ " Hours");
                                    tvFriday.setText(hire.getFri()+ " Hours");
                                    tvSaturday.setText(hire.getSat()+ " Hours");
                                    tvSunday.setText(hire.getSun()+ " Hours");
                                    tvTotalPayment.setText(hire.getTotal() + " CAD");
                                    hireId = hire.getId();
                                    checkPaymentStaus();

                                }


                            }


                        }
                    }


                });

    }

    private void checkPaymentStaus() {
        tvPaymentStatus.setText("Not yet payment done");

        firebaseFirestore.collection("payments").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {

                            return;
                        } else {
                            List<Payment> payments = documentSnapshots.toObjects(Payment.class);

                            for (int i = 0; i < payments.size(); i++) {

                                if (TextUtils.equals(documentSnapshots.getDocuments().get(i).getId(), hireId)) {
                                    tvPaymentStatus.setText("Paid");



                                }


                            }


                        }
                    }


                });

    }
}
