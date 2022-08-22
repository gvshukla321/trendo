package com.comfyapptech.libimgur.apis

import com.comfyapptech.libimgur.models.GalleryResponse
import com.comfyapptech.libimgur.models.TagResponse
import com.comfyapptech.libimgur.models.TagsResponse
import com.comfyapptech.libimgur.param.Section
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurAPIv3 {

    @GET("gallery/{section}")
    suspend fun getGallery(
        @Path("section") section: Section,
        @Query("album_previews") albumPreviews: Boolean = true
    ): Response<GalleryResponse>

    @GET("tags")
    suspend fun getTags(): Response<TagsResponse>

    @GET("gallery/t/{tag}")
    suspend fun getTagGallery(
        @Path("tag") tag: String
    ): Response<TagResponse>

}