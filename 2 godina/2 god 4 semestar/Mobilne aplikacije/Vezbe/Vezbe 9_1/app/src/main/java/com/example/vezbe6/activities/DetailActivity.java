package com.example.vezbe6.activities;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.vezbe6.database.Util;
import com.example.vezbe6.model.Cinema;
import com.example.vezbe6.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();

        Uri todoUri = extras.getParcelable("id");
        fillData(todoUri);
    }

    private void fillData(Uri todoUri) {
        Cinema cinema = Util.query(todoUri, this);

        TextView tvName = (TextView)findViewById(R.id.tvName);
        TextView tvDescr = (TextView)findViewById(R.id.tvDescr);

        tvName.setText(cinema.getName());
        tvDescr.setText(cinema.getDescription());

    }


}
