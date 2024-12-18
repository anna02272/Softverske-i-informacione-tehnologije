package com.example.vezbe7.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.vezbe7.MainActivity;
import com.example.vezbe7.R;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends Activity {
    public Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        int SPLASH_TIME_OUT = 3000;

        /*
        * Timer je jedna od komponenti koja nam omogucava da zakazmeo
        * zadatke koji treba da se izvrse u buducnosti, jednom ili periodicno.
        * Medjutim kada aplikacija nije aktivna, on nece izvrsiti svoj posao.
        * Idealna primena ovoga je za Splash screen ili pozdravni ili uvodni
        * ekran za korisnika.
        * posao koji timer treba izvrsi se opsiuje u run() metodi, a kao drugi
        * parametar kazemo koliko Timer treba da ceka da bi se posao izvrsio.
        * */
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish(); // da nebi mogao da ode back na splash
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}