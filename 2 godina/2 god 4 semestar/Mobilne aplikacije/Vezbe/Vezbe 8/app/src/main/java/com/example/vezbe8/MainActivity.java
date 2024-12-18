package com.example.vezbe8;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.vezbe8.fragments.PictureCaptureFragment;
import com.example.vezbe8.model.NavItem;
import com.example.vezbe8.activities.ReviewerPreferenceActivity;
import com.example.vezbe8.adapters.DrawerListAdapter;
import com.example.vezbe8.receivers.SyncReceiver;
import com.example.vezbe8.services.SyncService;
import com.example.vezbe8.tools.FragmentTransition;
import com.example.vezbe8.tools.ReviewerTools;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mDrawerPane;
    private CharSequence mTitle;
    private ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private AlertDialog dialog;

    //Sync stuff
    private PendingIntent pendingIntent;
    private AlarmManager manager;

    private SyncReceiver sync;
    public static String SYNC_DATA = "SYNC_DATA";
    private String synctime;
    private boolean allowSync;
    private SharedPreferences sharedPreferences;
    private static String CHANNEL_ID = "Zero channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        prepareMenu(mNavItems);
        
        mTitle  = getTitle();
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerList = findViewById(R.id.navList);

        View mProfileBox = findViewById(R.id.profileBox);
        mProfileBox.setClickable(true);
        // Populate the Navigtion Drawer with options
        mDrawerPane = findViewById(R.id.drawerPane);

        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        Log.i("REZ", "SET ADAPTER");
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerList.setAdapter(adapter);

        // enable ActionBar app icon to behave as action to toggle nav drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setIcon(R.drawable.ic_launcher);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setHomeButtonEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
//                getActionBar().setTitle(mTitle);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
//                getActionBar().setTitle(mDrawerTitle);
                getSupportActionBar().setTitle("iReviewer");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        if (savedInstanceState == null) {
            selectItemFromDrawer(0);
        }

        setUpReceiver();
        consultPreferences();
    }

    private void setUpReceiver(){
        sync = new SyncReceiver();

        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(this, SyncService.class);
        pendingIntent = PendingIntent.getService(this, 0, alarmIntent, 0);
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    }

    private void consultPreferences(){

        /* PRE
        * getDefaultSharedPreferences():
        * koristi podrazumevano ime preference-file-a.
        * Podrzazumevani fajl je setovan na nivou aplikacije tako da
        * sve aktivnosti u istom context-u mogu da mu pristupe jednostavnije
        * getSharedPreferences(name,mode):
        * trazi da se specificira ime preference file-a requires
        * i mod u kome se radi (e.g. private, world_readable, etc.)
        * sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        * */
        /*
        * getSharedPreferences(name, mode);
        * trazi da se specificira ime preference file-a requires
        * i mod u kome se radi (e.g. private, world_readable, etc.)
        * */
        sharedPreferences = getSharedPreferences("com.example.vezbe8_preferences", Context.MODE_PRIVATE);
        /*
        * Proverava da li postoji kljuc pref_sync_list unutar sharedPreferences fajla
        * */
        if(sharedPreferences.contains("pref_sync_list")){
            Log.i("REZ_SP", "Postoji pref_sync_list kljuc");
            /*
             * Koristeci parove kljuc-vrednost iz shared preferences mozemo da dobijemo
             * odnosno da zapisemo nekakve vrednosti. Te vrednosti mogu da budu iskljucivo
             * prosti tipovi u Javi.
             * Kao prvi parametar prosledjujemo kljuc, a kao drugi podrazumevanu vrednost,
             * ako nesto pod tim kljucem se ne nalazi u storage-u, da dobijemo podrazumevanu
             * vrednost nazad, i to nam je signal da nista nije sacuvano pod tim kljucem.
             * */
            synctime = sharedPreferences.getString("pref_sync_list", "1"); // pola minuta
            Log.i("REZ_SP", "SYNC TIME = " + synctime.toString());

        }

        if(sharedPreferences.contains("pref_sync")){
            Log.i("REZ_SP", "Postoji pref_sync kljuc");
            allowSync = sharedPreferences.getBoolean("pref_sync", false);
            Log.i("REZ_SP", "ALLOW SYNC = " + allowSync);
        }

        if(sharedPreferences.contains("pref_name")){
            Log.i("REZ_SP", "Postoji pref_name kljuc");
            String name = sharedPreferences.getString("pref_name", "default");
            Log.i("REZ_SP", "PREF NAME = " + name);
        }else{
            Log.i("REZ_SP", "Ne postoji pref_name kljuc");
        }

    }

    private void setPreferences(){
        sharedPreferences = getSharedPreferences("com.example.vezbe8_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editor = sharedPreferences.edit();
        Log.i("REZ_SP", "Set pref_name kljuc");
        if(!sharedPreferences.contains("pref_name")){
            sp_editor.putString("pref_name", "iReviewer");
            sp_editor.commit();
        }

    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();

        //Za slucaj da referenca nije postavljena da se izbegne problem sa androidom!
        if (manager == null) {
            setUpReceiver();
        }

        if(allowSync){
            int interval = ReviewerTools.calculateTimeTillNextSync(Integer.parseInt(synctime));
            manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
            Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(SYNC_DATA);

        registerReceiver(sync, filter);
    }
    
    private void prepareMenu(ArrayList<NavItem> mNavItems ){
    	mNavItems.add(new NavItem(getString(R.string.home), getString(R.string.home_long), R.drawable.ic_action_map));
        mNavItems.add(new NavItem(getString(R.string.places), getString(R.string.places_long), R.drawable.ic_action_place));
        mNavItems.add(new NavItem(getString(R.string.preferences), getString(R.string.preferences_long), R.drawable.ic_action_settings));
        mNavItems.add(new NavItem(getString(R.string.about), getString(R.string.about_long), R.drawable.ic_action_about));
        mNavItems.add(new NavItem(getString(R.string.sync_data), getString(R.string.sync_data_long), R.drawable.ic_action_refresh));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.activity_itemdetail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(this, ReviewerPreferenceActivity.class);
                startActivity(i);
                return true;
            case R.id.action_new:
                finish();
                startActivity(getIntent());
        }

        return super.onOptionsItemSelected(item);
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	selectItemFromDrawer(position);
        }
    }
    
    
    private void selectItemFromDrawer(int position) {
        if(position == 0){
            FragmentTransition.to(PictureCaptureFragment.newInstance(), this, false);
        }else if(position == 1){
           //..
        }else if(position == 2){
            Intent preference = new Intent(MainActivity.this,ReviewerPreferenceActivity.class);
            startActivity(preference);
        }else if(position == 3){
            //..
        }else if(position == 4){
            //..
        }else if(position == 5){
            //...
        }else{
            Log.e("DRAWER", "Nesto van opsega!");
        }

        mDrawerList.setItemChecked(position, true);
        if(position != 5) // za sve osim za sync
        {
            setTitle(mNavItems.get(position).getmTitle());
        }
        mDrawerLayout.closeDrawer(mDrawerPane);
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

    @Override
    protected void onPause() {
        if (manager != null) {
            manager.cancel(pendingIntent);
        }

        //osloboditi resurse
        if(sync != null){
            unregisterReceiver(sync);
        }
        setPreferences();

        super.onPause();

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification channel";
            String description = "Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                findViewById(R.id.button_image).setEnabled(true);
            }
        }
    }
}
