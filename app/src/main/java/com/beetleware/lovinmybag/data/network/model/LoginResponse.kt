package com.beetleware.lovinmybag.data.network.model


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("authorization")
    val authorization: String
)