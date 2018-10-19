package com.example.csitgis.lab6_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME= "student_db";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(student.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
        db.execSQL("DROP TABLE IF EXISTS " + student.TABLE_NAME);
        onCreate(db);
    }

    public  void insertStudent(String id, String student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(com.example.csitgis.lab6_sqlite.student.COLUMN_ID, id);
        values.put(com.example.csitgis.lab6_sqlite.student.COLUMN_NAME, student);

        db.insert(com.example.csitgis.lab6_sqlite.student.TABLE_NAME, null, values);
        db.close();
    }

    public student getStudent(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(student.TABLE_NAME,
                new String[]{student.COLUMN_ID, student.COLUMN_NAME},
                student.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        student student = new student(
                cursor.getString(cursor.getColumnIndex(com.example.csitgis.lab6_sqlite.student.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(com.example.csitgis.lab6_sqlite.student.COLUMN_NAME))
        );
        cursor.close();
        return student;
    }

    public List<student> getAllStudents(){
        List<student> students = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + student.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                student student = new student();
                student.setId(cursor.getString(cursor.getColumnIndex(com.example.csitgis.lab6_sqlite.student.COLUMN_ID)));
                student.setName(cursor.getString(cursor.getColumnIndex(com.example.csitgis.lab6_sqlite.student.COLUMN_NAME)));
                students.add(student);
            }while (cursor.moveToNext());
        }
        db.close();
        return students;
    }

    public int updateStudent(student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(com.example.csitgis.lab6_sqlite.student.COLUMN_ID, student.getId());
        values.put(com.example.csitgis.lab6_sqlite.student.COLUMN_NAME, student.getName());

        return db.update(com.example.csitgis.lab6_sqlite.student.TABLE_NAME, values, com.example.csitgis.lab6_sqlite.student.COLUMN_ID + " = ?",
                new  String[]{String.valueOf(student.getId())});
    }

    public void deleteStudents(student student){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(com.example.csitgis.lab6_sqlite.student.TABLE_NAME, com.example.csitgis.lab6_sqlite.student.COLUMN_ID + " = ?",
                new  String[]{String.valueOf(student.getId())});
        db.close();
    }

}
