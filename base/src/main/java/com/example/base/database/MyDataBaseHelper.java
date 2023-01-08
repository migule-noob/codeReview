package com.example.base.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.base.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME = "person";
    private static final int DATABASEVERSION = 1;
    private SQLiteDatabase database = getReadableDatabase();
    List<Person> personList = new ArrayList<>();

    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDataBase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createDataBase() {
        String sql = "create table person_table(id integer,name verChar(30),age int)";
        SQLiteDatabase database = getReadableDatabase();
        database.execSQL(sql);
    }

    public void insert() {
        ContentValues values = new ContentValues();
        values.put("id","1");
        values.put("name","migule");
        values.put("age","15");
        database.insert("person_table",null,values);
    }

    @SuppressLint("Range")
    public List<Person> queryAll() {
        Person person = new Person();
        personList.clear();
        Cursor cursor = database.query("person_table", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                person.setId(cursor.getInt(cursor.getColumnIndex("id")));
                person.setName(cursor.getString(cursor.getColumnIndex("name")));
                person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                personList.add(person);
            }
            cursor.close();
        }
        return personList;
    }
}
