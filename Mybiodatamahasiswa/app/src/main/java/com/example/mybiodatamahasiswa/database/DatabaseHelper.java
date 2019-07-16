package com.example.mybiodatamahasiswa.database;

import android.content.ContentValues; import android.content.Context; import android.database.Cursor; import android.database.sqlite.SQLiteDatabase; import android.database.sqlite.SQLiteOpenHelper; import java.util.ArrayList; import java.util.List;
public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "mahasiswa_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

// create notes table
        db.execSQL(Mahasiswa.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Mahasiswa.TABLE_NAME);

// Create tables again
        onCreate(db);
    }
}


    lalu selanjutnya kita tambahkan beberapa method berikut untuk melakukan proses CRUD.

        Menyimpan DatainsertMahasiswa(), method berikut digunakan untuk menyimpan proses input kedalam database.
        Pada proses ini memerlukan writable instance yaitu getWritableDatabase().

public long insertMahasiswa(String nama, String jenis_kelamin) {    // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
// `id` and `jurusan` will be inserted automatically.
        // no need to add them
        values.put(Mahasiswa.COLUMN_NAME, nama);    values.put(Mahasiswa.COLUMN_GENDER, jenis_kelamin);

// insert row
        long nim = db.insert(Mahasiswa.TABLE_NAME, null, values);

// close db connection
        db.close();

// return newly inserted row id
        return nim;
        }

