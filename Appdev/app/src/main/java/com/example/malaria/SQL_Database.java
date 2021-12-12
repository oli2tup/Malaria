package com.example.malaria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQL_Database extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Malaria.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "patients";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_INITIALS = "initials";
    private static final String COLUMN_LASTNAME = "last_name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_DATE = "date";

    SQL_Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_INITIALS + " TEXT, " +
                        COLUMN_LASTNAME + " TEXT, " +
                        COLUMN_AGE + " INTEGER, " +
                        COLUMN_DATE + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addSample(String initials, String lastName, int date, int age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_INITIALS, initials);
        cv.put(COLUMN_LASTNAME, lastName);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_AGE, age);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1 ){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    void updateData(String row_id, String initials, String lastName, String date, String age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_INITIALS, initials);
        cv.put(COLUMN_LASTNAME, lastName);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_AGE, age);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();
        }
    }
}
