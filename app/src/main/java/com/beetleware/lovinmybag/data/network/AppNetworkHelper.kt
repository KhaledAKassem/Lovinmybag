package com.beetleware.lovinmybag.data.network

import androidx.lifecycle.MutableLiveData
import com.beetleware.lovinmybag.common.extensions.getResponse
import com.beetleware.lovinmybag.data.network.model.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppNetworkHelper @Inject constructor(private val apiService: ApiService) : NetworkHelper {
    override fun login(
        username: String,
        password: String
    ): MutableLiveData<ApiResponse<GeneralResponse<LoginResponse>>> {
        val body = hashMapOf(
            "username" to username,
            "password" to password
        )
        return apiService.login(body).getResponse()
    }

    override fun getProfile(): MutableLiveData<ApiResponse<GeneralResponse<ProfileResponse>>> {
        return apiService.getProfile().getResponse()
    }

    override fun getSoldItem(): MutableLiveData<ApiResponse<SoldItemResponse>> {
        return apiService.getSoldItems().getResponse()
    }

    override fun getProductCount(): MutableLiveData<ApiResponse<SoldItemResponse>> {
        return apiService.getProductCount().getResponse()
    }

    override fun getEmployeeStatics(): MutableLiveData<ApiResponse<GeneralResponse<EmployeeStaticsResponse>>> {
        return apiService.getEmployeeStatics().getResponse()
    }


}