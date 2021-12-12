package com.example.malaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button scanButton;
    private Button database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = (Button) findViewById(R.id.newScan);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewScan();
            }
        });

        database = (Button) findViewById(R.id.database);
        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatabase();
            }
        });

    }

    public void openNewScan(){
        Intent intent = new Intent(this, New_Scan.class);
        startActivity(intent);
    }

    public void openDatabase(){
        Intent intent = new Intent(this, Database.class);
        startActivity(intent);
    }
}