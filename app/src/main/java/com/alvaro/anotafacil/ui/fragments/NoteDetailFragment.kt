package com.alvaro.anotafacil.ui.fragments

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.alvaro.anotafacil.databinding.FragmentNoteDetailBinding
import com.alvaro.anotafacil.model.Note
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private val args: NoteDetailFragmentArgs by navArgs()
    private lateinit var note: Note
    private lateinit var viewModel: NoteDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        note = args.note
        viewModel = ViewModelProvider(requireActivity())[NoteDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtTitle.text = note.Nombre
        binding.txtNote.text = note.Genero
        binding.txtEstado.text = note.Estado
        binding.txtHistoria.text = note.Historia
        binding.txtOcupacion.text = note.Ocupacion

        // Cargar la imagen usando Glide
        Glide.with(requireContext())
            .load(note.Imagen)
            .into(binding.img)

        if (note.isFavorite) {
            binding.btnAddFavorite.visibility = View.GONE
        }

        /* binding.btnAddFavorite.setOnClickListener {
            // agregar a favorito
            note.isFavorite = true
            viewModel.addFavorites(note)
            Snackbar.make(binding.root, "Agregado a favoritos", Snackbar.LENGTH_SHORT).show()
        } */

        binding.btnAddFavorite.setOnClickListener {
            if (!note.isFavorite) {
                note.isFavorite = true
                viewModel.viewModelScope.launch {
                    if (!viewModel.isFavorite(note._id)) {
                        viewModel.addFavorites(note)
                        Snackbar.make(binding.root, "Agregado a favoritos", Snackbar.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(binding.root, "El elemento ya está en favoritos", Snackbar.LENGTH_SHORT).show()
                    }
                }
            } else {
                Snackbar.make(binding.root, "El elemento ya está en favoritos", Snackbar.LENGTH_SHORT).show()
            }
        }




    }

}