package com.shouzhong.flutter.demo2

import android.os.Bundle
import io.flutter.embedding.android.FlutterFragment

class Test3Activity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_test3)
        val frgm: FlutterFragment = FlutterFragment.withNewEngine().initialRoute("page_web").build()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, frgm)
            .commit()
    }
}