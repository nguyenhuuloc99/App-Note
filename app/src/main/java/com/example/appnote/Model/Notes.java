package com.example.appnote.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Note_database")
public class Notes implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "notesTitle")
    public String notesTitle;
    @ColumnInfo(name = "notesSubTitle")
    public String notesSubTitle;
    @ColumnInfo(name = "notesDate")
    public String notesDate;
    @ColumnInfo(name = "notes")
    public String notes;
    @ColumnInfo(name = "notesPriority")
    public String notesPriority;

}
