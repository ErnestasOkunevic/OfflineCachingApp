package com.ernokun.offlinecachingapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "cannabis_table")
data class Cannabis(
    @PrimaryKey val id: Long = 0,
    @Json(name = "medical_use") val medicalUse: String,
    @Json(name = "health_benefit") val healthBenefit: String,
    val cannabinoid: String,
    val category: String
)