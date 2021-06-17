package com.ernokun.offlinecachingapp.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ernokun.offlinecachingapp.databinding.FragmentFeedBinding
import com.ernokun.offlinecachingapp.util.CannabisAdapter
import com.ernokun.offlinecachingapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private var binding: FragmentFeedBinding? = null

    private val viewModel: FeedViewModel by viewModels()

    private val adapter: CannabisAdapter = CannabisAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFeedBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.cannabisRecyclerView?.adapter = adapter

        viewModel.cannabisList.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> toggleProgressBarVisibility(true)
                is Resource.Success -> {
                    toggleProgressBarVisibility(false)
                    adapter.submitList(resource.data)
                }
                is Resource.Error -> {
                    toggleProgressBarVisibility(false)
                    showToastMessage("Failed to refresh data: ${resource.message}")
                }
            }
        }
    }

    private fun toggleProgressBarVisibility(shouldBeVisible: Boolean) {
        binding?.progressBar?.visibility = when (shouldBeVisible) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}