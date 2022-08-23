package com.comfyapptech.trendo.data

import com.comfyapptech.libimgur.ImgurClient
import com.comfyapptech.libimgur.models.Image
import com.comfyapptech.libimgur.models.Tag
import com.comfyapptech.libimgur.param.Section

class ImgurRepository {
    private val api = ImgurClient.api

    suspend fun getHotFeed(): List<Image>? {
        val response = api.getGallery(Section.HOT)
        return response.body()?.data
    }

    suspend fun getTopFeed(): List<Image>? {
        val response = api.getGallery(Section.TOP)
        return response.body()?.data
    }

    suspend fun getTags(): List<Tag>? {
        val response = api.getTags()
        return response.body()?.data?.tags
    }

    suspend fun getTagGallery(tagName: String): List<Image>? {
        val response = api.getTagGallery(tagName)
        return response.body()?.data?.items
    }
}