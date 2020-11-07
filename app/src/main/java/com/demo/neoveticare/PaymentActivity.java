package com.demo.neoveticare;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.braintreepayments.cardform.OnCardFormSubmitListener;
import com.braintreepayments.cardform.utils.CardType;
import com.braintreepayments.cardform.view.CardEditText;
import com.braintreepayments.cardform.view.CardForm;
import com.braintreepayments.cardform.view.SupportedCardTypesView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PaymentActivity extends AppCompatActivity implements OnCardFormSubmitListener,
        CardEditText.OnCardTypeChangedListener {

    private static final CardType[] SUPPORTED_CARD_TYPES = {CardType.VISA, CardType.MASTERCARD, CardType.DISCOVER,
            CardType.AMEX, CardType.DINERS_CLUB, CardType.JCB, CardType.MAESTRO, CardType.UNIONPAY,
            CardType.HIPER, CardType.HIPERCARD};

    private SupportedCardTypesView mSupportedCardTypesView;

    protected CardForm mCardForm;
    Button btnPay;
    String documentId;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        documentId = getIntent().getStringExtra("documentId");
        mSupportedCardTypesView = findViewById(R.id.supported_card_types);
        mSupportedCardTypesView.setSupportedCardTypes(SUPPORTED_CARD_TYPES);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mCardForm = findViewById(R.id.card_form);
        mCardForm.cardRequired(true)
                .maskCardNumber(true)
                .maskCvv(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .saveCardCheckBoxChecked(true)
                .saveCardCheckBoxVisible(true)
                .cardholderName(CardForm.FIELD_REQUIRED)
                .mobileNumberExplanation("Make sure SMS is enabled for this mobile number")
                .actionLabel("Pay for services")
                .setup(this);
        mCardForm.setOnCardFormSubmitListener(this);
        mCardForm.setOnCardTypeChangedListener(this);


        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);

        btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mCardForm.getCardNumber())) {
                    Toast.makeText(PaymentActivity.this, "Please enter card number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(mCardForm.getExpirationMonth())) {
                    Toast.makeText(PaymentActivity.this, "Please enter month", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mCardForm.getExpirationYear())) {
                    Toast.makeText(PaymentActivity.this, "Please enter year", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(mCardForm.getCvv())) {
                    Toast.makeText(PaymentActivity.this, "Please enter CVV", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String id = firebaseFirestore.collection("payments").document().getId();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date date = new Date();
                Map<String, Object> payment = new HashMap<>();
                payment.put("id", id);
                payment.put("card", mCardForm.getCardNumber());
                payment.put("documentId", documentId);
                payment.put("datetime", formatter.format(date));

                firebaseFirestore.collection("payments").document(id)
                        .set(payment)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                finish();
                                Toast.makeText(PaymentActivity.this, "Payment Successfully", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        });
    }

    @Override
    public void onCardTypeChanged(CardType cardType) {
        if (cardType == CardType.EMPTY) {
            mSupportedCardTypesView.setSupportedCardTypes(SUPPORTED_CARD_TYPES);
        } else {
            mSupportedCardTypesView.setSelected(cardType);
        }
    }

    @Override
    public void onCardFormSubmit() {
        if (mCardForm.isValid()) {
            Toast.makeText(this, R.string.valid, Toast.LENGTH_SHORT).show();
        } else {
            mCardForm.validate();
            Toast.makeText(this, R.string.invalid, Toast.LENGTH_SHORT).show();
        }
    }
}