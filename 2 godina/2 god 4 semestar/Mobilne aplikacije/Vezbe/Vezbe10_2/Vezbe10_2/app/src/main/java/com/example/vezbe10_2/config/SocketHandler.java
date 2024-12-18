package com.example.vezbe10_2.config;

import com.example.vezbe10_2.BuildConfig;

import java.util.Map;
import java.util.Properties;

import io.socket.client.IO;
import io.socket.client.Socket;


public class SocketHandler {
    static Socket socket;

    public static void setSocket(){
        try {
            socket = IO.socket("http://" + BuildConfig.IP_ADDR + ":3000");
        }catch (Exception e){

        }
    }

    public static Socket getSocket(){
        return socket;
    }

}
