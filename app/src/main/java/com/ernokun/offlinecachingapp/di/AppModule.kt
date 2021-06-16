package com.ernokun.offlinecachingapp.di

import android.app.Application
import androidx.room.Room
import com.ernokun.offlinecachingapp.data.CannabisDatabase
import com.ernokun.offlinecachingapp.network.RandomDataApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(RandomDataApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideRandomDataApi(retrofit: Retrofit): RandomDataApi =
        retrofit.create(RandomDataApi::class.java)

    @Provides
    @Singleton
    fun provideCannabisDatabase(app: Application): CannabisDatabase =
        Room.databaseBuilder(app, CannabisDatabase::class.java, "cannabis_database")
            .build()
}