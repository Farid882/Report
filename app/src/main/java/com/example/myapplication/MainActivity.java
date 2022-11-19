package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView77;
    private NoteAdapter adapter;
    private FloatingActionButton buttonNewNote;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        adapter=new NoteAdapter();
        recyclerView77.setAdapter(adapter);
        recyclerView77.setLayoutManager(new LinearLayoutManager(this));
        viewModel=new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
            }
        });

        buttonNewNote.setOnClickListener(v -> {
            Intent intent=NoteActivity.newIntent(MainActivity.this);
            startActivity(intent);
        });
    }

    private void initViews()
    {
        recyclerView77=findViewById(R.id.recyclerView77);
        buttonNewNote=findViewById(R.id.buttonNewNote);
    }
    
}