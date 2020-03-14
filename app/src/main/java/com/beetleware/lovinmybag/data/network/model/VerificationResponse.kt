package com.beetleware.lovinmybag.data.network.model

import com.google.gson.annotations.SerializedName

data class VerificationResponse(
    @SerializedName("data")
    val data: Data
) {
    data class Data(
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_verified")
        val is_verified: String,
        @SerializedName("token")
        val token: String
    )
}