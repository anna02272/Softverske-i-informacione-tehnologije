package com.example.reviewer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.example.reviewer.R;
import com.example.reviewer.activities.CinemaDetailActivity;
import com.example.reviewer.adapters.CinemaAdapter;
import com.example.reviewer.models.Cinema;
import com.example.reviewer.tools.Mokap;

public class CinemaFragment extends ListFragment {

    public static CinemaFragment newInstance() {
        return new CinemaFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.map_layout, vg, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Dodaje se adapter
        CinemaAdapter adapter = new CinemaAdapter(getActivity());
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Cinema cinema = Mokap.getCinemas().get(position);

        /*
         * Ako nasoj aktivnosti zelimo da posaljemo nekakve podatke
         * za to ne treba da koristimo konstruktor. Treba da iskoristimo
         * identicnu strategiju koju smo koristili kda smo radili sa
         * fragmentima! Koristicemo Bundle za slanje podataka. Tacnije
         * intent ce formirati Bundle za nas, ali mi treba da pozovemo
         * odgovarajucu putExtra metodu.
         * */
        Intent intent = new Intent(getActivity(), CinemaDetailActivity.class);
        intent.putExtra("name", cinema.getName());
        intent.putExtra("descr", cinema.getDescription());
        startActivity(intent);
    }

    /*
     * Ako zelimo da nasa aktivnost/fragment prikaze ikonice unutar toolbar-a
     * to se odvija u nekoliko koraka. potrebno je da napravimo listu koja
     * specificira koje sve ikonice imamo unutar menija, koje ikone koristimo
     * i da li se uvek prikazuju ili ne. Nakon toga koristeci metdu onCreateOptionsMenu
     * postavljamo ikone na toolbar.
     * */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // ovo korostimo ako je nasa arhitekrura takva da imamo jednu aktivnost
        // i vise fragmentaa gde svaki od njih ima svoj menu unutar toolbar-a
        menu.clear();
        inflater.inflate(R.menu.activity_itemdetail, menu);
    }

    /*
     * Da bi znali na koju ikonicu je korisnik kliknuo
     * treba da iskoristimo jedinstveni identifikator
     * za svaki element u listi. Metoda onOptionsItemSelected
     * ce vratiti MenuItem na koji je korisnik kliknuo. Ako znamo
     * da ce on vratiti i id, tacno znamo na koji element je korisnik
     * kliknuo, i shodno tome reagujemo.
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_refresh){
            Toast.makeText(getActivity(), "Refresh App", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.action_new){
            Toast.makeText(getActivity(), "Create Text", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
