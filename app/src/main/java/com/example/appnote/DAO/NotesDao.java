package com.example.appnote.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appnote.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {
    @Query("SELECT *FROM  Note_database")
    LiveData<List<Notes>>getAllNote();
    @Insert
    void insertNote(Notes notes);
    @Query("DELETE FROM Note_database WHERE id=:id")
    void delete(int id);
     @Update
    void updateNote(Notes notes);

     @Query("SELECT *FROM Note_database ORDER BY notesPriority DESC")
    LiveData<List<Notes>>hightolow();
    @Query("SELECT *FROM Note_database ORDER BY notesPriority ASC")
    LiveData<List<Notes>>lowtohigh();
}
