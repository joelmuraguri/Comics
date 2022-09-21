package com.joel.comics.di

import com.joel.comics.model.network.MarvelApiService
import com.joel.comics.model.network.MarvelRepository
import com.joel.comics.utils.Constants.Companion.MARVEL_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMarvelRepository(apiService: MarvelApiService) = MarvelRepository(apiService)

    @Provides
    @Singleton
    fun provideMarvelRetrofit() : MarvelApiService {
        return Retrofit.Builder()
            .baseUrl(MARVEL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApiService::class.java)
    }

}