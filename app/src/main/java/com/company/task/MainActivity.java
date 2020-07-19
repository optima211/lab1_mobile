package com.company.task;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final ArrayList <String> arrayList = new ArrayList<>();

    EditText name, weight, growth;
    Button AddBtn, ClearBtn, ReadBtn;
    DataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList.clear();
        AddBtn = findViewById(R.id.addBtn);
        AddBtn.setOnClickListener((View.OnClickListener) this);
        ClearBtn = findViewById(R.id.ClearBtn);
        ClearBtn.setOnClickListener((View.OnClickListener) this);
        ReadBtn = findViewById(R.id.ShowBtn);
        ReadBtn.setOnClickListener((View.OnClickListener) this);
        name = findViewById(R.id.ETName);
        weight = findViewById(R.id.weightET);
        growth = findViewById(R.id.growthET);
        dataBase = new DataBase(this);
    }

    @Override
    public void onClick(View v) {


        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch ((v.getId())) {

            case R.id.addBtn: //Добавление
                contentValues.put("Name", name.getText().toString());

                int wei = Integer.valueOf(weight.getText().toString());
                contentValues.put("Weight", wei);

                int gro = Integer.valueOf(growth.getText().toString());
                contentValues.put("Growth", gro);

                Random r = new Random();
                contentValues.put("Age", r.nextInt(100));

                sqLiteDatabase.insert("Person", null, contentValues);

                Toast.makeText(getApplicationContext(), "Данные были добавлены в БД", Toast.LENGTH_LONG).show();

                dataBase.close();
                name.setText("");
                growth.setText("");
                weight.setText("");
                break;

            case R.id.ShowBtn: //чтение
                dataBase.close();
                Intent intent = new Intent(MainActivity.this, Show.class);
                startActivity(intent);
                break;

            case R.id.ClearBtn: //запись
                sqLiteDatabase.delete("Person", null,null);
                Toast.makeText(getApplicationContext(), "Таблица успешно очищена", Toast.LENGTH_LONG).show();
                arrayList.clear();
                dataBase.close();
                break;

        }

    }
}
