package com.ernokun.offlinecachingapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ernokun.offlinecachingapp.data.model.Cannabis

@Database(entities = [Cannabis::class], version = 1)
abstract class CannabisDatabase : RoomDatabase() {
    abstract fun cannabisDao(): CannabisDao
}