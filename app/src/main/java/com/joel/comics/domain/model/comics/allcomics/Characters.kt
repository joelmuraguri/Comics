package com.joel.comics.domain.model.comics.allcomics

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)