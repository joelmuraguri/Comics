package com.joel.comics.domain.model.characters.allcharacters

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)