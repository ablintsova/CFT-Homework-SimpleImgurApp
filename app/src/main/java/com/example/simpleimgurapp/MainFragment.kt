package com.example.simpleimgurapp

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.simpleimgurapp.api.ImgurApi
import com.example.simpleimgurapp.api.QueryConstants
import com.example.simpleimgurapp.api.model.UploadCallback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val query = QueryConstants
    private val retrofit = Retrofit.Builder()
            .baseUrl(QueryConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val service = retrofit.create(ImgurApi::class.java)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.findViewById<Button>(R.id.btnUpload)?.setOnClickListener { getImageFromDevice() }
    }

    private fun getImageFromDevice() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
                .setType("image/*")
        startActivityForResult(Intent.createChooser(intent, "Select an image"), 111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        data?.let {
            if (requestCode == 111 && resultCode == RESULT_OK) {
                var imagePath: String? = null
                it.data?.let { uri ->
                    imagePath = DocumentHelper.getPath(requireContext(), uri)//getPathFromUri(requireContext(), uri)
                }
                Log.d("onActivityResult", "imagePath = $imagePath")
                val image: File? = File(imagePath!!)
                uploadImageToImgur(image)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun uploadImageToImgur(image: File?) {
        image?.let {
            val upload = service.uploadImage(
                    //query.ACCESS_TOKEN,
                    query.CLIENT_ID,
                    it
            )
            upload.enqueue(UploadCallback())
        }
    }
}