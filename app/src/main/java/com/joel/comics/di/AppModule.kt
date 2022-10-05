package com.joel.comics.di

import android.util.Log
import com.joel.comics.model.network.MarvelApiService
import com.joel.comics.model.network.MarvelRepo
import com.joel.comics.model.network.MarvelRepositoryImpl
import com.joel.comics.utils.Constants.Companion.MARVEL_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMarvelRepository(apiService: MarvelApiService): MarvelRepo = MarvelRepositoryImpl(apiService)


    @Provides
    @Singleton
    fun provideMarvelRetrofit() : MarvelApiService {
        Log.d("D::", "REQUESTS")
        return Retrofit.Builder()
            .baseUrl(MARVEL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApiService::class.java)
    }
}