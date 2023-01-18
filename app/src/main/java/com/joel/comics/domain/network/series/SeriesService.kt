package com.joel.comics.domain.network.series

import com.joel.comics.domain.model.series.allseries.AllSeries
import com.joel.comics.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface SeriesService {

    @GET("series")
    suspend fun getSeries(
        @Query("apikey") apikey : String = Constants.MARVEL_API_KEY,
        @Query("ts") ts : String = Constants.ts,
        @Query("hash") hash: String = Constants.hash(),
        @Query("limit") limit : Int,
        @Query("offset") offset : Int
    ) : AllSeries

    suspend fun getSeriesById()

    suspend fun getCharacterBySeries()

    suspend fun getComicBySeries()


}