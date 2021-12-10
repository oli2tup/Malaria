package com.example.malaria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public SQL_Database(@Nullable Context context) {
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
                        COLUMN_AGE + " INTEGER) ; ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
