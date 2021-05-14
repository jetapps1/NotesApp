package com.example.userdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.userdb.data.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newNote = findViewById(R.id.btnNewNote);
        Button showNotes = findViewById(R.id.btnShowAll);

        db = new DatabaseHelper(this);

        // New Note
        newNote.setOnClickListener(view -> {
            Intent showIntent = new Intent(MainActivity.this, NewNote.class);
            startActivity(showIntent);
        });

        // Show All Notes
        showNotes.setOnClickListener(view -> {
            Intent showIntent = new Intent(MainActivity.this, ShowNotes.class);
            startActivity(showIntent);
        });
    }
}