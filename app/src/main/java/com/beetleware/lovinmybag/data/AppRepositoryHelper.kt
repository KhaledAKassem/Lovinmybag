package com.beetleware.lovinmybag.data


import androidx.lifecycle.MutableLiveData
import com.beetleware.lovinmybag.data.network.AppNetworkHelper
import com.beetleware.lovinmybag.data.network.model.VerificationResponse
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

    override fun removeUser() {
        preferenceHelper.removeUser()
        isUserLoggedinObservable.postValue(false)
    }

    override fun storeUser(user: VerificationResponse.Data) {
        preferenceHelper.storeUser(user)
        isUserLoggedinObservable.postValue(true)
    }

    override fun isUserLoggedIn() = preferenceHelper.isUserLoggedIn()
    override fun getUserId() = preferenceHelper.getUserId()
    override fun getUserAccessToken() = preferenceHelper.getUserAccessToken()
    override fun setUserAccessToken(token:String) =preferenceHelper.setUserAccessToken(token)

    override fun getUserProfileCompletedState() = preferenceHelper.getUserProfileCompletedState()
    override fun setUserProfileCompletedState(completed: Boolean) =
        preferenceHelper.setUserProfileCompletedState(completed)

    override fun getUserProfileConfirmedState() = preferenceHelper.getUserProfileConfirmedState()
    override fun setUserProfileConfirmedState(confirmed: Int) =
        preferenceHelper.setUserProfileConfirmedState(confirmed)

    /** Networking **/

}