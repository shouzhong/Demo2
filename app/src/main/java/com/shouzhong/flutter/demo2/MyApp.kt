package com.shouzhong.flutter.demo2

import android.app.Application
import android.content.res.Configuration
import android.content.res.Resources
import com.shouzhong.screenhelper.ScreenHelper
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.view.FlutterMain


class MyApp : Application() {
    lateinit var flutterEngine : FlutterEngine

    override fun onCreate() {
        super.onCreate()
//        FlutterMain.startInitialization(this)
//        flutterEngine = FlutterEngine(this)
//        可设置初始路由
//        flutterEngine.getNavigationChannel().setInitialRoute("your/route/here");
//        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
//        FlutterEngineCache.getInstance().put("my_engine_id", flutterEngine)
    }

//    override fun onTerminate() {
//        flutterEngine.destroy()
//        super.onTerminate()
//    }

    override fun getResources(): Resources {
        return if (super.getResources().configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) ScreenHelper.adaptHeight(
            super.getResources(),
            750
        ) else ScreenHelper.adaptWidth(super.getResources(), 750)
    }
}