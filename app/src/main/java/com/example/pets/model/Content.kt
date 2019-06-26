package com.example.pets.model

import com.google.gson.annotations.SerializedName

data class Content (
    @SerializedName("name") val name : String,
    @SerializedName("gender") val gender : String,
    @SerializedName("age") val age : Int,
    @SerializedName("pets") val pets : List<Pets>?
)