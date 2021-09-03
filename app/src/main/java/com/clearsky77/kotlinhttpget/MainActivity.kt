package com.clearsky77.kotlinhttpget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    val handler = Handler() {
        when (it.what) {
            HttpRequest.MSG_RESPONSE_FAIL -> {
                Toast.makeText(this, "Response failed", Toast.LENGTH_SHORT).show()
            }
            HttpRequest.MSG_RESPONSE_SUCCESS -> {
                textResponse.setText((it.obj as String))
            }
        }

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        httpGetBnt_postman.setOnClickListener() {
            val httpRequest = HttpRequest("GET", "https://postman-echo.com/get", handler)
                                                            //보낸 값을 그대로 반환해주는 사이트
            httpRequest.setParam("name", "냥냐루")
            httpRequest.setParam("phone", "01023456789")
            httpRequest.start()
        }

        httpPostBnt_postman.setOnClickListener(){
            val httpRequest = HttpRequest("POST", "https://postman-echo.com/post", handler)
            //보낸 값을 그대로 반환해주는 사이트
            httpRequest.setParam("name", "냐냐루")
            httpRequest.setParam("phone", "01023456789")
            httpRequest.start()
        }

    }
}