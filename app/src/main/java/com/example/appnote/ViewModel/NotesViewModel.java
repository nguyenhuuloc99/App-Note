package com.example.appnote.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appnote.Model.Notes;
import com.example.appnote.Repository.NoteRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    NoteRepository noteRepository;
    public LiveData<List<Notes>>getAllNotes;
    public LiveData<List<Notes>>hightolow;
    public LiveData<List<Notes>>lowtohigh;
    public NotesViewModel(@NonNull Application application) {
        super(application);
       noteRepository=new NoteRepository(application);
       getAllNotes=noteRepository.getAllNotes;
       hightolow=noteRepository.hightolow;
       lowtohigh=noteRepository.lowtohigh;
    }
    public void insertNotes(Notes notes)
    {
        noteRepository.insertNotes(notes);
    }
    public void deleteNotes(int id)
    {
        noteRepository.deleteNotes(id);
    }
    public void updateNotes(Notes notes)
    {
        noteRepository.updateNotes(notes);
    }
}
