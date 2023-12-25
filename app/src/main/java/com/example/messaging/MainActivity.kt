package com.example.messaging

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var fcm= FirebaseMessaging.getInstance()
    var token=""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val title= findViewById<EditText>(R.id.Title)
        val body= findViewById<EditText>(R.id.body)
        val sendBtn= findViewById<Button>(R.id.send)

        fcm.token.addOnSuccessListener {
            token=it
        }

        sendBtn.setOnClickListener {

            val header = "key=AAAA9y5kDa0:APA91bEF0_mgzeuZOIxXh3WL73zjM_Vv-SuDC3FLlCoiT7abf7V-XDbhtowsUzIKtVZ_VnnwHcFQmgXT-5hMogSZHNarpEclXMET9pvt-yUjF2TFU5wS3964CFti3PbdvyTvyG3CzuOf"
            val headerMap = HashMap<String, String>()
            headerMap["Authorization"] = header

            val data= ReqModel(
                Notification(title.text.toString(),body.text.toString(),"fcm_kotlin_app",true)
            , arrayListOf("fpmUIv4zt2LSmDwAgJlH40:APA91bFK_7ATlF413kUsleZfVu_GZo8pSG0yEBcJJrBRgT8UPvL1PUJ67ce1zIhWm55xrVOyW3WBjkgvI_NCIm5p0faMxqmIAUV3BbEpzuN-0FZMhA73l64c4kZ_3Fc8x4WT_j-jJzZI")
            )

            FcmApi.createRetrofit().send(headerMap,data)
                .enqueue(object : Callback<ResModel>{
                    override fun onResponse(call: Call<ResModel>, response: Response<ResModel>) {
                        Toast.makeText(this@MainActivity, ""+ response.code(), Toast.LENGTH_SHORT).show()
                        finish()
                    }

                    override fun onFailure(call: Call<ResModel>, t: Throwable) {
                        Toast.makeText(this@MainActivity, ""+ t.message, Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }
}