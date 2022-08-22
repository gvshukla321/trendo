package com.comfyapptech.libimgur

import com.comfyapptech.libimgur.param.Section
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class ImgurAPIv3Tests {
    private val api = ImgurClient.api

    @Test
    fun `get gallery - hot working`() = runBlocking {
        val response = api.getGallery(section = Section.HOT)
        assertNotNull(response.body())
    }

    @Test
    fun `get gallery - top working`() = runBlocking {
        val response = api.getGallery(section = Section.TOP)
        assertNotNull(response.body())
    }

    @Test
    fun `get tags working`() = runBlocking {
        val response = api.getTags()
        assertNotNull(response.body())
    }

    @Test
    fun `get tag - aww working`() = runBlocking {
        val response = api.getTagGallery("aww")
        assertNotNull(response.body())
    }
}