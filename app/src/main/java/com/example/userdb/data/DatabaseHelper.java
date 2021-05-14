package com.example.userdb.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.userdb.model.Note;
import com.example.userdb.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "tag";

    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

         String CREATE_NOTE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Util.NOTE + " TEXT)";
         sqLiteDatabase.execSQL(CREATE_NOTE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_NOTE_TABLE = "DROP TABLE IF EXISTS";
        sqLiteDatabase.execSQL(DROP_NOTE_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(sqLiteDatabase);

    }

    public long insertNote (Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NOTE, note.getNote());
        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }

    public List<Note> fetchAllNotes (){
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setNote_id(cursor.getInt(0));
                note.setNote(cursor.getString(1));

                noteList.add(note);

            } while (cursor.moveToNext());
        }
        return noteList;
    }

    public int updateNote(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NOTE, note.getNote());
        int updateRowID = db.update(Util.TABLE_NAME, contentValues, Util.NOTE_ID + "=?", new String[]{String.valueOf(note.getNote_id())});
        db.close();
        return updateRowID;
    }

    public long removeNote (Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long removedRowId = db.delete(Util.TABLE_NAME, Util.NOTE_ID + "=?", new String[]{String.valueOf(note.getNote_id())});
        db.close();
        return removedRowId;
    }


}
