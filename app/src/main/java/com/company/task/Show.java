package com.company.task;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import java.util.ArrayList;

public class Show extends AppCompatActivity {

    EditText etN, etW, etG, etA;
    ArrayList<String> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        etN = findViewById(R.id.etName);
        etG = findViewById(R.id.etGrowth);
        etA = findViewById(R.id.etAge);
        etW = findViewById(R.id.etWeiht);

        DataBase dataBase = new DataBase(this);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("Person", null, null, null, null, null, "Age");

        if (cursor.moveToFirst()) {
            do {
                arr.add( cursor.getString(cursor.getColumnIndex("Name")) );
                arr.add( String.valueOf(cursor.getInt(cursor.getColumnIndex("Weight"))) );
                arr.add( String.valueOf(cursor.getInt(cursor.getColumnIndex("Growth"))));
                arr.add( String.valueOf(cursor.getInt(cursor.getColumnIndex("Age"))) );
            } while ( cursor.moveToNext() );
        }

        for (int i = 0; i < arr.size(); i += 4) {
            etN.setText(etN.getText().toString() + "\n" + arr.get(i));
            etW.setText(etW.getText().toString() + "\n" + arr.get(i + 1));
            etG.setText(etG.getText().toString() + "\n" + arr.get(i + 2));
            etA.setText(etA.getText().toString() + "\n" + arr.get(i + 3));
        }

    }
}
