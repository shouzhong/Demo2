package com.shouzhong.flutter.demo2

import android.os.Bundle
import io.flutter.embedding.android.FlutterFragment

class FlutterTest1Activity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_flutter_test1)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, FlutterFragment.withNewEngine().initialRoute("route1").build())
            .commit()
    }
}