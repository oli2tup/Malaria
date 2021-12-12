package com.example.malaria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Table extends AppCompatActivity {

    EditText initials_input, lastName_input, date_input, age_input;
    Button update_button;

    String id, initials, lastName, date, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_table);

        initials_input = findViewById(R.id.initials_input2);
        lastName_input = findViewById(R.id.lastName_input2);
        date_input = findViewById(R.id.date_input2);
        age_input = findViewById(R.id.age_input2);
        update_button = findViewById(R.id.update_button);
        // First call this
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQL_Database myDB = new SQL_Database(Update_Table.this);
                myDB.updateData(id, initials, lastName, date, age);
            }
        });

    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("initials")
                && getIntent().hasExtra("lastName") && getIntent().hasExtra("date")
                && getIntent().hasExtra("age")){

            //Getting data from intent
            id = getIntent().getStringExtra("id");
            initials = getIntent().getStringExtra("initials");
            lastName = getIntent().getStringExtra("lastName");
            date = getIntent().getStringExtra("date");
            age = getIntent().getStringExtra("age");

            //Setting intent data
            initials_input.setText(initials);
            lastName_input.setText(lastName);
            date_input.setText(date);
            age_input.setText(age);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}