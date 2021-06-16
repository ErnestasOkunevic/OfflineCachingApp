package com.ernokun.offlinecachingapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ernokun.offlinecachingapp.data.model.Cannabis
import kotlinx.coroutines.flow.Flow

@Dao
interface CannabisDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cannabisList: List<Cannabis>)

    @Query("SELECT * FROM cannabis_table")
    fun getAll(): Flow<List<Cannabis>>

    @Query("DELETE FROM cannabis_table")
    fun deleteAll()
}