package com.example.vezbe9_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button initDB = findViewById(R.id.button_init);
        initDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloudStoreUtil.initDB();
                Log.i("REZ_DB", "INIT DATABASE");
            }
        });
        Button insert = findViewById(R.id.button_insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloudStoreUtil.insert();
                Log.i("REZ_DB", "INSERT DATA");
            }
        });
        Button update = findViewById(R.id.button_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloudStoreUtil.update();
                Log.i("REZ_DB", "UPDATE DATA");
            }
        });
        Button delete = findViewById(R.id.button_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloudStoreUtil.delete();
                Log.i("REZ_DB", "DELETE DATA");
            }
        });
        Button select = findViewById(R.id.button_select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloudStoreUtil.select();
                Log.i("REZ_DB", "SELECT DATA");

            }
        });
        Button select2 = findViewById(R.id.button_select2);
        select2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloudStoreUtil.selectById();
                Log.i("REZ_DB", "SELECT DATA BY ID ");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}