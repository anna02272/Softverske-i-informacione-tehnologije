package com.example.reviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.reviewer.activities.SecondActivity;
import com.google.android.material.snackbar.Snackbar;

/*
 * Aktivnost pravimo tako sto napravimo klasu koja nasledjuje AppCompatActivity klasu.
 * Nasledjivanjem ove klase, dobijamo metode zivotnog ciklusa Aktivnosti.
 * */
public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_CODE = 100;

    /*

    /*
     * Unutar onCreate metode, postavljamo izgled nase aktivnosti koristeci setContentView
     * U ovoj metodi mozemo dobaviti sve view-e (widget-e, komponente interface-a).
     * Moramo voditi racuna, ovde se ne sme nalaziti kod koji ce blokirati prelazak aktivnosti
     * u naredne metode! To znaci da izvrsavanje dugackih operacija treba izbegavati ovde.
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("MainActivity", "MainActivity.onCreate()");
        /*
         * Toast klasa se koristi za prikazivanje obavestenja za odredjeni vremenski interval. Posle nekog vremena nestaje.
         * Ne blokira interakciju korisnika
         * */
        Toast.makeText(this, "onCreate()",Toast.LENGTH_SHORT).show();

        /*
         * Koristeci metodu findViewById, mozemo dobaviti tacnu komponentu interface-a preko
         * njenog jedinstvenog id-a (vise o tome kasnije). Na komponente mozemo dodavati razne
         * akcije listenere na slican nacin kako se to radi u drugi programskim jezicima.
         * */
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                startActivity(intent);
            }
        });

        Button open_google = findViewById(R.id.button2);
        open_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * Za korišćenje određenih podataka ili pokretanje određenih akcija,
                 * potrebno je dobiti dozvolu od strane korisnika.
                 * Proveravamo da li je pravo pristupa omogućeno i ukoliko jeste, kreiramo implicitnu nameru.
                 * Ukoliko nije omogućeno, od korisnika tražimo dozvolu prikazom prompta.
                 * */
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    Snackbar.make(v, "CAMERA NOT PERMITTED", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.CAMERA},
                            CAMERA_PERMISSION_CODE);

                } else {

                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                    startActivity(i);
                }
            }
        });
    }

    /*
     * onStart se poziva kada se aktivnost pokrece, nakon nje poziva se onResume
     * i aktivnost je vidljiva..
     * */
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
    }
    /*
     * onResume se poziva kada je aktivnost u fokusu i korisnik je u interakciji sa
     * aktivnosti.
     * */
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume()",Toast.LENGTH_SHORT).show();
    }

    /*
     * onPause se poziva kada je aktivnost delimicno prekrivena.
     * */
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause()",Toast.LENGTH_SHORT).show();
    }

    /*
     * onPause se poziva kada je aktivnost u potpunosti prekrivena.
     * */
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop()",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Pristup kameri je dozvoljen!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Pristup kameri nije dozvoljen!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}