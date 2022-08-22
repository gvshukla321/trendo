package com.comfyapptech.trendo.data

import com.comfyapptech.libimgur.ImgurClient
import com.comfyapptech.libimgur.models.Item
import com.comfyapptech.libimgur.param.Section

class ImgurRepository {
    private val api = ImgurClient.api

    suspend fun getHotFeed(): List<Item>? {
        val response = api.getGallery(Section.HOT)
        return response.body()?.data
    }

    suspend fun getTopFeed(): List<Item>? {
        val response = api.getGallery(Section.TOP)
        return response.body()?.data
    }


}