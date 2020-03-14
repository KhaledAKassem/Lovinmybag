package com.beetleware.lovinmybag.di.component

import android.content.Context
import android.content.SharedPreferences
import com.beetleware.lovinmybag.BeetlewareApp
import com.beetleware.lovinmybag.di.module.AppModule
import com.beetleware.lovinmybag.di.module.NetworkModule
import com.beetleware.lovinmybag.ui.base.BaseViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun app(): BeetlewareApp
    fun context(): Context
    fun inject(baseViewModel: BaseViewModel)
    fun getSharedPreference(): SharedPreferences

}