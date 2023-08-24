package com.alvaro.anotafacil.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alvaro.anotafacil.databinding.FragmentNoteFavoriteBinding

class NoteFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentNoteFavoriteBinding
    private lateinit var viewModel: NoteFavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NoteFavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVNoteListAdapter(listOf()) { note ->
            val destination = NoteFavoriteFragmentDirections.actionNoteFavoriteFragmentToNoteDetailFragment(note)
            findNavController().navigate(destination)
        }
        binding.rvFavorites.adapter = adapter
        viewModel.favorites.observe(requireActivity()) {
            adapter.notes = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getFavorites()
    }

}