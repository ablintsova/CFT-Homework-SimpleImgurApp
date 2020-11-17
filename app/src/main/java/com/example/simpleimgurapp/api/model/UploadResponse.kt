package com.example.simpleimgurapp.api.model

import com.google.gson.annotations.SerializedName

data class UploadResponse(
        @SerializedName("status")
        var status: String = "") {

    @SerializedName("data")
    var data: UploadData? = null

    override fun toString(): String {
        return "status: $status, data: $data"
    }
}