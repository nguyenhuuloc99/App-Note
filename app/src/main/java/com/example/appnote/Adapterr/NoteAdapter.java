package com.example.appnote.Adapterr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnote.Activity.Update_Activity;
import com.example.appnote.Model.Notes;
import com.example.appnote.R;
import com.example.appnote.databinding.ItemNoteBinding;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    List<Notes>list;
    Context context;

    public NoteAdapter(List<Notes> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notes notes=list.get(position);
        holder.itemNoteBinding.noteDate.setText(notes.notesDate);
        holder.itemNoteBinding.noteSubtitle.setText(notes.notesSubTitle);
        holder.itemNoteBinding.noteTitle.setText(notes.notesTitle);
        if (notes.notesPriority.equals("1"))
        {
            holder.itemNoteBinding.notePriority.setBackgroundResource(R.drawable.green_shape);
        }else if(notes.notesPriority.equals("2"))
        {
            holder.itemNoteBinding.notePriority.setBackgroundResource(R.drawable.yellow_shape);
        }else
        {
            holder.itemNoteBinding.notePriority.setBackgroundResource(R.drawable.red_shape);
        }
        holder.itemNoteBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Update_Activity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("note",notes);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
            ItemNoteBinding itemNoteBinding;
        public ViewHolder(@NonNull ItemNoteBinding binding) {
            super(binding.getRoot());
            this.itemNoteBinding=binding;
        }
    }
}
