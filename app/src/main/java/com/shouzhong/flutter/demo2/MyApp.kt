package com.shouzhong.flutter.demo2

import android.app.Application
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MyApp : Application() {
    lateinit var flutterEngine : FlutterEngine

    override fun onCreate() {
        super.onCreate()
        flutterEngine = FlutterEngine(this)
//        可设置初始路由
//        flutterEngine.getNavigationChannel().setInitialRoute("your/route/here");
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        FlutterEngineCache.getInstance().put("my_engine_id", flutterEngine)
    }

    override fun onTerminate() {
        flutterEngine.destroy()
        super.onTerminate()
    }
}