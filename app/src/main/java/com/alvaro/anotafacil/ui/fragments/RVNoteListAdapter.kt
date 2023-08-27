package com.alvaro.anotafacil.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alvaro.anotafacil.databinding.ItemNoteBinding
import com.alvaro.anotafacil.model.Note
import com.bumptech.glide.Glide

class RVNoteListAdapter(var notes: List<Note>, val onNoteClick:(Note) -> Unit): RecyclerView.Adapter<NoteVH>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteVH(binding, onNoteClick)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteVH, position: Int) {
        holder.bind(notes[position])
    }

}

class NoteVH(private val binding: ItemNoteBinding, val onNoteClick:(Note) -> Unit): ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.txtNoteTitle.text = note.Nombre
        binding.txtContentNote.text = note.Genero
        binding.txtLablesNote.text = note.Estado
        binding.txtCreateNote.text = note.Ocupacion
        // Cargar la imagen usando Glide
        Glide.with(itemView.context)
            .load(note.Imagen)
            .into(binding.imgSimpson)
        binding.root.setOnClickListener {
            // Pasar a la siguiente pantalla
            onNoteClick(note)
        }
    }

}