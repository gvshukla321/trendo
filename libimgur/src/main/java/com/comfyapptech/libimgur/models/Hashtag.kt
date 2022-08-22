package com.comfyapptech.libimgur.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hashtag(
    @Json(name = "indices")
    val indices: List<Int?>?,
    @Json(name = "tag")
    val tag: String?
)