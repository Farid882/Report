package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class NoteActivity extends AppCompatActivity {
    private EditText editText;
    private RadioButton radioButton1, radioButton2;
    private Button button77;
    private NoteDatabase database;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initViews();
        database=NoteDatabase.getInstance(getApplication());
        button77.setOnClickListener(v -> {
            save();
        });
    }

    private void initViews() {
        editText = findViewById(R.id.editTextTextPersonName);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        button77 = findViewById(R.id.button77);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, NoteActivity.class);
    }

    private void save() {
        String text = editText.getText().toString();
        int priority = getPriority();
        Note note=new Note(text,priority);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                database.noteDao().add(note);
            }
        });
        thread.start();

        finish();
    }

    private int getPriority() {
        int priority;
        if (radioButton1.isChecked()) {
            priority = 0;
        } else if (radioButton2.isChecked()) {
            priority = 1;
        } else {
            priority = 2;
        }
        return priority;
    }

}