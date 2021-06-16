package com.ernokun.offlinecachingapp.data

import androidx.room.withTransaction
import com.ernokun.offlinecachingapp.network.RandomDataApi
import com.ernokun.offlinecachingapp.util.networkBoundResource
import javax.inject.Inject

class CannabisRepository @Inject constructor(
    private val cannabisDatabase: CannabisDatabase,
    private val api: RandomDataApi
) {
    private val cannabisDao = cannabisDatabase.cannabisDao()

    fun getCannabisList() = networkBoundResource(
        query = {
            cannabisDao.getAll()
        },
        fetch = {
            api.getCannabis()
        },
        saveFetchResult = { cannabisList ->
            cannabisDatabase.withTransaction {
                cannabisDao.deleteAll()
                cannabisDao.insert(cannabisList)
            }
        },
        shouldFetch = {
            // I don't really have any need to fully implement this right now,
            // so I will make it always return true...
            true
        }
    )
}