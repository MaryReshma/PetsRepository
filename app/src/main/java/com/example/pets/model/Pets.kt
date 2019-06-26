package com.example.pets.model

import com.google.gson.annotations.SerializedName

data class Pets(
    @SerializedName("name") val name : String,
    @SerializedName("type") val type : String
)