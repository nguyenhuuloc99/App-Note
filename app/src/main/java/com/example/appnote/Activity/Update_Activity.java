package com.example.appnote.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.appnote.Model.Notes;
import com.example.appnote.R;
import com.example.appnote.ViewModel.NotesViewModel;
import com.example.appnote.databinding.ActivityUpdateBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Update_Activity extends AppCompatActivity {
    Bundle bundle;
    ActivityUpdateBinding binding;
    String priority="1";
     String title, subtitle, notes_data;
     NotesViewModel notesViewModel;

     int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bundle=getIntent().getExtras();
        notesViewModel= new ViewModelProvider(this).get(NotesViewModel.class);
        Notes notes= (Notes) bundle.get("note");
        id=notes.id;
        binding.edtTitleUpdate.setText(notes.notesTitle);
        binding.edtSubtitleUpdate.setText(notes.notesSubTitle);
        binding.notesDataUpdate.setText(notes.notes);
        switch (notes.notesPriority)
        {
            case "1":
                binding.imgGreenUpdate.setImageResource(R.drawable.ic_baseline_done_24);
                binding.imgYellowUpdate.setImageResource(0);
                binding.imgRedUpdate.setImageResource(0);
                break;
            case "2":
                binding.imgGreenUpdate.setImageResource(0);
                binding.imgYellowUpdate.setImageResource(R.drawable.ic_baseline_done_24);
                binding.imgRedUpdate.setImageResource(0);
                break;
            case "3":
                binding.imgGreenUpdate.setImageResource(0);
                binding.imgYellowUpdate.setImageResource(0);
                binding.imgRedUpdate.setImageResource(R.drawable.ic_baseline_done_24);
                break;
        }
        binding.imgGreenUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority="1";
                binding.imgGreenUpdate.setImageResource(R.drawable.ic_baseline_done_24);
                binding.imgYellowUpdate.setImageResource(0);
                binding.imgRedUpdate.setImageResource(0);
            }
        });
        binding.imgRedUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority="3";
                binding.imgGreenUpdate.setImageResource(0);
                binding.imgYellowUpdate.setImageResource(0);
                binding.imgRedUpdate.setImageResource(R.drawable.ic_baseline_done_24);
            }
        });
        binding.imgYellowUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority="2";
                binding.imgGreenUpdate.setImageResource(0);
                binding.imgYellowUpdate.setImageResource(R.drawable.ic_baseline_done_24);
                binding.imgRedUpdate.setImageResource(0);
            }
        });
        binding.dontNoteUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 title=binding.edtTitleUpdate.getText().toString();
                 subtitle= binding.edtSubtitleUpdate.getText().toString();
                notes_data=binding.notesDataUpdate.getText().toString();
                update(title,subtitle,notes_data);
            }
        });
    }

    private void update(String title, String subtitle, String notes_data) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date= Calendar.getInstance().getTime();
        String currentTime=simpleDateFormat.format(date);
        Notes update_notes=new Notes();
        update_notes.id=id;
        update_notes.notesTitle=title;
        update_notes.notesSubTitle=subtitle;
        update_notes.notes=notes_data;
        update_notes.notesDate=currentTime;
        update_notes.notesPriority=priority;
        notesViewModel.updateNotes(update_notes);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_delete:
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(getApplicationContext());
                View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.delete_bt_sheet,(LinearLayout)findViewById(R.id.bottom_sheet));
                bottomSheetDialog.setContentView(view);
                TextView yes,no;
                yes=view.findViewById(R.id.yes_delete);
                no=view.findViewById(R.id.no_delete);
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                    }
                });
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        delete();
                    }
                });
                bottomSheetDialog.show();
                break;
        }
        return true;
    }

    private void delete() {

        notesViewModel.deleteNotes(id);

    }
}