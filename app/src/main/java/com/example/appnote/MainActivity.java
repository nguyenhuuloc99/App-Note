package com.example.appnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appnote.Activity.InsertNotes_Activity;
import com.example.appnote.Adapterr.NoteAdapter;
import com.example.appnote.Model.Notes;
import com.example.appnote.ViewModel.NotesViewModel;
import com.example.appnote.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding viewbinding;
    NotesViewModel notesViewModel;
    NoteAdapter adapter;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewbinding=ActivityMainBinding.inflate(getLayoutInflater());
       setContentView(viewbinding.getRoot());
       setSupportActionBar(viewbinding.toolbarMain);
        notesViewModel= new ViewModelProvider(MainActivity.this).get(NotesViewModel.class);
        viewbinding.recycerview.setLayoutManager(new GridLayoutManager(this,2));
        viewbinding.recycerview.setHasFixedSize(true);


        viewbinding.newnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertNotes_Activity.class));
            }
        });
        viewbinding.noFilter.setBackgroundResource(R.drawable.filter_bg);
        viewbinding.noFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData(0);
                viewbinding.hightolow.setBackgroundResource(0);
                viewbinding.lowtohigh.setBackgroundResource(0);
                viewbinding.noFilter.setBackgroundResource(R.drawable.filter_bg);
            }
        });
        viewbinding.hightolow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData(1);
                viewbinding.noFilter.setBackgroundResource(0);
                viewbinding.hightolow.setBackgroundResource(R.drawable.filter_bg);
                viewbinding.lowtohigh.setBackgroundResource(0);
            }
        });
        viewbinding.lowtohigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData(2);
                viewbinding.noFilter.setBackgroundResource(0);
                viewbinding.hightolow.setBackgroundResource(0);
                viewbinding.lowtohigh.setBackgroundResource(R.drawable.filter_bg);
            }
        });
            notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    adapter=new NoteAdapter(notes,MainActivity.this);
                    viewbinding.recycerview.setAdapter(adapter);
                }
            });
        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
            }
        });
    }

    private void loadData(int i) {
            if(i==0)
            {
                notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                    @Override
                    public void onChanged(List<Notes> notes) {
                        setAdapter(notes);
                    }
                });
            }else if(i==1) {
                notesViewModel.hightolow.observe(this, new Observer<List<Notes>>() {
                    @Override
                    public void onChanged(List<Notes> notes) {
                        setAdapter(notes);
                    }
                });
            }else if(i==2)
            {
                notesViewModel.lowtohigh.observe(this, new Observer<List<Notes>>() {
                    @Override
                    public void onChanged(List<Notes> notes) {
                        setAdapter(notes);
                    }
                });
            }
    }

    public void setAdapter(List<Notes> notes)
    {
            adapter=new NoteAdapter(notes,getApplicationContext());
            viewbinding.recycerview.setAdapter(adapter);
    }

}