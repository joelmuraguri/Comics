package com.joel.comics.domain.model.comics.comicdetails

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)