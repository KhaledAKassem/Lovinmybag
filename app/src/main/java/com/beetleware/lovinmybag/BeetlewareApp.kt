package com.beetleware.lovinmybag

import androidx.multidex.MultiDexApplication
import com.beetleware.lovinmybag.di.component.AppComponent
import com.beetleware.lovinmybag.di.component.DaggerAppComponent
import com.beetleware.lovinmybag.di.module.AppModule

class BeetlewareApp : MultiDexApplication() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }


}