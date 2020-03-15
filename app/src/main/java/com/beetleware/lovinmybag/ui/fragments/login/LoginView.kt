package com.beetleware.lovinmybag.ui.fragments.login

import com.beetleware.lovinmybag.ui.base.BaseView

interface LoginView : BaseView {
    fun login(userName: String, password: String)
}