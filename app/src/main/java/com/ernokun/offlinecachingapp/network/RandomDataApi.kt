package com.ernokun.offlinecachingapp.network

import com.ernokun.offlinecachingapp.data.model.Cannabis
import retrofit2.http.GET

interface RandomDataApi {

    companion object {
        const val BASE_URL = "https://random-data-api.com/api/"
    }

    @GET("cannabis/random_cannabis?size=20")
    suspend fun getCannabis(): List<Cannabis>
}