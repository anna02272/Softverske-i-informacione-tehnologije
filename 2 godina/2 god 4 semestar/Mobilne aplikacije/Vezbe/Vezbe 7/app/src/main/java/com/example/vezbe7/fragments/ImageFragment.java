package com.example.vezbe7.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vezbe7.R;
import com.example.vezbe7.tools.NetworkUtil;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class ImageFragment extends Fragment {


    String mUrl = "https://www.pametnitelefoni.rs/images/main/2--33.jpeg";
    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance() {
        ImageFragment fragment = new ImageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        ImageView imageView = view.findViewById(R.id.image);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Log.i("REZ", "Background work here");
                Bitmap bitmap = NetworkUtil.fetchImage(mUrl);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(bitmap != null){
//                            imageView.setImageBitmap(bitmap);
//                        }
//                    }
//                });
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(bitmap != null){

                            Log.i("REZ", "UI Thread work here");
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                });

            }
        });
        return view;
    }
}