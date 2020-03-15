package com.beetleware.lovinmybag.data.network

import androidx.lifecycle.MutableLiveData
import com.beetleware.lovinmybag.data.network.model.*


interface NetworkHelper {
    // Networking
    fun login(
        username: String,
        password: String
    ): MutableLiveData<ApiResponse<GeneralResponse<LoginResponse>>>

    fun getProfile():MutableLiveData<ApiResponse<GeneralResponse<ProfileResponse>>>

    fun getSoldItem():MutableLiveData<ApiResponse<SoldItemResponse>>

    fun getProductCount():MutableLiveData<ApiResponse<SoldItemResponse>>

    fun getEmployeeStatics():MutableLiveData<ApiResponse<GeneralResponse<EmployeeStaticsResponse>>>

    }
