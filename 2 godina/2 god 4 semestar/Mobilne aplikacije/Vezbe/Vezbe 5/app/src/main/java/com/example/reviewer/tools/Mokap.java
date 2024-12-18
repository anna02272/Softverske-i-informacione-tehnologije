package com.example.reviewer.tools;

import com.example.reviewer.R;
import com.example.reviewer.models.Cinema;

import java.util.ArrayList;
import java.util.List;

public class Mokap {

    public static List<Cinema> getCinemas(){
        ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
        Cinema u1 = new Cinema("Arena", "Cineplexx 3D", R.drawable.ic_action_settings);
        Cinema u2 = new Cinema("Cinestar", "Najnoviji 5D", R.drawable.ic_action_settings);
        Cinema u3 = new Cinema("Jadran", "Tradicionalni u mirnom ambijentu", R.drawable.ic_action_settings);

        cinemas.add(u1);
        cinemas.add(u2);
        cinemas.add(u3);

        return cinemas;
    }

}
