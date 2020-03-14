package com.beetleware.lovinmybag.data.network.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errors")
    val errors: Map<String,ArrayList<String>>,
    @SerializedName("message")
    val message: String?
)