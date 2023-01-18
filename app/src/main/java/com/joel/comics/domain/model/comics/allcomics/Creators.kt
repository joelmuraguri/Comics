package com.joel.comics.domain.model.comics.allcomics

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)