package com.beetleware.lovinmybag.data.preferences

import com.beetleware.lovinmybag.data.network.model.LoginResponse
import com.beetleware.lovinmybag.data.network.model.VerificationResponse


interface PreferenceHelper {

    /**
     * use this method to change user language
     *
     * @param language the language code (0 for en and 1 for arabic)
     */
    fun setUserLanguage(language: Int)

    /**
     * use this method to get the stored user language code or -1 if no language stored
     */
    fun getUserLanguage(): Int


    /**
     * use this method to store user data
     */
    fun storeUser(user: LoginResponse)

    /**
     * user logIn flag
     *
     * @return true if user is loggedIn, false otherwise
     */
    fun isUserLoggedIn(): Boolean

    /**
     * get the stored user access token
     *
     * @return the user access token
     */
    fun getUserAccessToken(): String

    fun setUserAccessToken(token:String)

    /**
     * use this method to log out the user and remove his info
     */
    fun removeUser()
}