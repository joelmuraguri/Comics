package com.joel.comics.domain.model.series.allseries

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)