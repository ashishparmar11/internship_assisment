package com.example.internship_assisment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class dashboard extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radio1,radio2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        addListner();
    }
    public void addListner()
    {
        radioGroup= findViewById(R.id.radioGroup);

        button =  findViewById(R.id.button33);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = radioGroup.getCheckedRadioButtonId();
                radio1  =findViewById(selected);
                if(!radio1.isChecked())
                    Toast.makeText(dashboard.this , "Please Select stream ", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(dashboard.this , radio1.getText(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}