package com.example.userdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.userdb.data.DatabaseHelper;
import com.example.userdb.model.Note;

import java.io.Serializable;
import java.util.List;

public class EditNote extends AppCompatActivity {
    EditText EditNote;
    Button btnUpdate,btnDelete;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        EditNote = findViewById(R.id.editNote);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        db = new DatabaseHelper(this);

        int itemPos = getIntent().getIntExtra("elementPos", 0);
        List<Note> noteList = db.fetchAllNotes();

        Note note = noteList.get(itemPos);

        EditNote.setText(note.getNote());

        btnUpdate.setOnClickListener(view -> { // btn update
            note.setNote(EditNote.getText().toString());
            db.updateNote(note);
            Intent intent = new Intent(EditNote.this, ShowNotes.class);
            startActivity(intent);
            });

        btnDelete.setOnClickListener(view -> { // btn Delete
            db.removeNote(note);
            Intent intent = new Intent(EditNote.this, ShowNotes.class);
            startActivity(intent);
        });
    }
}