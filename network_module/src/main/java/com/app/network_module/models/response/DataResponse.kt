package com.app.network_module.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataResponse(

    @Json(name = "id")
    val id: Int? = -1,

    @Json(name = "listId")
    val listId: Int? = -1,

    @Json(name = "name")
    val name: String? = ""
)
