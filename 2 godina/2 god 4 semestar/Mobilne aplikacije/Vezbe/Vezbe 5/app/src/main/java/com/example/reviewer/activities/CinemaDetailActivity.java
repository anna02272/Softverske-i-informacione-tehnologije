package com.example.reviewer.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.reviewer.R;

public class CinemaDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cinema_detail_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescr = findViewById(R.id.tvDescr);

        tvName.setText(getIntent().getStringExtra("name"));
        tvDescr.setText(getIntent().getStringExtra("descr"));
    }

}
