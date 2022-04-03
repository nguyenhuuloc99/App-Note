package com.example.appnote.Database;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.appnote.DAO.NotesDao;
import com.example.appnote.Model.Notes;

@Database(entities ={Notes.class} ,version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    private static final String databasename="app_notes";
    private static  NotesDatabase intance;
    public static NotesDatabase  getInstance(Application application)
    {
        if (intance==null)
        {
            intance=Room.databaseBuilder(application,NotesDatabase.class,databasename).allowMainThreadQueries()
                    .build();
        }
        return intance;
    }
    public abstract NotesDao dao_notes();

}
