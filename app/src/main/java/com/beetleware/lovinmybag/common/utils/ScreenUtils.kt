package com.beetleware.lovinmybag.common.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager


object ScreenUtils {

    fun getScreenWidth(context: Context): Int {
        val windowManager = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.let {
            val dm = DisplayMetrics()
            it.defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }
    }

    fun getScreenHeight(context: Context): Int {
        val windowManager = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.let {
            val dm = DisplayMetrics()
            it.defaultDisplay.getMetrics(dm)
            return dm.heightPixels
        }
    }

    fun getScreenAspectRatio(context: Context): Double {
        val resources = context.resources
        val config = resources.configuration
        val dm = resources.displayMetrics

        val screenWidthInPixels = config.screenWidthDp.toDouble() * dm.density
        var screenHeightInPixels = screenWidthInPixels * dm.heightPixels / dm.widthPixels


        if (showNavigationBar(context.resources))
            screenHeightInPixels += getNavigationBarHeight(context)

        return screenHeightInPixels / screenWidthInPixels

    }


    private fun showNavigationBar(resources: Resources): Boolean {
        val id = resources.getIdentifier("config_showNavigationBar", "bool", "android")
        return id > 0 && resources.getBoolean(id)
    }

    private fun getNavigationBarHeight(context: Context): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val metrics = DisplayMetrics()
            (context as Activity).windowManager.defaultDisplay.getMetrics(metrics)
            val usableHeight = metrics.heightPixels
            context.windowManager.defaultDisplay.getRealMetrics(metrics)
            val realHeight = metrics.heightPixels
            return if (realHeight > usableHeight)
                realHeight - usableHeight
            else
                0
        }
        return 0
    }
}
