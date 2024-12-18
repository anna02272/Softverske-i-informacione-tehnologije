package com.example.vezbe10_2;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.vezbe10_2.config.SocketHandler;
import com.example.vezbe10_2.fragments.MessageFragment;
import com.example.vezbe10_2.tools.FragmentTransition;

import io.socket.client.Socket;

public class MainActivity extends AppCompatActivity {
    public static Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tw2 = findViewById(R.id.textView2);
        Button button = findViewById(R.id.button1);

        SocketHandler.setSocket();

        socket = SocketHandler.getSocket();
        socket.connect();

        button.setOnClickListener(v -> {
            socket.emit("counter");
        });


        socket.on("counter", args -> {
            if(args[0] != null){
                Integer s = (Integer) args[0];
                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        tw2.setText(s.toString());
                    }
                });
            }
        });

        FragmentTransition.to(MessageFragment.newInstance(), this, true, R.id.fragmet1);

    }
}