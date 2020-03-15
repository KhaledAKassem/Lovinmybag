package com.beetleware.lovinmybag.ui.fragments.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.beetleware.lovinmybag.data.network.model.ApiResponse
import com.beetleware.lovinmybag.data.network.model.GeneralResponse
import com.beetleware.lovinmybag.data.network.model.LoginResponse
import com.beetleware.lovinmybag.data.network.model.SoldItemResponse
import com.beetleware.lovinmybag.ui.base.BaseViewModel

class HomeViewModel (app:Application) : BaseViewModel(app){
    fun getSoldItems(): MutableLiveData<ApiResponse<SoldItemResponse>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.getSoldItem()
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

    fun getProductCount(): MutableLiveData<ApiResponse<SoldItemResponse>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.getProductCount()
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }}