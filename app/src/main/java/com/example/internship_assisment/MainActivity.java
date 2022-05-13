package com.example.internship_assisment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] items = {"English", "Gujarati", "Hindi", "German", "Spanish", "English-US"};
    AutoCompleteTextView auto_text;
    ArrayAdapter<String> arrayAdapter;
    Button next_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auto_text = findViewById(R.id.autoCompleteTextView);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.language_selector, items);
        auto_text.setAdapter(arrayAdapter);
        auto_text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "item :" + item, Toast.LENGTH_SHORT).show();
            }
        });
        next_b = findViewById(R.id.button);
        next_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchA();
            }
        });
    }

    private void switchA()
    {
        Intent intent = new Intent(this , Send_otp_activity.class);
        startActivity(intent);
    }
}