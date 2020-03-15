package com.beetleware.lovinmybag.data.network.model


import com.google.gson.annotations.SerializedName

data class GeneralResponse <T>(

    @SerializedName("data")
    val data: T?,
    @SerializedName("message")
    val message:String,
    @SerializedName("status")
    val status:String

)