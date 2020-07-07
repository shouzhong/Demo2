package com.shouzhong.flutter.demo2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.ThreadUtils
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.EventChannel

class FlutterTest1Activity : BaseActivity() {
    companion object {
        const val FLUTTER_CHANNEL = "com.shouzhong.flutter.demo2/aaa"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_flutter_test1)
        val frgm: FlutterFragment = FlutterFragment.withNewEngine().initialRoute("page_home").build()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, frgm)
            .commit()
        supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
                if (f == frgm) {
                    EventChannel(frgm.flutterEngine?.dartExecutor?.binaryMessenger, FLUTTER_CHANNEL).setStreamHandler(object : EventChannel.StreamHandler {
                        override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                            events?.success("123456")
                        }

                        override fun onCancel(arguments: Any?) {}
                    })
                }
            }
        }, true)
    }
}