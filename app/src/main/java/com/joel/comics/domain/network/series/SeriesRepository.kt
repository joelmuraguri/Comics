package com.joel.comics.domain.network.series

import com.joel.comics.domain.model.series.allseries.AllSeries
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeriesRepository @Inject constructor(
    private val api : SeriesService
) {

    suspend fun getSeries(offset : Int, limit :Int) : AllSeries {
        return api.getSeries(limit = limit, offset = offset)
    }
}