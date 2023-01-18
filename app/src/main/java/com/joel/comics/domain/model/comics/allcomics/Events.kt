package com.joel.comics.domain.model.comics.allcomics

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)