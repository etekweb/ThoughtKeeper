package net.etekweb.thoughtkeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "notes";
    private static final String NOTE_COL = "note";
    private static final String DATE_COL = "date";
    private static final Date DATE = new Date();

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOTE_COL + " LONGTEXT, "
                + DATE_COL + " DATE)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropIfTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropIfTable);
        onCreate(db);
    }

    public boolean addData(String itemToAdd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTE_COL, itemToAdd);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String dateStr = simpleDate.format(DATE);
        contentValues.put(DATE_COL, dateStr);

        Log.d(TAG, "addData: Adding " + itemToAdd + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);
        Log.d(TAG, "result: " + result);

        return (!(result == -1));
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }
}
