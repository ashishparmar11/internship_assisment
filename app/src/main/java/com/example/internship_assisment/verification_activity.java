package com.example.internship_assisment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class verification_activity extends AppCompatActivity {

    String getOTPback;
    EditText inN1 , inN2, inN3, inN4, inN5, inN6;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        inN1= findViewById(R.id.num1);
        inN2= findViewById(R.id.num2);
        inN3= findViewById(R.id.num3);
        inN4= findViewById(R.id.num4);
        inN5= findViewById(R.id.num5);
        inN6= findViewById(R.id.num6);

        TextView text  = findViewById(R.id.textMobile);
        text.setText(String.format("+91.%s" ,  getIntent().getStringExtra("mobile")));

        getOTPback = getIntent().getStringExtra("verification");

        final ProgressBar progressBar  = findViewById(R.id.progress_bar_ver);
        button = findViewById(R.id.button33);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enterCodeOtp ="";
                if(!inN1.getText().toString().trim().isEmpty() &&
                    !inN2.getText().toString().trim().isEmpty() &&
                        !inN3.getText().toString().trim().isEmpty() &&
                        !inN4.getText().toString().trim().isEmpty() &&
                        !inN5.getText().toString().trim().isEmpty() &&
                        !inN6.getText().toString().trim().isEmpty() )
                {
                    enterCodeOtp = inN1.getText().toString() +
                            inN2.getText().toString() +
                            inN3.getText().toString() +
                            inN4.getText().toString() +
                            inN5.getText().toString() +
                            inN6.getText().toString();
                    if(getOTPback != null)
                    {
                        progressBar.setVisibility(View.VISIBLE);
                        button.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getOTPback , enterCodeOtp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        button.setVisibility(View.VISIBLE);

                                        if(task.isSuccessful())
                                        {
                                            Intent intent = new Intent(getApplicationContext(),dashboard.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Toast.makeText(verification_activity.this , "OTP NOT MATCH", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                    }
                    else
                    {
                        Toast.makeText(verification_activity.this , "Please fill all 6 number", Toast.LENGTH_SHORT).show();
                    }
                }

                else
                    Toast.makeText(verification_activity.this , "Please fill all 6 number", Toast.LENGTH_SHORT).show();

            }
        });
        numberMove();
        findViewById(R.id.resend_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        verification_activity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(verification_activity.this , e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String s_new, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                    getOTPback = s_new;
                                Toast.makeText(verification_activity.this , "OTP SENDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();

                            }
                        }
                );

            }
        });
    }


    private void numberMove() {
        inN1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                    inN2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inN2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                    inN3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inN3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                    inN4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inN4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                    inN5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inN5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                    inN6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}