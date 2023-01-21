package com.joel.comics.domain.model.comics.comicdetails

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)