package com.shouzhong.flutter.demo2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ThreadUtils
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StandardMessageCodec

class FlutterTest1Activity : BaseActivity() {
    var events: EventChannel.EventSink? = null
    var basicMessageChannel: BasicMessageChannel<Any>? = null
    var basicMessageChannel2: BasicMessageChannel<Any>? = null
    var methodChannel: MethodChannel? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_flutter_test1)
        val frgm: FlutterFragment = FlutterFragment.withNewEngine().initialRoute("page_home").build()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, frgm)
            .commit()
        supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
                if (f != frgm) return
                EventChannel(frgm.flutterEngine?.dartExecutor?.binaryMessenger, "com.shouzhong.flutter.demo2/event")
                    .setStreamHandler(object : EventChannel.StreamHandler {
                        override fun onListen(
                            arguments: Any?,
                            events: EventChannel.EventSink?
                        ) {
                            this@FlutterTest1Activity.events = events
                            events?.success("${System.currentTimeMillis()}")
                        }

                        override fun onCancel(arguments: Any?) {}
                    })
                basicMessageChannel = BasicMessageChannel(frgm.flutterEngine!!.dartExecutor.binaryMessenger, "com.shouzhong.flutter.demo2/basic", StandardMessageCodec.INSTANCE)
                basicMessageChannel?.setMessageHandler { message, reply ->
                    LogUtils.e("onMessage:$message")
                    reply.reply("返回给flutter的数据")
                    basicMessageChannel2?.send("发送给Flutter的数据") {
                        LogUtils.e("reply:$it")
                    }
                }
                basicMessageChannel2 = BasicMessageChannel(frgm.flutterEngine!!.dartExecutor.binaryMessenger, "com.shouzhong.flutter.demo2/basic2", StandardMessageCodec.INSTANCE)
                methodChannel = MethodChannel(frgm.flutterEngine!!.dartExecutor.binaryMessenger, "com.shouzhong.flutter.demo2/method")
                methodChannel?.setMethodCallHandler { call, result ->
                    if (call.method == "add") {
                        val r = add(call.argument<Int>("i1")!!, call.argument<Int>("i2")!!)
                        result.success("$r")
                    }
                }
            }
        }, true)
    }

    override fun onResume() {
        super.onResume()
        events?.success("${System.currentTimeMillis()}")
    }

    override fun onDestroy() {
        events = null
        basicMessageChannel = null
        basicMessageChannel2 = null
        super.onDestroy()
    }

    private fun add(i1: Int, i2: Int) : Int = i1 + i2
}