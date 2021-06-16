package com.ernokun.offlinecachingapp.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ernokun.offlinecachingapp.data.CannabisRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    cannabisRepository: CannabisRepository
) : ViewModel() {

    val cannabisList = cannabisRepository.getCannabisList().asLiveData()
}