package com.example.vezbe6.fragments;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import com.example.vezbe6.R;
import com.example.vezbe6.activities.DetailActivity;
import com.example.vezbe6.database.DBContentProvider;
import com.example.vezbe6.database.ReviewerSQLiteHelper;

// TODO 2 LOADER MANAGER
public class MyFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private SimpleCursorAdapter adapter;

	public static MyFragment newInstance() {
		
		MyFragment mpf;
        mpf = new MyFragment();

        return mpf;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.map_layout, vg, false);
	}

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
//        Cinema cinema = (Cinema)l.getAdapter().getItem(position);

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        // TODO 3 PREKO ID-A
        Uri todoUri = Uri.parse(DBContentProvider.CONTENT_URI_CINEMA + "/" + id);
        intent.putExtra("id", todoUri);
        startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "onActivityCreated()", Toast.LENGTH_SHORT).show();
        /*
         * deprecated
         * getLoaderManager().initLoader(0, null, this);
         */
        LoaderManager.getInstance(this).initLoader(0, null, this);

        String[] from = new String[] { ReviewerSQLiteHelper.COLUMN_NAME, ReviewerSQLiteHelper.COLUMN_DESCRIPTION };
        int[] to = new int[] {R.id.name, R.id.description};
        adapter = new SimpleCursorAdapter(getActivity(), R.layout.cinema_list, null, from, to, 0);
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
        if(id == R.id.action_insert){
            Toast.makeText(getActivity(), "Create Text", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] allColumns = { ReviewerSQLiteHelper.COLUMN_ID, ReviewerSQLiteHelper.COLUMN_NAME, ReviewerSQLiteHelper.COLUMN_DESCRIPTION, ReviewerSQLiteHelper.COLUMN_AVATAR };

        return new CursorLoader(getActivity(), DBContentProvider.CONTENT_URI_CINEMA, allColumns, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }


    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}