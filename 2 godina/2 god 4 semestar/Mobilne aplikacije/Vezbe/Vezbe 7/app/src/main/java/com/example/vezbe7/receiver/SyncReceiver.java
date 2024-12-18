package com.example.vezbe7.receiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.vezbe7.R;
import com.example.vezbe7.MainActivity;
import com.example.vezbe7.service.SyncService;
import com.example.vezbe7.tools.ReviewerTools;


/*
 * BroadcastReceiver je komonenta koja moze da reaguje na poruke drugih delova
 * samog sistema kao i korisnicki definisanih. Cesto se koristi u sprezi sa
 * servisima i asinhronim zadacima.
 *
 * Pored toga on moze da reaguje i na neke sistemske dogadjaje prispece sms poruke
 * paljenje uredjaja, novi poziv isl.
 */
public class SyncReceiver extends BroadcastReceiver {

    private static int NOTIFICATION_ID = 1;
    private static String CHANNEL_ID = "Zero channel";

    /*
     * Intent je bitan parametar za BroadcastReceiver. Kada posaljemo neku poruku,
     * ovaj Intent cuva akciju i podatke koje smo mu poslali.
     * */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("REZ", "onReceive");
        // STARIJE VERZIJE ANDROIDA
//        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        // notificationId is a unique int for each notification that you must define

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID);

        /*
         * Posto nas BroadcastReceiver reaguje samo na jednu akciju koju smo definisali
         * dobro je da proverimo da li som dobili bas tu akciju. Ako jesmo onda mozemo
         * preuzeti i sadrzaj ako ga ima.
         *
         * Voditi racuna o tome da se naziv akcije kada korisnik salje Intent mora poklapati sa
         * nazivom akcije kada akciju proveravamo unutar BroadcastReceiver-a. Isto vazi i za podatke.
         * Dobra praksa je da se ovi nazivi izdvoje unutar neke staticke promenljive.
         * */
        if(intent.getAction().equals(MainActivity.SYNC_DATA)){
            int resultCode = intent.getExtras().getInt(SyncService.RESULT_CODE);
            Bitmap bm;

            Intent wiFiintent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, wiFiintent, 0);

            if(resultCode == ReviewerTools.TYPE_NOT_CONNECTED){
                bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_action_network_wifi);
                mBuilder.setSmallIcon(R.drawable.ic_action_error);
                mBuilder.setContentTitle(context.getString(R.string.autosync_problem));
                mBuilder.setContentText(context.getString(R.string.no_internet));
                mBuilder.setContentIntent(pIntent);
                mBuilder.addAction(R.drawable.ic_action_network_wifi, context.getString(R.string.turn_wifi_on), pIntent);
                mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

            }else if(resultCode == ReviewerTools.TYPE_MOBILE){
                bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_action_network_cell);
                mBuilder.setSmallIcon(R.drawable.ic_action_warning);
                mBuilder.setContentTitle(context.getString(R.string.autosync_warning));
                mBuilder.setContentText(context.getString(R.string.connect_to_wifi));
                mBuilder.addAction(R.drawable.ic_action_network_wifi, context.getString(R.string.turn_wifi_on), pIntent);
            }else{
                bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
                mBuilder.setSmallIcon(R.drawable.ic_action_refresh_w);
                mBuilder.setContentTitle(context.getString(R.string.autosync));
                mBuilder.setContentText(context.getString(R.string.good_news_sync));
            }


            mBuilder.setLargeIcon(bm);
            // notificationID allows you to update the notification later on.
            notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        }
    }

}
