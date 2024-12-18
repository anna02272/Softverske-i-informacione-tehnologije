package com.example.reviewer.fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import com.example.reviewer.R;
import com.example.reviewer.activities.SecondActivity;
import com.google.android.material.snackbar.Snackbar;

public class NewFragment extends Fragment {
    private static final int CAMERA_PERMISSION_CODE = 100;

    public static NewFragment newInstance() {
        return new NewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment_layout, container, false);
        /*
         * Koristeci metodu findViewById, mozemo dobaviti tacnu komponentu interface-a preko
         * njenog jedinstvenog id-a (vise o tome kasnije). Na komponente mozemo dodavati razne
         * akcije listenere na slican nacin kako se to radi u drugi programskim jezicima.
         * */
        Button button = view.findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), SecondActivity.class);

                startActivity(intent);
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button open_google = view.findViewById(R.id.button_2);
        open_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * Za korišćenje određenih podataka ili pokretanje određenih akcija,
                 * potrebno je dobiti dozvolu od strane korisnika.
                 * Proveravamo da li je pravo pristupa omogućeno i ukoliko jeste, kreiramo implicitnu nameru.
                 * Ukoliko nije omogućeno, od korisnika tražimo dozvolu prikazom prompta.
                 * */
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    Snackbar.make(v, "CAMERA NOT PERMITTED", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.CAMERA},
                            CAMERA_PERMISSION_CODE);

                } else {

                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                    startActivity(i);
                }
            }
        });
        return view;
    }


}
