package com.example.reviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.pm.PackageManager;
import android.content.res.Configuration;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.reviewer.adapters.NavItemAdapter;
import com.example.reviewer.fragments.CinemaFragment;
import com.example.reviewer.fragments.LinearLayoutFragment;
import com.example.reviewer.fragments.NewFragment;
import com.example.reviewer.fragments.NewFragment2;
import com.example.reviewer.fragments.RelativeLayoutFragment;
import com.example.reviewer.models.NavItem;
import com.example.reviewer.tools.FragmentTransition;

import java.util.ArrayList;

/*
 * Aktivnost pravimo tako sto napravimo klasu koja nasledjuje AppCompatActivity klasu.
 * Nasledjivanjem ove klase, dobijamo metode zivotnog ciklusa Aktivnosti.
 * */
public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_CODE = 100;

    private final ArrayList<NavItem> mNavItems = new ArrayList<>();

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mDrawerPane;
    private CharSequence mTitle;

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
        Toast.makeText(this, "MainActivity - onCreate()",Toast.LENGTH_SHORT).show();

        prepareMenu(mNavItems);
        mTitle = getTitle();
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerList = findViewById(R.id.navList);
        mDrawerPane = findViewById(R.id.drawerPane);

        NavItemAdapter adapter = new NavItemAdapter(this, mNavItems);
        // postavljamo senku koja preklama glavni sadrzaj
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // dodajemo listener koji ce reagovati na klik pojedinacnog elementa u listi
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        // drawer-u postavljamo unapred definisan adapter
        mDrawerList.setAdapter(adapter);
        // Specificiramo da kada se drawer zatvori prikazujemo jednu ikonu
        // kada se drawer otvori drugu. Za to je potrebo da ispranvo povezemo
        // Toolbar i ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setIcon(R.drawable.ic_launcher);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setHomeButtonEnabled(true);
        }

        /*
         * drawer-u specificiramo za koju referencu toolbar-a da se veze
         * Specificiramo sta ce da pise unutar Toolbar-a kada se drawer otvori/zatvori
         * i specificiramo sta ce da se desava kada se drawer otvori/zatvori.
         * */
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("iReviewer");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Izborom na neki element iz liste, pokrecemo akciju
        if (savedInstanceState == null) {
            selectItemFromDrawer(0);
        }

    }

    private void prepareMenu(ArrayList<NavItem> mNavItems ){
        mNavItems.add(new NavItem(getString(R.string.home), getString(R.string.home_long), R.drawable.ic_action_map));
        mNavItems.add(new NavItem(getString(R.string.places), getString(R.string.places_long), R.drawable.ic_action_place));
        mNavItems.add(new NavItem(getString(R.string.preferences), getString(R.string.preferences_long), R.drawable.ic_action_settings));
        mNavItems.add(new NavItem(getString(R.string.about), getString(R.string.about_long), R.drawable.ic_action_about));
        mNavItems.add(new NavItem(getString(R.string.sync_data), getString(R.string.sync_data_long), R.drawable.ic_action_refresh));
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItemFromDrawer(position);
            Log.i("Pisi", String.valueOf(position));

        }
    }
    private void selectItemFromDrawer(int position) {
        if(position == 0){
            Log.i("DRAWER", "Position 0");
            FragmentTransition.to(CinemaFragment.newInstance(), this, false, R.id.mainContent);
        }else if(position == 1){
            Log.i("DRAWER", "Position 1");
            FragmentTransition.to(NewFragment.newInstance(), this, false, R.id.mainContent);

        }else if(position == 2){
            Log.i("DRAWER", "Position 2");
            FragmentTransition.to(NewFragment2.newInstance(), this, false, R.id.mainContent);
        }else if(position == 3){
            Log.i("DRAWER", "Position 3");
            //..
        }else if(position == 4){
            Log.i("DRAWER", "Position 4");
            //..
        }else{
            Log.e("DRAWER", "Nesto van opsega!");
        }

        mDrawerList.setItemChecked(position, true);
        if(position != 4) // za sve osim za sync
        {
            setTitle(mNavItems.get(position).getmTitle());
        }
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
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