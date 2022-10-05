package com.joel.comics.model.marvelmodel.marvdata.marvresponse.character

data class SeriesX(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)