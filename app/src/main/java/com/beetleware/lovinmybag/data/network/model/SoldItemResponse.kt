package com.beetleware.lovinmybag.data.network.model


import com.google.gson.annotations.SerializedName

data class SoldItemResponse(
    @SerializedName("data")
    val `data`: Int,
    @SerializedName("status")
    val status: String
)