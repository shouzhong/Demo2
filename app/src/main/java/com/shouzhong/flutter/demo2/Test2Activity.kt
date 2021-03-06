package com.shouzhong.flutter.demo2

import android.os.Bundle
import io.flutter.embedding.android.FlutterFragment

class Test2Activity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_test2)
        val frgm: FlutterFragment = FlutterFragment.withNewEngine().initialRoute("page_login").build()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, frgm)
            .commit()
    }
}