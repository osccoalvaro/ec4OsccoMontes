package com.alvaro.anotafacil.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alvaro.anotafacil.databinding.ActivityAddNoteBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        firestore = Firebase.firestore
        setContentView(binding.root)

        binding.btnRegisterNote.setOnClickListener {
            val Nombre = binding.tilTitleNote.editText?.text.toString()
            val Genero = binding.tilGeneroSimpson.editText?.text.toString()
            val Ocupacion = binding.tilOcupacionSimpson.editText?.text.toString()

            if (Nombre.isNotEmpty() && Genero.isNotEmpty() && Ocupacion.isNotEmpty()) {
                addToFirestore(Nombre, Genero, Ocupacion)
            }
        }
    }

    private fun addToFirestore(Nombre: String, Genero: String, Ocupacion: String) {
        val newNote = hashMapOf<String, Any>(
            "Nombre" to Nombre,
            "Genero" to Genero,
            "Ocupacion" to Ocupacion,
        )
        firestore.collection("simpson").add(newNote).addOnSuccessListener {
            Toast.makeText(this, "Personaje agregado con id: ${it.id}", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(this, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
    }

}