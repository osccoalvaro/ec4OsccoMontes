package com.alvaro.anotafacil.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alvaro.anotafacil.R
import com.alvaro.anotafacil.databinding.ActivityMainBinding
import com.alvaro.anotafacil.ui.AddNoteActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    //private lateinit var txtHello: TextView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        // Cerrar Sesion
        binding.btnCerrarSesion.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Clase#2
        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_note) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvMenu.setupWithNavController(navController)

        /* binding.bnvMenu.setOnItemSelectedListener { item->
            when (item.itemId) {
                R.id.note_list -> {
                    binding.txtHello.text = "Lista"
                    true
                }
                R.id.note_favorite -> {
                    binding.txtHello.text = "Favorito"
                    true
                }
                R.id.note_info -> {
                    binding.txtHello.text = "Info"
                    true
                } else -> {
                    false
                }
            }
        } */
    }

}