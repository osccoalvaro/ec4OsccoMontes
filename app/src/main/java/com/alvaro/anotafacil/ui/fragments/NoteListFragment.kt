package com.alvaro.anotafacil.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alvaro.anotafacil.databinding.FragmentNoteListBinding
import com.alvaro.anotafacil.ui.viewmodels.NoteListViewModel

class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding
    private lateinit var viewModel: NoteListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NoteListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVNoteListAdapter(listOf()) { note ->
            val destination = NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(note)
            findNavController().navigate(destination)
        }
        binding.rvNoteList.adapter = adapter
        viewModel.notes.observe(requireActivity()) {
            adapter.notes = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getNotesFromService()
    }

}