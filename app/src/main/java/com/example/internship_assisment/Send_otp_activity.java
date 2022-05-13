package com.example.internship_assisment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Send_otp_activity extends AppCompatActivity {

    Button button ;
    EditText editNumber;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        button = findViewById(R.id.button11);
        editNumber = findViewById(R.id.enterNumber);
        progressBar  = findViewById(R.id.progress_bar_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editNumber.getText().toString().trim().isEmpty())
                {
                    if(editNumber.getText().toString().trim().length() ==10)
                    {
                        progressBar.setVisibility(View.VISIBLE);
                        button.setVisibility(View.INVISIBLE);
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + editNumber.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                Send_otp_activity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        button.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        button.setVisibility(View.VISIBLE);
                                        Toast.makeText(Send_otp_activity.this , e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.GONE);
                                        button.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(getApplicationContext() , verification_activity.class);
                                        intent.putExtra("mobile",editNumber.getText().toString());
                                        intent.putExtra("verification",s);

                                        startActivity(intent);
                                    }
                                }
                        );


                    }
                    else
                        Toast.makeText(Send_otp_activity.this , "Please enter 10 digit number", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Send_otp_activity.this , "Enter Mobile Number", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}