package com.example.userdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.userdb.data.DatabaseHelper;
import com.example.userdb.model.Note;

import java.util.ArrayList;
import java.util.List;

public class ShowNotes extends AppCompatActivity {
    ListView notesListView;

    public static ArrayList<String> notesArrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        notesListView = findViewById(R.id.notesListView);
        notesArrayList = new ArrayList<>();
        DatabaseHelper db = new DatabaseHelper(ShowNotes.this);

        List<Note> noteList = db.fetchAllNotes();
        for (Note note :noteList)
        {
            notesArrayList.add(note.getNote());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesArrayList);
        notesListView.setAdapter(adapter);

        notesListView.setOnItemClickListener((parent, view, position, id) -> {
            Integer selectedItem = position;

            Intent intent = new Intent(ShowNotes.this, EditNote.class);
            intent.putExtra("elementPos", selectedItem);
            startActivity(intent);
        });
    }
}