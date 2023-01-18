package com.joel.comics.di

import com.joel.comics.domain.network.characters.CharacterService
import com.joel.comics.domain.network.characters.CharactersRepository
import com.joel.comics.domain.network.comics.ComicsRepository
import com.joel.comics.domain.network.comics.ComicsService
import com.joel.comics.domain.network.series.SeriesRepository
import com.joel.comics.domain.network.series.SeriesService
import com.joel.comics.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ComicsModule {

    @Provides
    @Singleton
    fun providesComicsRepository(apiService: ComicsService) : ComicsRepository =  ComicsRepository(apiService)


    @Provides
    @Singleton
    fun provideMarvelRetrofit() : ComicsService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.MARVEL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ComicsService::class.java)
    }
}