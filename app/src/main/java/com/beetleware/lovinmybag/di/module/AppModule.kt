package com.beetleware.lovinmybag.di.module

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.beetleware.lovinmybag.BeetlewareApp
import com.beetleware.lovinmybag.common.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: BeetlewareApp) {

    /**
     * Provides App instance of the application
     *
     * @return the instance of application
     */
    @Provides
    @Singleton
    fun provideApplication() = application

    /**
     * provide context to be used in data repository
     *
     * @return applicationContext
     */
    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    /**
     * provide shared preference to store data
     *
     * @return shared preference instance
     */
    @Provides
    @Singleton
    fun provideSharedPref() = application.getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE)




}