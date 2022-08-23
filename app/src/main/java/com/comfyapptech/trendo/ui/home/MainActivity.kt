package com.comfyapptech.trendo.ui.home

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.request.ImageRequest
import com.comfyapptech.trendo.R
import com.comfyapptech.trendo.databinding.ActivityMainBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val storiesViewModel by viewModels<MainActivityViewModel>()
    private val storiesAdapter = StoriesRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.storiesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = storiesAdapter
        }

        setUpNav()

        storiesViewModel.fetchTags()

    }

    private fun setUpNav(){
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)

    }

    override fun onResume() {
        super.onResume()
        storiesViewModel.tags.observe(this){
            it.forEach { tag ->
                val request = ImageRequest.Builder(this)
                    .data("https://i.imgur.com/${tag.backgroundHash}.jpg")
                    .size(resources.getDimensionPixelSize(R.dimen.story_head_image_size))
                    .build()
                Coil.imageLoader(this).enqueue(request)
            }
            storiesAdapter.submitList(it)
        }
    }
}