package com.joel.comics.domain.model.series.allseries

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<AllSeriesResult>,
    val total: Int
)