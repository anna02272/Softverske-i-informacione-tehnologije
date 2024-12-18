package com.example.vezbe10_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.vezbe10_1.fragments.MessageFragment;
import com.example.vezbe10_1.tools.FragmentTransition;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransition.to(MessageFragment.newInstance(), this, true, R.id.fragmet1);
    }
}