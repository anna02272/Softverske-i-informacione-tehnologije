package com.example.vezbe7.fragments;

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
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.vezbe7.R;
import com.example.vezbe7.activities.DetailActivity;
import com.example.vezbe7.adapters.CinemaAdapter;
import com.example.vezbe7.model.Cinema;
import com.example.vezbe7.tools.Mokap;

public class MyFragment extends ListFragment {


	public static MyFragment newInstance() {
		
		MyFragment mpf;
        mpf = new MyFragment();

        return mpf;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
		View view;
        view = inflater.inflate(R.layout.map_layout, vg, false);

        return view;
	}

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Cinema cinema = Mokap.getCinemas().get(position);

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("name", cinema.getName());
        intent.putExtra("descr", cinema.getDescription());
        startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "onCreated()", Toast.LENGTH_SHORT).show();

        //Dodaje se adapter
        CinemaAdapter adapter = new CinemaAdapter(getActivity());
        setListAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.activity_itemdetail, menu);
    }

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