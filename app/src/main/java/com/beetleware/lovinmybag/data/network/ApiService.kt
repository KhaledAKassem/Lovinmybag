package com.beetleware.lovinmybag.data.network

import com.beetleware.lovinmybag.data.network.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth")
    fun login(
        @Body body: Map<String, String>
    ): Call<GeneralResponse<LoginResponse>>

    @GET("profile-show")
    fun getProfile():Call<GeneralResponse<ProfileResponse>>

    @GET("sold-items")
    fun getSoldItems():Call<SoldItemResponse>

    @GET("products")
    fun getProductCount():Call<SoldItemResponse>

    @GET("employee-statistics")
    fun getEmployeeStatics():Call<GeneralResponse<EmployeeStaticsResponse>>

}

