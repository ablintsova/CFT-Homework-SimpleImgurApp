package com.example.simpleimgurapp.api

import com.example.simpleimgurapp.api.model.UploadResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.io.File

interface ImgurApi {

    @POST("upload")
    fun uploadImage(
            //@Header("Token") token: String,
            @Header("Authorization") clientId: String,
            @Body image: File
    ): Call<UploadResponse>
}