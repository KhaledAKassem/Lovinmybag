package com.beetleware.lovinmybag.data.network.model.headers

data class ApiHeaders(
    var x_lang: String? = null,
    var authorization: String? = null,
    var content_type: String = "application/json"
)
