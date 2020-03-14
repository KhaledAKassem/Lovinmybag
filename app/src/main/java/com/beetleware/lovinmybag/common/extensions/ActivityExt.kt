package com.beetleware.lovinmybag.common.extensions

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.beetleware.lovinmybag.BeetlewareApp


fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

fun Activity.getThisColor(@ColorRes id: Int) = ContextCompat.getColor(baseContext, id)



inline fun Activity.alertDialog(body: AlertDialog.Builder.() -> AlertDialog.Builder): AlertDialog {
    return AlertDialog.Builder(this)
        .body()
        .show()
}

fun Activity.goToActivity(activityClass: Class<*>) = this.startActivity(Intent(this, activityClass))

fun Activity.addFragment(
    @IdRes id: Int, fragment: Fragment, bundle: Bundle? = null,
    addToStack: Boolean = false, tag: String? = fragment::class.java.simpleName
): Int {

    fragment.arguments = bundle

    return if (addToStack)
        (this as FragmentActivity)
            .supportFragmentManager
            .beginTransaction()
            .add(id, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    else
        (this as FragmentActivity)
            .supportFragmentManager
            .beginTransaction()
            .add(id, fragment, tag)
            .commitAllowingStateLoss()
}

fun Activity.replaceFragment(
    @IdRes id: Int, fragment: Fragment, bundle: Bundle? = null,
    addToStack: Boolean = false, tag: String? = fragment::class.java.simpleName
): Int {

    fragment.arguments = bundle

    return if (addToStack)
        (this as FragmentActivity)
            .supportFragmentManager
            .beginTransaction()
            .replace(id, fragment, tag)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    else
        (this as FragmentActivity)
            .supportFragmentManager
            .beginTransaction()
            .replace(id, fragment, tag)
            .commitAllowingStateLoss()
}

fun Activity.popBackStack(fragment: Fragment? = null): Boolean {
    return (this as FragmentActivity)
        .supportFragmentManager
        .popBackStackImmediate()
}

fun Activity.appComponent() = (this.application as BeetlewareApp).component

fun Activity.showDialog(
    dialogFragment: DialogFragment, bundle: Bundle? = null,
    tag: String? = dialogFragment::class.java.simpleName
) {

    if (dialogFragment.isAdded) return

    dialogFragment.arguments = bundle
    return dialogFragment.show((this as FragmentActivity).supportFragmentManager, tag)
}

fun Activity.popAllFragments() {
    for (fragment in (this as FragmentActivity).supportFragmentManager.fragments) {
        fragment.popBackStack()
    }
}





