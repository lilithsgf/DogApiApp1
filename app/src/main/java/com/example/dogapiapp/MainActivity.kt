package com.example.dogapiapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.dogapiapp.viewmodel.DogViewModel

class MainActivity : AppCompatActivity() {
    private val dogViewModel: DogViewModel by viewModels()
    private lateinit var dogImageView: ImageView
    private lateinit var fetchDogButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogImageView = findViewById(R.id.dogImageView)
        fetchDogButton = findViewById(R.id.fetchDogButton)

        // Observa los cambios en la imagen del perro
        dogViewModel.dogImage.observe(this) { response ->
            if (response != null) {
                Glide.with(this)
                    .load(response.imageUrl)
                    .centerCrop()
                    .into(dogImageView)
            } else {
                Toast.makeText(
                    this, "Error al obtener la imagen del perro",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        // Configura el clic del botón
        fetchDogButton.setOnClickListener {
            dogViewModel.getRandomDogImage()
        }
        // Llama a la API al iniciar la app
        dogViewModel.getRandomDogImage()

    }
}
