package com.example.simpleimgurapp.api.model

import android.util.Log
import retrofit2.Call
import retrofit2.Response

class UploadCallback : retrofit2.Callback<UploadResponse> {

    override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
        Log.d("uploadImage", "error uploading: ${t.message}}")
    }

    override fun onResponse(
            call: Call<UploadResponse>,
            response: Response<UploadResponse>
    ) {
        val body = response.body()
        val errorBody = response.errorBody()
        Log.d("uploadImage", "response body: $body")
        Log.e("uploadImage", "response error: $errorBody")
    }
}