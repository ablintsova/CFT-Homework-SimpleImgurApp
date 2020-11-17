package com.example.simpleimgurapp.api.model

import com.google.gson.annotations.SerializedName

class UploadData {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("error")
    var error: String? = null

    override fun toString(): String {
        return "title: $title, description: $description, error: $error"
    }
}