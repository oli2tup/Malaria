package com.example.malaria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText initials, lastName, date, age;
    Button addInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initials = findViewById(R.id.initials_input);
        lastName = findViewById(R.id.lastName_input);
        date = findViewById(R.id.date_input);
        age = findViewById(R.id.age_input);
        addInfo = findViewById(R.id.addInfo);

        addInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDatabase();
            }
        });

    }

    void addToDatabase(){
        SQL_Database myDB = new SQL_Database(AddActivity.this);
        myDB.addSample(initials.getText().toString().trim(), lastName.getText().toString().trim(),
                Integer.parseInt(date.getText().toString().trim()),
                Integer.parseInt(age.getText().toString().trim()));
    }
}