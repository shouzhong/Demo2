package com.shouzhong.flutter.demo2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickFlutter(view: View) {
        startActivity(Intent(this, FlutterTest1Activity::class.java))
    }

    fun onClickLogin(view: View) {
        startActivity(Intent(this, Test2Activity::class.java))
    }
}