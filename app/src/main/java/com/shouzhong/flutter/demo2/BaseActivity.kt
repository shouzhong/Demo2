package com.shouzhong.flutter.demo2

import android.content.res.Configuration
import android.content.res.Resources
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shouzhong.screenhelper.ScreenHelper

abstract class BaseActivity : AppCompatActivity() {
    override fun getResources(): Resources {
        return if (super.getResources().configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) ScreenHelper.adaptHeight(
            super.getResources(),
            750
        ) else ScreenHelper.adaptWidth(super.getResources(), 750)
    }

    fun onClickBack(v: View) {
        onBackPressed()
    }
}