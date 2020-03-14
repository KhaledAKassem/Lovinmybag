package com.beetleware.lovinmybag.common.extensions

import android.os.Bundle

fun androidx.fragment.app.DialogFragment.showDialog(
        dialogFragment: androidx.fragment.app.DialogFragment, bundle: Bundle? = null,
        tag: String? = dialogFragment::class.java.simpleName
) = activity?.showDialog(dialogFragment, bundle, tag)

fun androidx.fragment.app.DialogFragment.appComponent() = activity?.appComponent()!!

fun androidx.fragment.app.DialogFragment.close() {

    this.dismissAllowingStateLoss()

    //activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commitAllowingStateLoss()
}
