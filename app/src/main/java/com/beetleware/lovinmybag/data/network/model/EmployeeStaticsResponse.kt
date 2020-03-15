package com.beetleware.lovinmybag.data.network.model


import com.google.gson.annotations.SerializedName

data class EmployeeStaticsResponse(
    @SerializedName("accepted")
    val accepted: Int,
    @SerializedName("finished")
    val finished: Int,
    @SerializedName("pending")
    val pending: Int,
    @SerializedName("processing")
    val processing: Int,
    @SerializedName("rejected")
    val rejected: Int,
    @SerializedName("returned")
    val returned: Int
)