package com.comfyapptech.trendo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comfyapptech.libimgur.models.Tag
import com.comfyapptech.trendo.data.ImgurRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val repo = ImgurRepository()
    private val _tags = MutableLiveData<List<Tag>>()

    val tags: LiveData<List<Tag>> = _tags

    fun fetchTags() {
        viewModelScope.launch(Dispatchers.IO) {
            _tags.postValue(repo.getTags())
        }
    }
}