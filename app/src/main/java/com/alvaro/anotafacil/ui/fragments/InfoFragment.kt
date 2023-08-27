package com.alvaro.anotafacil.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alvaro.anotafacil.databinding.FragmentInfoBinding
import com.alvaro.anotafacil.databinding.ItemSimpsonBinding
import com.alvaro.anotafacil.model.SimpsonItem
import com.google.firebase.firestore.FirebaseFirestore

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        firestore = FirebaseFirestore.getInstance()

        // Crear una instancia de ItemSimpsonBinding
        val itemBinding = ItemSimpsonBinding.inflate(inflater, container, false)

        val adapter = MyRecyclerViewAdapter(itemBinding)
        binding.rvFirestoreList.adapter = adapter

        // Obtenemos los datos de Firestore y actualizamos el adaptador
        firestore.collection("simpson")
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    return@addSnapshotListener
                }

                adapter.clearData()

                snapshot?.documents?.forEach { document ->
                    val nombre = document["Nombre"] as? String
                    val genero = document["Genero"] as? String
                    val ocupacion = document["Ocupacion"] as? String

                    if (nombre != null && genero != null && ocupacion != null) {
                        val item = SimpsonItem(nombre, genero, ocupacion)
                        adapter.addItem(item)
                    }
                }
            }
        return binding.root
    }

}