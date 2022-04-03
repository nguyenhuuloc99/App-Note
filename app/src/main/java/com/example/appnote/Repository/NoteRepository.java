package com.example.appnote.Repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.appnote.DAO.NotesDao;
import com.example.appnote.Database.NotesDatabase;
import com.example.appnote.Model.Notes;

import java.util.List;

public class NoteRepository {
    public NotesDao notesDao;

    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>>hightolow;
    public LiveData<List<Notes>>lowtohigh;
    public NoteRepository(Application application)
    {
        NotesDatabase notesDatabase=NotesDatabase.getInstance(application);
        notesDao=notesDatabase.dao_notes();
        getAllNotes=notesDao.getAllNote();
        hightolow=notesDao.hightolow();
        lowtohigh=notesDao.lowtohigh();
    }
    public void insertNotes(Notes notes)
    {
        notesDao.insertNote(notes);
    }
    public void deleteNotes(int id)
    {
        notesDao.delete(id);
    }
    public void updateNotes(Notes notes)
    {
        notesDao.updateNote(notes);
    }

}
