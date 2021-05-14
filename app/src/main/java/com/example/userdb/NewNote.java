package com.example.userdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.userdb.data.DatabaseHelper;
import com.example.userdb.model.Note;

public class NewNote extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        EditText newNote = findViewById(R.id.txtNewNote);
        Button saveButton = findViewById(R.id.saveButton);
        db = new DatabaseHelper(this);

        saveButton.setOnClickListener(view -> {

            String note = newNote.getText().toString();

            long result = db.insertNote(new Note(note));
            if (result > 0)
            {
                Toast.makeText(NewNote.this, "Note Saved!", Toast.LENGTH_SHORT).show();
            }
                else {
                    Toast.makeText(NewNote.this, "Note Saved Failed!", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(NewNote.this, ShowNotes.class);
                startActivity(intent);
            });



    }
}