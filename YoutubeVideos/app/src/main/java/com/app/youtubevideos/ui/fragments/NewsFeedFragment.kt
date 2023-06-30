package com.app.youtubevideos.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.app.youtubevideos.databinding.FragmentNewsFeedBinding
import com.app.youtubevideos.models.UsersUiState
import com.app.youtubevideos.models.YoutubeData
import com.app.youtubevideos.ui.adapters.NewsFeedPagingAdapter
import com.app.youtubevideos.ui.viewmodels.NewsFeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NewsFeedFragment : Fragment() {

    private lateinit var newsFeedPagingAdapter: NewsFeedPagingAdapter
    private var _binding: FragmentNewsFeedBinding? = null
    private val binding get() = _binding!!
    private val newsFeedViewModel: NewsFeedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsFeedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsFeedPagingAdapter = NewsFeedPagingAdapter(newsFeedViewModel)

        binding.rvVideos.apply {
            adapter = newsFeedPagingAdapter
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty())
                    observer(newsFeedViewModel.getVideos(query))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })

        recyclerItemClickEvent()

    }


    private fun observer(function: Flow<PagingData<Any>>) {
        lifecycleScope.launchWhenCreated {
            function.collectLatest {
                launch(Dispatchers.Main) {
                    newsFeedPagingAdapter.loadStateFlow.collectLatest { states ->
                        binding.uistates =
                            UsersUiState(
                                states.source.refresh,
                                states,
                                newsFeedPagingAdapter.itemCount
                            )
                    }
                }
                newsFeedPagingAdapter.submitData(it as PagingData<YoutubeData.ItemsItem>)
            }

        }
    }

    private fun recyclerItemClickEvent() {
        newsFeedViewModel.onItemClicked.observe(viewLifecycleOwner) { it ->
            it.getContentIfNotHandled()?.let { pair ->
                pair.first?.let {
                    val data = pair.first
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=${data?.id?.videoId}")
                        )
                    )

                }
            }
        }
    }

}