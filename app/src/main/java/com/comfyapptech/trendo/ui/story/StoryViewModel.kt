package com.comfyapptech.trendo.ui.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comfyapptech.libimgur.models.Image
import com.comfyapptech.trendo.data.ImgurRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoryViewModel : ViewModel() {
    private val repo = ImgurRepository()
    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>> = _images

    fun fetchTagGallery(tagName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _images.postValue(repo.getTagGallery(tagName))
        }
    }
}