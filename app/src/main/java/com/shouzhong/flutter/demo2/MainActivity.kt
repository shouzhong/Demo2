package com.shouzhong.flutter.demo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.flutter.embedding.android.FlutterActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickFlutter(view: View) {
        startActivity(FlutterActivity.withCachedEngine("my_engine_id").build(this))
    }
}