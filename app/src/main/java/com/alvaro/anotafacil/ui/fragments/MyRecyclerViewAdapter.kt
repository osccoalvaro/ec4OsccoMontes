package com.alvaro.anotafacil.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvaro.anotafacil.databinding.ItemSimpsonBinding
import com.alvaro.anotafacil.model.SimpsonItem

class MyRecyclerViewAdapter(private val binding: ItemSimpsonBinding) :

    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

    private val items: MutableList<SimpsonItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSimpsonBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    fun addItem(item: SimpsonItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemSimpsonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SimpsonItem) {
            binding.textViewNombre.text = item.nombre
            binding.textViewGenero.text = item.genero
            binding.textViewOcupacion.text = item.ocupacion
        }
    }

}