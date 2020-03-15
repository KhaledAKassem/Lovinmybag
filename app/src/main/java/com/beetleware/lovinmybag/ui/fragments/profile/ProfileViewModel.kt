package com.beetleware.lovinmybag.ui.fragments.profile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.beetleware.lovinmybag.data.network.model.*
import com.beetleware.lovinmybag.ui.base.BaseViewModel

class ProfileViewModel (app:Application):BaseViewModel(app) {

    fun getProfile(): MutableLiveData<ApiResponse<GeneralResponse<ProfileResponse>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.getProfile()
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }


    fun getEmployeeStatics(): MutableLiveData<ApiResponse<GeneralResponse<EmployeeStaticsResponse>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.getEmployeeStatics()
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

}