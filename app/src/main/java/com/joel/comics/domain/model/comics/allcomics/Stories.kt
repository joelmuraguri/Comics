package com.joel.comics.domain.model.comics.allcomics

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)