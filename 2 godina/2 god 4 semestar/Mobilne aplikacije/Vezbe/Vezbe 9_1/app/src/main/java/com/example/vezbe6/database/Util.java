package com.example.vezbe6.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import com.example.vezbe6.R;
import com.example.vezbe6.database.DBContentProvider;
import com.example.vezbe6.database.ReviewerSQLiteHelper;
import com.example.vezbe6.model.Cinema;

public class Util {
    public static void initDB(Activity activity) {
        ReviewerSQLiteHelper dbHelper = new ReviewerSQLiteHelper(activity);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.i("REZ_DB", "ENTRY INSERT TO DATABASE");
        {

            ContentValues entry = new ContentValues();
            entry.put(ReviewerSQLiteHelper.COLUMN_NAME, "Arena");
            entry.put(ReviewerSQLiteHelper.COLUMN_DESCRIPTION, "Cineplexx 3D");
            entry.put(ReviewerSQLiteHelper.COLUMN_AVATAR, -1);
            long id = db.insert(ReviewerSQLiteHelper.TABLE_CINEMA, null, entry);
            Log.i("REZ_DB", "INSERT " + id + " TO DATABASE");
//            insert preko content provider-a
//            activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_CINEMA, entry);

            entry = new ContentValues();
            entry.put(ReviewerSQLiteHelper.COLUMN_NAME, "Cinestar");
            entry.put(ReviewerSQLiteHelper.COLUMN_DESCRIPTION, "Najnoviji 5D");
            entry.put(ReviewerSQLiteHelper.COLUMN_AVATAR, -1);
            id = db.insert(ReviewerSQLiteHelper.TABLE_CINEMA, null, entry);
            Log.i("REZ_DB", "INSERT " + id + " TO DATABASE");
//            insert preko content provider-a
//            activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_CINEMA, entry);

        }

        db.close();
    }
//   DODAVANJE U BAZU
    public static void insert(Activity activity){
        ReviewerSQLiteHelper dbHelper = new ReviewerSQLiteHelper(activity);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.i("REZ_DB", "ENTRY INSERT TO DATABASE");

        ContentValues entry = new ContentValues();
        entry.put(ReviewerSQLiteHelper.COLUMN_NAME, "5D bioskop");
        entry.put(ReviewerSQLiteHelper.COLUMN_DESCRIPTION, "Najbolji bioskop!");
        entry.put(ReviewerSQLiteHelper.COLUMN_AVATAR, -1);
        long id = db.insert(ReviewerSQLiteHelper.TABLE_CINEMA, null, entry);
        Log.i("REZ_DB", "INSERT " + id + " TO DATABASE");
//      insert preko content provider-a
//      activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_CINEMA, entry);

        entry = new ContentValues();
        entry.put(ReviewerSQLiteHelper.COLUMN_NAME, "Cinestar");
        entry.put(ReviewerSQLiteHelper.COLUMN_DESCRIPTION, "Najnoviji 5D");
        entry.put(ReviewerSQLiteHelper.COLUMN_AVATAR, -1);
        id = db.insert(ReviewerSQLiteHelper.TABLE_CINEMA, null, entry);
        Log.i("REZ_DB", "INSERT " + id + " TO DATABASE");
//            insert preko content provider-a
//            activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_CINEMA, entry);

    }

//    IZMENA
    public static void update(Activity activity){
        ReviewerSQLiteHelper dbHelper = new ReviewerSQLiteHelper(activity);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.i("REZ_DB", "ENTRY UPDATE TO DATABASE");
        String tableName = ReviewerSQLiteHelper.TABLE_CINEMA;
        String selection = "name = ?";
        String[] selectionArgs = {"Cinestar"};
        ContentValues entry = new ContentValues();
        entry = new ContentValues();
        entry.put(ReviewerSQLiteHelper.COLUMN_NAME, "Cinestar X");
        entry.put(ReviewerSQLiteHelper.COLUMN_DESCRIPTION, "Najnoviji 5D bioskop");
        entry.put(ReviewerSQLiteHelper.COLUMN_AVATAR, -1);
        int updatedRows = db.update(tableName, entry, selection, selectionArgs);
//        activity.getContentResolver().update(DBContentProvider.CONTENT_URI_CINEMA, entry, selection, selectionArgs);
        if (updatedRows > 0) {
            Log.d("REZ_DB", "UPDATED " + updatedRows + " ROWS FROM " + tableName);
        } else {
            Log.d("REZ_DB", "No rows updated from " + tableName);
        }
    }

//    BRISANJE
    public static void delete(Activity activity){
        ReviewerSQLiteHelper dbHelper = new ReviewerSQLiteHelper(activity);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.i("REZ_DB", "DELETE DATABASE");
        String tableName = ReviewerSQLiteHelper.TABLE_CINEMA;
        String selection = "_id >= ?";
        String[] selectionArgs = {"1"};

        int deletedRows = db.delete(tableName, selection, selectionArgs);
//        activity.getContentResolver().delete(DBContentProvider.CONTENT_URI_CINEMA, selection, selectionArgs);
        if (deletedRows > 0) {
            Log.d("REZ_DB", "DELETED " + deletedRows + " ROWS FROM " + tableName);
        } else {
            Log.d("REZ_DB", "No rows deleted from " + tableName);
        }
    }

    public static Cinema query(Uri todoUri, Activity activity) {
        Log.i("REZ_DB", "EXECUTE QUERY");
        String[] allColumns = { ReviewerSQLiteHelper.COLUMN_ID,
                ReviewerSQLiteHelper.COLUMN_NAME, ReviewerSQLiteHelper.COLUMN_DESCRIPTION, ReviewerSQLiteHelper.COLUMN_AVATAR };

        Cursor cursor = activity.getContentResolver().query(todoUri, allColumns, null, null,
                null);

        cursor.moveToFirst();
        Cinema cinema = createCinema(cursor);
        cursor.close();
        return cinema;
    }

    public static Cinema createCinema(Cursor cursor) {
        Log.i("REZ_DB", "CREATE CINEMA FROM CURSOR");
        Cinema cinema = new Cinema();
        cinema.setId(cursor.getLong(0));
        cinema.setName(cursor.getString(1));
        cinema.setDescription(cursor.getString(2));
        cinema.setAvatar(cursor.getInt(3));
        return cinema;
    }
}
