package com.comfyapptech.trendo.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.Coil
import coil.request.ImageRequest
import com.comfyapptech.trendo.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private val feedViewModel: FeedViewModel by viewModels()
    private val feedAdapter: FeedRecyclerAdapter = FeedRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val feed = arguments?.getString("feed")
        feed?.let { feedViewModel.updateFeed(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.feedRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        _binding?.feedRecyclerView?.adapter = feedAdapter

        feedViewModel.feed.observe(viewLifecycleOwner) {
            it.forEach { image ->
                val request = _binding?.feedRecyclerView?.width?.let { it1 ->
                    ImageRequest.Builder(requireContext())
                        .data("https:///i.imgur.com/${image.cover}.jpg")
                        .size(it1)
                        .build()
                }
                if (request != null) {
                    Coil.imageLoader(requireContext()).enqueue(request)
                }
            }
            feedAdapter.submitList(it)
        }
    }
}