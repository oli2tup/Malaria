package com.example.malaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Database extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    SQL_Database myDB;
    ArrayList<String> id, initials, lastName, date, age;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewData();
            }
        });

        myDB = new SQL_Database(this);
        id = new ArrayList<>();
        initials = new ArrayList<>();
        lastName = new ArrayList<>();
        date = new ArrayList<>();
        age = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(this, id, initials, lastName, date, age);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Database.this));
    }

    public void addNewData(){
        Intent intent = new Intent(this, AddActivity.class );
        startActivity(intent);
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0 ) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while ( cursor.moveToNext()){
                id.add(cursor.getString(0));
                initials.add(cursor.getString(1));
                lastName.add(cursor.getString(2));
                date.add(cursor.getString(3));
                age.add(cursor.getString(4));
            }
        }
    }
}