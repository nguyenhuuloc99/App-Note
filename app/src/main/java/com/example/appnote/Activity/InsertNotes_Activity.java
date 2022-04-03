package com.example.appnote.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.appnote.Model.Notes;
import com.example.appnote.R;
import com.example.appnote.ViewModel.NotesViewModel;
import com.example.appnote.databinding.ActivityInsertNotesBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InsertNotes_Activity extends AppCompatActivity {
    ActivityInsertNotesBinding binding;
    NotesViewModel notesViewModel;
    String title,subtitle,notes;
    String priority="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_notes);
        binding=ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesViewModel=new ViewModelProvider(this).get(NotesViewModel.class);
        binding.imgGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            priority="1";
            binding.imgGreen.setImageResource(R.drawable.ic_baseline_done_24);
            binding.imgYellow.setImageResource(0);
            binding.imgRed.setImageResource(0);
            }
        });
        binding.imgRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority="3";
                binding.imgGreen.setImageResource(0);
                binding.imgYellow.setImageResource(0);
                binding.imgRed.setImageResource(R.drawable.ic_baseline_done_24);
            }
        });
        binding.imgYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority="2";
                binding.imgGreen.setImageResource(0);
                binding.imgYellow.setImageResource(R.drawable.ic_baseline_done_24);
                binding.imgRed.setImageResource(0);
            }
        });
        binding.dontNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=binding.edtTitle.getText().toString();
                subtitle=binding.edtSubtitle.getText().toString();
                notes=binding.notesData.getText().toString();
                insert(title,subtitle,notes);
            }
        });

    }

    private void insert(String title, String subtitle, String notes) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=Calendar.getInstance().getTime();
        String currentTime=simpleDateFormat.format(date);
        Notes notes1=new Notes();
        notes1.notesTitle=title;
        notes1.notesSubTitle=subtitle;
        notes1.notes=notes;
        notes1.notesDate=currentTime;
        notes1.notesPriority=priority;
        notesViewModel.insertNotes(notes1);
        finish();
    }
}