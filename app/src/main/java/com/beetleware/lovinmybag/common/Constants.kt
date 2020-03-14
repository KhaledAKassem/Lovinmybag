package com.beetleware.lovinmybag.common

object Constants {

    //numbers
    const val CACHE_SIZE = 10 * 1024 * 1024 //10MB
    const val CACHE_MAX_AGE = 5 //5 seconds
    const val CACHE_MAX_STALE = 60 * 60 * 24 * 7 //1 week
    const val GLOBAL="global"
    const val ARABIC = 1
    const val ENGLISH = 0
    const val NOT_DEFINED_LANG = -1

    //time
    const val SNAK_BAR_DURATION: Int = 3 * 1000

    //strings
    const val NETWORKING_LOG = "networking"

    //shared preference keys
    const val PREFERENCE_NAME = "CREP_SHARED_PREFERENCE"

    const val CURRENT_LANGUAGE_KEY = "CURRENT_LANGUAGE"

    const val GALLERY_REQUEST_CODE: Int = 98
    const val CAMERA_REQUEST_CODE: Int = 97

    // Shard Logging

    const val IS_USER_LOGGED_IN_KEY = "IS_USER_LOGGED_IN"
    const val USER_ID_KEY = "USER_ID"
    const val USER_ACCESS_TOKEN_KEY = "USER_ACCESS_TOKEN"
    const val USER_PROFILE_IS_COMPLETED_KEY = "USER_PROFILE_IS_COMPLETED"
    const val USER_PROFILE_IS_CONFIRMED_KEY = "USER_PROFILE_IS_CONFIRMED"
    const val USER_PRIVILADGE="USER_PRIVILADGE"

    const val REQUEST_CAMERA_PERMISSION_CODE: Int = 96
    const val REQUEST_STORAGE_PERMISSION_CODE: Int = 95
    const val REQUEST_LOCATION_PERMISSION_CODE: Int = 94
    const val LOCATION_REQUEST_CODE: Int = 93

    // Notifications
    const val notificationGlobalType = "global"

}