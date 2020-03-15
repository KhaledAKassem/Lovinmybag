package com.beetleware.lovinmybag.data

import androidx.lifecycle.MutableLiveData
import com.beetleware.lovinmybag.data.network.AppNetworkHelper
import com.beetleware.lovinmybag.data.network.model.*
import com.beetleware.lovinmybag.data.preferences.AppPreferenceHelper
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppRepositoryHelper @Inject constructor(
    private val networkHelper: AppNetworkHelper,
    private val preferenceHelper: AppPreferenceHelper
) : RepositoryHelper {


    val isUserLoggedinObservable = MutableLiveData<Boolean>().apply {
        postValue(isUserLoggedIn())
    }

    /** preferences **/
    override fun setUserLanguage(language: Int) = preferenceHelper.setUserLanguage(language)
    override fun getUserLanguage() = preferenceHelper.getUserLanguage()


    override fun storeUser(user: LoginResponse) {
        preferenceHelper.storeUser(user)
        isUserLoggedinObservable.postValue(true)
    }

    override fun isUserLoggedIn() = preferenceHelper.isUserLoggedIn()
    override fun getUserAccessToken() = preferenceHelper.getUserAccessToken()
    override fun setUserAccessToken(token:String) =preferenceHelper.setUserAccessToken(token)
    override fun removeUser() {
        preferenceHelper.removeUser()
        isUserLoggedinObservable.postValue(false)
    }
    /** Networking **/


    override fun login(
        username: String,
        password: String
    )  = networkHelper.login(username, password)
    override fun getProfile() = networkHelper.getProfile()
    override fun getSoldItem() = networkHelper.getSoldItem()
    override fun getProductCount() = networkHelper.getProductCount()
    override fun getEmployeeStatics() = networkHelper.getEmployeeStatics()


}