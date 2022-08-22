package com.comfyapptech.libimgur.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DescriptionAnnotations(
    @Json(name = "hashtag")
    val hashtag: List<Hashtag?>?
)