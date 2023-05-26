package com.app.fetchTestApp.models.language

import com.google.gson.annotations.SerializedName

data class LanguageJson(

    @field:SerializedName("messages")
    val messages: Messages? = null,
    @field:SerializedName("listing_screen")
    val listingScreen: ListingScreen? = null
)

data class ErrorMessages(

    @field:SerializedName("internal")
    val internal: String? = null,

    @field:SerializedName("no_result")
    val noResult: String? = null,

    @field:SerializedName("internet")
    val internet: String? = null,

    @field:SerializedName("file_format")
    val fileFormat: String? = null
)

data class Messages(

    @field:SerializedName("error_messages")
    val errorMessages: ErrorMessages? = null
)
data class ListingScreen(

    @field:SerializedName("header_title")
    val headerTitle: String? = null
)
