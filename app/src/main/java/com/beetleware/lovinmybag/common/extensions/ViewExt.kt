package com.beetleware.lovinmybag.common.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun ViewGroup.inflate(@LayoutRes resourceId: Int) =
    LayoutInflater.from(context).inflate(
        resourceId,
        this,
        false
    )

fun <T : ViewDataBinding?> ViewGroup.bindingInflate(@LayoutRes resourceId: Int) =
    DataBindingUtil.inflate<T>(
        LayoutInflater.from(context),
        resourceId,
        this,
        false
    )

 fun ViewGroup.forEach(action: (view: View) -> Unit) {
    children.forEach {
        if (it is ViewGroup)
            it.forEach(action)
        else
            action(it)
    }
}

fun View.hide() {
    visibility = GONE
}

fun View.show() {
    visibility = VISIBLE
}



