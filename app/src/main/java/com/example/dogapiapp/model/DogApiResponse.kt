package com.example.dogapiapp.model
import com.google.gson.annotations.SerializedName

data class DogApiResponse(
    @SerializedName("message")
    val imageUrl: String,
    val status: String
)
