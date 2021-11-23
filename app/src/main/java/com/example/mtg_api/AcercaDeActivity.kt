package com.example.mtg_api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import com.example.mtg_api.databinding.ActivityAcercaDeBinding

class AcercaDeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAcercaDeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAcercaDeBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btVolver.setOnClickListener { volver() }
        binding.btPulsaAqui.setOnClickListener { menuPopUp() }
    }

    private fun volver(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun menuPopUp(){
        val menupopup = PopupMenu(this, binding.btPulsaAqui)
        menupopup.inflate(R.menu.popup)
        menupopup.setOnMenuItemClickListener {
            Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
            true
        }
        menupopup.show()
    }
}